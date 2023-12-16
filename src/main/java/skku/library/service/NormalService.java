package skku.library.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import skku.library.domain.entity.Book;
import skku.library.domain.entity.User;
import skku.library.domain.type.BookType;
import skku.library.repository.BookRepository;
import skku.library.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class NormalService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final LoginService loginService;
    private final EntityManager em;

    public List<Book> getBooksAll() {
        return bookRepository.findAll();
    }

    public List<Book> getBooksAsType(BookType type) {
        return bookRepository.findByType(type);
    }

    public List<Book> getBooksAsTitleOrAuthor(String title, String author) {
        return bookRepository.findByTitleContainingOrAuthorContaining(title, author);
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Boolean borrowBook(Long id) {
        Book book = bookRepository.findById(id).get();
        if (book.getUserId() != null) {
            return false;
        }
        Long userId = loginService.getUserId();
        User user = userRepository.findById(userId).get();
        book.setUserId(user);
        book.setDue(0);
        em.flush();
        em.clear();
        return true;
    }

    public Boolean renewBook(Long id) {
        Book book = bookRepository.findById(id).get();
        Long userId = loginService.getUserId();
        if (book.getUserId().getId() != userId) {
            return false;
        }
        book.setDue(0);
        em.flush();
        em.clear();
        return true;
    }

    public Boolean returnBook(Long id) {
        Book book = bookRepository.findById(id).get();
        Long userId = loginService.getUserId();
        if (book.getUserId().getId() != userId) {
            return false;
        }
        book.setUserId(null);
        em.flush();
        em.clear();
        return true;
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

    public List<Book> getHotBooks() {
        List<Book> booksAll = bookRepository.findAll();
        List<Book> hotBooks = new ArrayList<>();
        hotBooks.add(booksAll.get(0));
        hotBooks.add(booksAll.get(1));
        hotBooks.add(booksAll.get(2));
        hotBooks.add(booksAll.get(3));
        return hotBooks;
    }

    public void deleteAccount() {
        Long userId = loginService.getUserId();
        userRepository.deleteById(userId);
    }
}
