package gov.sp.fatec.bookblog.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import gov.sp.fatec.bookblog.model.User;
import gov.sp.fatec.bookblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/users")
public class UsersController {
    @Autowired
    private UserService userService;

    @PostMapping("signin")
    @JsonView(User.Token.View.class)
    public User.Token signIn(@RequestBody final User user) throws JsonProcessingException, AuthenticationException {
        return userService.signIn(user);
    }

    @PostMapping("signup")
    @JsonView(User.Token.View.class)
    public User.Token signUp(@RequestBody final User user)
            throws JsonProcessingException, AuthenticationException {
        return userService.signUp(user);
    }
}
