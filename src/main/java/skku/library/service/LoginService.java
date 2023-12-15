package skku.library.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import skku.library.domain.entity.User;
import skku.library.domain.form.SignUpForm;
import skku.library.domain.type.UserType;
import skku.library.repository.UserRepository;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private Long userId;

    public boolean login(String loginId, String password) {
        Optional<User> user = Optional.ofNullable(userRepository.findByLoginIdAndPassword(loginId, password));
        if (user.equals(Optional.empty())) {
            return false;
        }
        this.userId = user.get().getId();
        return true;
    }

    public UserType getUserType() {
        Optional<User> user = userRepository.findById(userId);
        return user.get().getType();
    }

    public boolean checkSignUp(SignUpForm signUpForm) {
        //check Id Duplicate
        Optional<User> userById = Optional.ofNullable(userRepository.findByLoginId(signUpForm.getId()));
        if (!userById.isEmpty()) {
            return false;
        }
        //check Confirm Password
        if (!signUpForm.getPassword().equals(signUpForm.getConfirmPassword())) {
            return false;
        }
        return true;
    }

    public void saveUser(SignUpForm signUpForm) {
        UserType type = null;
        if (signUpForm.getType().equals("NORMAL")) {
            type = UserType.NORMAL;
        } else if (signUpForm.getType().equals("ADMINISTRATOR")) {
            type = UserType.ADMINISTRATOR;
        }
        userRepository.save(User.builder()
                .name(signUpForm.getName())
                .loginId(signUpForm.getId())
                .password(signUpForm.getPassword())
                .type(type)
                .build()
        );
    }

    public Long getUserId() {
        return userId;
    }
}
