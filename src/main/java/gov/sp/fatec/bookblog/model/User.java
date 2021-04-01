package gov.sp.fatec.bookblog.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class User implements UserDetails {
    private static final long serialVersionUID = -2716502827930129870L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @JsonView(View.class)
    private final Long id = null;

    @Column(length = 16, unique = true)
    @NotNull
    @Getter
    @JsonView(View.class)
    private final String username;

    @NotNull
    @Getter
    private final String password;

    @NotNull
    @Getter
    @JsonView(View.class)
    private final String name;

    @Column(columnDefinition = "boolean default false")
    @NotNull
    @Getter
    @JsonView(View.class)
    private final Boolean admin = false;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "owner", orphanRemoval = true)
    @Getter
    private final List<Book> books = Collections.emptyList();

    public User() {
        username = null;
        password = null;
        name = null;
    }

    public User(@JsonProperty("username") final String username, @JsonProperty("name") final String name, @JsonProperty("password") final String password) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    @Override
    public List<SimpleGrantedAuthority> getAuthorities() {
        final List<SimpleGrantedAuthority> authorities = new ArrayList<>(2);

        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        if (this.getAdmin()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public interface View {
    }

    public static class Token {
        @Getter
        @JsonView(View.class)
        private final Long id;

        @Getter
        @JsonView(View.class)
        private final String username;

        @Getter
        @JsonView(View.class)
        private final String name;

        @Getter
        @JsonView(View.class)
        private final String token;

        @Getter
        @JsonView(View.class)
        private final Boolean admin;

        public Token(final User user, final String token) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.name = user.getName();
            this.admin = user.getAdmin();

            this.token = token;
        }

        public interface View extends User.View {
        }
    }
}
