package br.com.personal.budget.auth;

import br.com.personal.budget.adapter.output.UserAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class UserInfoService implements UserDetailsService {

    private static final String MSG_ERROR = "Email or password invalid!";

    @Autowired
    private UserAdapter userAdapter;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt = userAdapter.findByEmail(username);
        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException("Email not found.");
        }

        User user = userOpt.get();

        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getPassword() {
                return user.getPwd();
            }

            @Override
            public String getUsername() {
                return user.getEmail();
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
        };

    }

    public User add(User user) {
        // checks duplicate
        Optional<User> userOpt = userAdapter.findByEmail(user.getEmail());
        if (userOpt.isPresent()) {
            throw new UserException(MSG_ERROR);
        }

        User userToSave = user.toBuilder()
                .pwd(encode(user.getPwd()))
                .build();

        return userAdapter.save(userToSave);

    }

    private String encode(String pwd) {
        return passwordEncoder.encode(pwd);
    }

    public Long getUserId(String username) {
        Optional<User> userOpt = userAdapter.findByEmail(username);

        User user = userOpt.orElseThrow(() -> new UserException(MSG_ERROR));

        return user.getId();
    }
}
