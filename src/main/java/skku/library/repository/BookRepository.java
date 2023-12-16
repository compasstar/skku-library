package skku.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import skku.library.domain.entity.Book;
import skku.library.domain.entity.User;
import skku.library.domain.type.BookType;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByType(BookType type);

    List<Book> findByTitleContainingOrAuthorContaining(String title, String author);

    List<Book> findByUserId(User userId);


}
