package skku.library;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import skku.library.domain.entity.Book;
import skku.library.domain.entity.User;
import skku.library.domain.type.BookType;
import skku.library.domain.type.UserType;
import skku.library.repository.BookRepository;
import skku.library.repository.UserRepository;

import java.util.List;


@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class DataInjection {


    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    EntityManager em;

    @Test
    void userDataInjection() {
        User user = User.builder()
                .name("user1")
                .loginId("user1_id")
                .password("user1_password")
                .type(UserType.ADMINISTRATOR)
                .build();
        userRepository.save(user);

        User user2 = User.builder()
                .name("user2")
                .loginId("user2_id")
                .password("user2_password")
                .type(UserType.NORMAL)
                .build();
        userRepository.save(user2);

        User user3 = User.builder()
                .name("user3")
                .loginId("user3_id")
                .password("user3_password")
                .type(UserType.NORMAL)
                .build();
        userRepository.save(user3);

        User user4 = User.builder()
                .name("user4")
                .loginId("user4_id")
                .password("user4_password")
                .type(UserType.NORMAL)
                .build();
        userRepository.save(user4);

        List<User> all = userRepository.findAll();
        System.out.println("user size = " + all.size());
        for (User user1 : all) {
            System.out.println("user name = " + user1.getName());
        }

        em.flush();
        em.clear();
    }

    @Test
    void bookDataInjection() {
        Book book = Book.builder()
                .title("book1")
                .author("book1_author")
                .publisher("book1_publisher")
                .year(2001)
                .type(BookType.LITERATURE)
                .build();
        bookRepository.save(book);

        Book book2 = Book.builder()
                .title("book2")
                .author("book2_author")
                .publisher("book2_publisher")
                .year(2002)
                .type(BookType.NON_LITERATURE)
                .build();
        bookRepository.save(book2);

        Book book3 = Book.builder()
                .title("book3")
                .author("book3_author")
                .publisher("book3_publisher")
                .year(2003)
                .type(BookType.SELF_IMPROVEMENT)
                .build();
        bookRepository.save(book3);

        Book book4 = Book.builder()
                .title("book4")
                .author("book4_author")
                .publisher("book4_publisher")
                .year(2004)
                .type(BookType.ESSAY)
                .build();
        bookRepository.save(book4);

        Book book5 = Book.builder()
                .title("book5")
                .author("book5_author")
                .publisher("book5_publisher")
                .year(2005)
                .type(BookType.ETC)
                .build();
        bookRepository.save(book5);

        List<Book> all = bookRepository.findAll();
        System.out.println("book size = " + all.size());

        em.flush();
        em.clear();
    }

}
