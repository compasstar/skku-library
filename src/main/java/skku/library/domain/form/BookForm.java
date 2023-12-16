package skku.library.domain.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BookForm {
    private String title;
    private String author;
    private String publisher;
    private Integer year;
    private String type;
    private MultipartFile file;
}
