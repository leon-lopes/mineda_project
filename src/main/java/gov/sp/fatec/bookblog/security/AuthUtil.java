package gov.sp.fatec.bookblog.security;

import gov.sp.fatec.bookblog.model.User;
import gov.sp.fatec.bookblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AuthUtil {
    private static UserService userService;

    private AuthUtil() {
    }

    public static User getAuthenticatedUser() {
        final String username = getAuthenticatedUserName();

        return userService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public static String getAuthenticatedUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Autowired
    public void setUserService(final UserService userService) {
        AuthUtil.userService = userService;
    }
}
