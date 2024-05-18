package br.com.personal.budget.auth;

import br.com.personal.budget.adapter.output.UserRepository;
import br.com.personal.budget.database.UserEntity;
import br.com.personal.budget.usecase.exception.BudgetBadRequest;
import br.com.personal.budget.usecase.exception.SignInException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    public AuthenticationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String signIn(String email, String pwd) throws SignInException {
        Optional<UserEntity> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            throw new SignInException("Email not found.");
        }

        UserEntity user = userOpt.get();
        matches(pwd, user.getPwd());

        return "TOKEN";
    }

    @Override
    public void signUp(String name, String email, String pwd) {
        // checks duplicate
        Optional<UserEntity> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            throw new BudgetBadRequest();
        }

        UserEntity user = new UserEntity();
        user.setPwd(encode(pwd));
        user.setName(name);
        user.setEmail(email);

        userRepository.save(user);

    }

    private void matches(String pwd, String userPwd) throws SignInException {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        boolean matches = encoder.matches(pwd, userPwd);
        if (!matches) {
            throw new SignInException("Password invalid!");
        }
    }

    private String encode(String pwd) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        return encoder.encode(pwd);
    }
}
