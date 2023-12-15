package skku.library.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import skku.library.domain.form.LoginForm;
import skku.library.domain.form.SignUpForm;
import skku.library.domain.type.UserType;
import skku.library.service.LoginService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    /**
     * login
     */
    @GetMapping("/")
    public String login(@ModelAttribute("login")LoginForm loginForm) {
        return "login";
    }

    @PostMapping("/")
    public String loginPost(@ModelAttribute("login")LoginForm loginForm, Model model) {
        boolean loginResult = loginService.login(loginForm.getId(), loginForm.getPassword());

        //Fail Login
        if (!loginResult) {
            return "redirect:/login/fail";
        }

        UserType userType = loginService.getUserType();
        if (userType.equals(UserType.NORMAL)) {
            return "redirect:/normal/main";
        } else if (userType.equals(UserType.ADMINISTRATOR)) {
            return "redirect:/administrator/main";
        }
        return "redirect:/";
    }

    @GetMapping("/login/fail")
    public String loginFail(@ModelAttribute("login")LoginForm loginForm) {
        return "login-fail";
    }


    /**
     * Sign Up
     */
    @GetMapping("/signUp")
    public String signUp(@ModelAttribute("signUp")SignUpForm signUpForm) {
        signUpForm.setType("NORMAL");
        return "sign-up";
    }

    @PostMapping("/signUp")
    public String signUpPost(@ModelAttribute("signUp")SignUpForm signUpForm) {
        signUpForm.setType("NORMAL");

        boolean checkSignUp = loginService.checkSignUp(signUpForm);
        //SignUp Fail!
        if (!checkSignUp) {
            return "redirect:/signUp/fail";
        }
        //SignUp Success!
        loginService.saveUser(signUpForm);
        return "redirect:/";
    }

    @GetMapping("/signUp/fail")
    public String signUpFail(@ModelAttribute("signUp")SignUpForm signUpForm) {
        signUpForm.setType("NORMAL");
        return "sign-up-fail";
    }
}
