package skku.library.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import skku.library.domain.entity.Book;
import skku.library.domain.entity.User;
import skku.library.domain.form.BookForm;
import skku.library.domain.form.SearchForm;
import skku.library.domain.type.BookType;
import skku.library.service.AdministratorService;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AdministratorController {

    private final AdministratorService administratorService;

    /**
     * Administrator
     */
    @GetMapping("/administrator/main")
    public String administratorMain() {
        return "administrator/main-administrator";
    }

    /**
     * Users
     * get All Users
     */
    @GetMapping("/administrator/users")
    public String administratorUsers(Model model) {
        model.addAttribute("users", administratorService.getUsersAll());
        return "administrator/users";
    }

    @GetMapping("/administrator/users/userInformation/{id}")
    public String administratorUserInformation(@PathVariable Long id, Model model) {
        Optional<User> user = administratorService.getUserById(id);
        model.addAttribute("user", user.get());

        List<Book> books = administratorService.getBooksByUserId(id);
        model.addAttribute("books", books);

        return "administrator/user-information";
    }

    /**
     * Resign User
     */
    @PostMapping("/administrator/users/userInformation/{id}/resign")
    public String administratorUserInformationResign(@PathVariable Long id) {
        administratorService.resignUser(id);
        return "redirect:/administrator/users";
    }


    /**
     * Books
     * get All Books
     */
    @GetMapping("/administrator/books")
    public String administratorBooks(@ModelAttribute("search") SearchForm searchForm, Model model) {
        model.addAttribute("books", administratorService.getBooksAll());
        return "administrator/books";
    }

    /**
     * Search as Type
     * Literature, Non-Literature, Self-Improvement, Essay, ETC.
     */
    @GetMapping("/administrator/books/{type}")
    public String administratorBooksType(@ModelAttribute("search") SearchForm searchForm, @PathVariable String type, Model model) {
        BookType bookType = getBookType(type);
        model.addAttribute("books", administratorService.getBooksAsType(bookType));
        return "administrator/books";
    }

    /**
     * Search as Title or Author
     */
    @PostMapping("/administrator/books")
    public String administratorBooksSearch(@ModelAttribute("search") SearchForm searchForm, Model model) {
        if (searchForm.getSearch().equals("")) {
            return "redirect:/administrator/books";
        }
        List<Book> books = administratorService.getBooksAsTitleOrAuthor(searchForm.getSearch(), searchForm.getSearch());
        model.addAttribute("books", books);
        return "administrator/books";
    }


    /**
     * Book Information
     */
    @GetMapping("/administrator/books/bookAdministrator/{id}")
    public String administratorBook(@PathVariable Long id, Model model) {
        Book book = administratorService.getBookById(id);
        model.addAttribute("book", book);
        return "administrator/book-administrator";
    }

    /**
     * Add Book
     */
    @GetMapping("/administrator/books/addBook")
    public String administratorAddBook(@ModelAttribute("book")BookForm bookForm) {
        bookForm.setType("literature");
        return "administrator/add-book";
    }


    @Value("${file.dir}")
    private String fileDir;

    @PostMapping("/administrator/books/addBook")
    public String administratorAddBookPost(@ModelAttribute("book")BookForm bookForm) throws IOException {
        MultipartFile file = bookForm.getFile();
        if (!file.isEmpty()) {
            String fullPath = fileDir + file.getOriginalFilename();
            log.info("fullPath = " + fullPath);
            file.transferTo(new File(fullPath));
        }

        Book book = new Book();
        book.setTitle(bookForm.getTitle());
        book.setAuthor(bookForm.getAuthor());
        book.setPublisher(bookForm.getPublisher());
        book.setYear(bookForm.getYear());
        BookType bookType = getBookType(bookForm.getType());
        book.setType(bookType);
        book.setImage(file.getOriginalFilename());
        administratorService.saveBook(book);

        return "redirect:/administrator/books";
    }


    /**
     * Edit a Book
     */
    @GetMapping("/administrator/books/editBook/{id}")
    public String administratorEditBook(@PathVariable Long id, @ModelAttribute("book")BookForm bookForm, Model model) {
        Book book = administratorService.getBookById(id);
        bookForm.setTitle(book.getTitle());
        bookForm.setAuthor(book.getAuthor());
        bookForm.setPublisher(book.getPublisher());
        bookForm.setYear(book.getYear());
        BookType type = book.getType();
        bookForm.setType(changeFormType(type));


        model.addAttribute("bookImage", book.getImage());
        model.addAttribute("bookId", id);
        return "administrator/edit-book";
    }

    @PostMapping("/administrator/books/editBook/{id}")
    public String administratorEditBookPost(@PathVariable Long id, @ModelAttribute("book")BookForm bookForm) throws IOException {
        administratorService.updateBook(id, bookForm);
        return "redirect:/administrator/books";
    }

    /**
     * Delete the Book
     */
    @PostMapping("/administrator/books/deleteBook/{id}")
    public String administratorDeleteBookPost(@PathVariable Long id) {
        administratorService.deleteBook(id);
        return "redirect:/administrator/books";
    }


    private static BookType getBookType(String type) {
        BookType bookType = null;
        if (type.equals("literature")) {
            bookType = BookType.LITERATURE;
        } else if (type.equals("nonLiterature")) {
            bookType = BookType.NON_LITERATURE;
        } else if (type.equals("essay")) {
            bookType = BookType.ESSAY;
        } else if (type.equals("selfImprovement")) {
            bookType = BookType.SELF_IMPROVEMENT;
        } else if (type.equals("etc")) {
            bookType = BookType.ETC;
        }
        return bookType;
    }

    private static String changeFormType(BookType type) {
        String formType = null;
        if (type.equals(BookType.LITERATURE)) {
            formType = "literature";
        } else if (type.equals(BookType.NON_LITERATURE)) {
            formType = "nonLiterature";
        } else if (type.equals(BookType.SELF_IMPROVEMENT)) {
            formType = "selfImprovement";
        } else if (type.equals(BookType.ESSAY)) {
            formType = "essay";
        } else if (type.equals(BookType.ETC)) {
            formType = "etc";
        }
        return formType;
    }
}
