package skku.library.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import skku.library.domain.entity.Book;
import skku.library.domain.entity.User;
import skku.library.domain.form.SearchForm;
import skku.library.domain.type.BookType;
import skku.library.service.AdministratorService;

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


    @GetMapping("/administrator/books/bookAdministrator")
    public String administratorBook() {
        return "administrator/book-administrator";
    }

    @GetMapping("/administrator/books/addBook")
    public String administratorAddBook() {
        return "administrator/add-book";
    }

    @GetMapping("/administrator/books/editBook")
    public String administratorEditBook() {
        return "administrator/edit-book";
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
