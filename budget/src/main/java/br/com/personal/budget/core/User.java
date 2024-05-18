package br.com.personal.budget.core;

import br.com.personal.budget.usecase.exception.SignInException;
import jakarta.annotation.Nonnull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class User {

    private final String name;
    private final String email;
    private final String pwd;


    public User(@Nonnull String name, @Nonnull String email, @Nonnull String pwd) {
        this.name = name;
        this.email = email;
        this.pwd = pwd;
    }

    public String encode() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        return encoder.encode(pwd);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void matches(String pwd) throws SignInException {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        boolean matches = encoder.matches(pwd, this.pwd);
        if (!matches) {
            throw new SignInException("Password invalid!");
        }
    }

}
