package skku.library.domain.form;

import lombok.Data;

@Data
public class SignUpForm {
    private String id;
    private String password;
    private String confirmPassword;
    private String name;
    private String type;
}
