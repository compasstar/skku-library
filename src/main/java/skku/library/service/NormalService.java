package skku.library.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import skku.library.domain.entity.Book;
import skku.library.domain.entity.User;
import skku.library.domain.type.BookType;
import skku.library.repository.BookRepository;
import skku.library.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NormalService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final LoginService loginService;

    public List<Book> getBooksAll() {
        return bookRepository.findAll();
    }

    public List<Book> getBooksAsType(BookType type) {
        return bookRepository.findByType(type);
    }

    public List<Book> getBooksAsTitleOrAuthor(String title, String author) {
        return bookRepository.findByTitleContainingOrAuthorContaining(title, author);
    }

    public List<User> getUsersAll() {
        return userRepository.findAll();
    }

    public List<Book> getBooksByUserId() {
        Long userId = loginService.getUserId();
        Optional<User> user = userRepository.findById(userId);
        List<Book> books = bookRepository.findByUserId(user.get());
        return books;
    }

    public String getUserNameByUserId() {
        Long userId = loginService.getUserId();
        Optional<User> user = userRepository.findById(userId);
        return user.get().getName();
    }
}
