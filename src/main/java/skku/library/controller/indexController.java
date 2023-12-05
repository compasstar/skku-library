package skku.library.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class indexController {

    @GetMapping("/normal/main")
    public String normalMain() {
        return "normal/main";
    }

    @GetMapping("/normal/borrow")
    public String normalBorrow() {
        return "normal/borrow";
    }

    @GetMapping("/normal/myLibrary")
    public String normalMyLibrary() {
        return "normal/myLibrary";
    }

    @GetMapping("/administrator/main")
    public String administratorMain() {
        return "administrator/main";
    }

    @GetMapping("/administrator/users")
    public String administratorUsers() {
        return "administrator/users";
    }

    @GetMapping("/administrator/users/userInformation")
    public String administratorUserInformation() {
        return "administrator/userInformation";
    }

    @GetMapping("/administrator/books")
    public String administratorBooks() {
        return "administrator/books";
    }

    @GetMapping("/administrator/books/addBook")
    public String administratorAddBook() {
        return "administrator/addBook";
    }
}
