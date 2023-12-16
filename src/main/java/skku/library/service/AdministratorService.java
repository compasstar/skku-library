package skku.library.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import skku.library.domain.entity.Book;
import skku.library.domain.entity.User;
import skku.library.domain.form.BookForm;
import skku.library.domain.type.BookType;
import skku.library.repository.BookRepository;
import skku.library.repository.UserRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AdministratorService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
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

    public Book getBookById(Long id) {
        return bookRepository.findById(id).get();
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

    public void resignUser(Long id) {
        userRepository.deleteById(id);
    }

    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Value("${file.dir}")
    private String fileDir;

    public void updateBook(Long id, BookForm bookForm) throws IOException {
        Book book = bookRepository.findById(id).get();
        book.setTitle(bookForm.getTitle());
        book.setAuthor(bookForm.getAuthor());
        book.setPublisher(bookForm.getPublisher());
        book.setYear(bookForm.getYear());
        BookType bookType = getBookType(bookForm.getType());
        book.setType(bookType);

        MultipartFile file = bookForm.getFile();
        if (!file.isEmpty()) {
            String fullPath = fileDir + file.getOriginalFilename();
            file.transferTo(new File(fullPath));
            book.setImage(file.getOriginalFilename());
        }

        em.flush();
        em.clear();
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
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
