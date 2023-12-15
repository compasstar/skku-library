package skku.library.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import skku.library.domain.type.BookType;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id @GeneratedValue
    private Long id;

    private String title;
    private String author;
    private String publisher;
    private Integer year;

    @Enumerated(EnumType.STRING)
    private BookType type;

    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    @ColumnDefault("0")
    private Integer due;
}
