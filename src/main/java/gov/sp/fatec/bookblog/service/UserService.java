package gov.sp.fatec.bookblog.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import gov.sp.fatec.bookblog.model.User;
import gov.sp.fatec.bookblog.repository.UserRepository;
import gov.sp.fatec.bookblog.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("permitAll()")
    public User.Token signIn(final User user) throws JsonProcessingException, AuthenticationException {
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(),
                user.getPassword());

        User authenticatedUser = (User) authenticationManager.authenticate(authentication).getPrincipal();

        String token = JwtUtil.generateToken(authenticatedUser);

        return new User.Token(authenticatedUser, token);
    }

    @PreAuthorize("permitAll()")
    public User.Token signUp(final User user)
            throws JsonProcessingException, AuthenticationException {
        return signIn(userRepository.save(user));
    }

    public Optional<User> findByUsername(final String username) {
        return userRepository.findByUsername(username);
    }

    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
