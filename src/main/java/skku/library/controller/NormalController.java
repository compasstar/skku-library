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

@Slf4j
@Controller
@RequiredArgsConstructor
public class NormalController {

    private final NormalService normalService;

    @GetMapping("/normal/main")
    public String normalMain() {
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




    @GetMapping("/normal/bookNormal")
    public String normalBook() {
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
