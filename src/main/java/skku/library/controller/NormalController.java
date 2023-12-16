package skku.library.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import skku.library.domain.entity.Book;
import skku.library.domain.entity.User;
import skku.library.domain.form.SearchForm;
import skku.library.domain.type.BookType;
import skku.library.domain.type.UserType;
import skku.library.repository.UserRepository;
import skku.library.service.NormalService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class NormalController {

    private final NormalService normalService;

    @GetMapping("/normal/main")
    public String normalMain(Model model) {
        List<Book> hotBooks = normalService.getHotBooks();
        model.addAttribute("hotBooks", hotBooks);
        return "normal/main-normal";
    }

    /**
     * Borrow
     * get All Books
     */
    @GetMapping("/normal/borrow")
    public String normalBorrow(@ModelAttribute("search") SearchForm searchForm, Model model) {
        model.addAttribute("books", normalService.getBooksAll());
        return "normal/borrow";
    }

    /**
     * Search as Type
     * Literature, Non-Literature, Self-Improvement, Essay, ETC.
     */
    @GetMapping("/normal/borrow/{type}")
    public String normalBorrowType(@ModelAttribute("search") SearchForm searchForm, @PathVariable String type, Model model) {
        BookType bookType = getBookType(type);
        model.addAttribute("books", normalService.getBooksAsType(bookType));
        return "normal/borrow";
    }

    /**
     * Search as Title or Author
     */
    @PostMapping("/normal/borrow")
    public String normalBorrowSearch(@ModelAttribute("search") SearchForm searchForm, Model model) {
        if (searchForm.getSearch().equals("")) {
            return "redirect:/normal/borrow";
        }
        List<Book> books = normalService.getBooksAsTitleOrAuthor(searchForm.getSearch(), searchForm.getSearch());
        model.addAttribute("books", books);
        return "normal/borrow";
    }


    /**
     * MyLibrary
     */
    @GetMapping("/normal/myLibrary")
    public String normalMyLibrary(Model model) {
        List<Book> books = normalService.getBooksByUserId();
        model.addAttribute("books", books);

        String name = normalService.getUserNameByUserId();
        model.addAttribute("name", name);
        return "normal/my-library";
    }

    /**
     * MyLibrary - Delete Account
     */
    @PostMapping("/normal/myLibrary")
    public String normalMyLibraryDeleteAccount() {
        normalService.deleteAccount();
        return "redirect:/";
    }


    /**
     * Book Information Page
     */
    @GetMapping("/normal/bookNormal/{id}")
    public String normalBook(@PathVariable Long id, Model model) {
        Book book = normalService.getBookById(id).get();
        model.addAttribute("book", book);
        return "normal/book-normal";
    }

    /**
     * In Book Information Page
     * Borrow Button
     */
    @GetMapping("/normal/bookNormal/{id}/borrow")
    public String normalBookBorrow(@PathVariable Long id, Model model) {
        String message = null;
        if (normalService.borrowBook(id)) {
            message = "Successfully borrowed the book!";
        } else {
            message = "Someone else is already borrowing the book";
        }
        Book book = normalService.getBookById(id).get();
        model.addAttribute("book", book);
        model.addAttribute("message", message);
        return "normal/book-normal";
    }

    /**
     * Renew the Book
     */
    @GetMapping("/normal/bookNormal/{id}/renew")
    public String normalBookRenew(@PathVariable Long id, Model model) {
        String message = null;
        if (normalService.renewBook(id)) {
            message = "Successfully renew the book!";
        } else {
            message = "Please borrow the book first";
        }
        Book book = normalService.getBookById(id).get();
        model.addAttribute("book", book);
        model.addAttribute("message", message);
        return "normal/book-normal";
    }

    /**
     * Return the Book
     */
    @GetMapping("/normal/bookNormal/{id}/return")
    public String normalBookReturn(@PathVariable Long id, Model model) {
        String message = null;
        if (normalService.returnBook(id)) {
            message = "Successfully return the book!";
        } else {
            message = "Please borrow the book first";
        }
        Book book = normalService.getBookById(id).get();
        model.addAttribute("book", book);
        model.addAttribute("message", message);
        return "normal/book-normal";
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
}
