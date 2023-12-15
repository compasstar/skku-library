package skku.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import skku.library.domain.entity.Book;
import skku.library.domain.entity.User;
import skku.library.domain.type.BookType;
import skku.library.repository.BookRepository;
import skku.library.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdministratorService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

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

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<Book> getBooksByUserId(Long id) {
        Optional<User> user = userRepository.findById(id);
        List<Book> books = bookRepository.findByUserId(user.get());
        return books;
    }
}
