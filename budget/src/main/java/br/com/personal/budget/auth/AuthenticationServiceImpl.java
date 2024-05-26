package br.com.personal.budget.auth;

import br.com.personal.budget.core.domain.User;
import br.com.personal.budget.core.usecase.exception.SignInException;
import br.com.personal.budget.core.usecase.exception.UserException;
import br.com.personal.budget.core.usecase.port.UserPort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserPort userPort;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final String MSG_ERROR = "Email or password invalid!";

    public AuthenticationServiceImpl(UserPort userPort, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userPort = userPort;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public String signIn(String email, String pwd) throws SignInException {
        Optional<User> userOpt = userPort.findByEmail(email);
        if (userOpt.isEmpty()) {
            throw new SignInException("Email not found.");
        }

        User user = userOpt.get();
        matches(pwd, user.getPwd());

        return "TOKEN";
    }

    @Override
    public User signUp(User user) {
        // checks duplicate
        Optional<User> userOpt = userPort.findByEmail(user.getEmail());
        if (userOpt.isPresent()) {
            throw new UserException(MSG_ERROR);
        }

        User userToSave = user.toBuilder()
                .pwd(encode(user.getPwd()))
                .build();

        return userPort.save(userToSave);
    }

    private void matches(String pwd, String userPwd) throws SignInException {
        boolean matches = bCryptPasswordEncoder.matches(pwd, userPwd);
        if (!matches) {
            throw new SignInException(MSG_ERROR);
        }
    }

    private String encode(String pwd) {
        return bCryptPasswordEncoder.encode(pwd);
    }
}
