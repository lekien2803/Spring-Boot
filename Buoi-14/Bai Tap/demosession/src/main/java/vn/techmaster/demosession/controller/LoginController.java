package vn.techmaster.demosession.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.techmaster.demosession.dto.UserDto;
import vn.techmaster.demosession.exception.UserException;
import vn.techmaster.demosession.model.User;
import vn.techmaster.demosession.request.LoginRequest;
import vn.techmaster.demosession.service.UserService;

@Controller
@RequestMapping("/")
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String showHomePage(Model model, HttpSession session) {
        UserDto userDto = (UserDto) session.getAttribute("user");
        if (userDto != null) {
            model.addAttribute("user", userDto);
        }
        return "index";
    }

    @GetMapping("login")
    public String showLogin(Model model) {
        model.addAttribute("loginrequest", new LoginRequest("", ""));
        return "login";
    }

    @PostMapping("login")
    public String handleLogin(@Valid @ModelAttribute("loginrequest") LoginRequest loginRequest, BindingResult result,
            HttpSession session) {
        if (result.hasErrors()) {
            return "login";
        }
        User user;
        try {
            user = userService.login(loginRequest.email(), loginRequest.password());
            session.setAttribute("user", new UserDto(user.getId(), user.getFullname(), user.getEmail()));
            return "redirect:/";
        } catch (UserException e) {
            switch (e.getMessage()) {
                case "User not found":
                    result.addError(new FieldError("loginrequest", "email", "Email does not exist"));
                    break;
                case "User is not active":
                    result.addError(new FieldError("loginrequest", "email", "User is not active"));
                    break;
                case "Password incorrect":
                    result.addError(new FieldError("loginrequest", "email", "Password incorrect"));
                    break;

            }
            return "login";
        }
    }

    @GetMapping("admin")
    public String showAdminPage(HttpSession session) {
        UserDto userDto = (UserDto) session.getAttribute("user");
        if (userDto != null) {
            return "admin";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }

    @GetMapping("register")
    public String showRegisterPage() {
        return "register";
    }

    @GetMapping("foo")
    public String foo() {
        throw new UserException("User not found");
    }
}
