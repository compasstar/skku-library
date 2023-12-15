package skku.library.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import skku.library.domain.type.UserType;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private String loginId;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType type;
}
