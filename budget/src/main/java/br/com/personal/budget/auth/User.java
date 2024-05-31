package br.com.personal.budget.auth;

import br.com.personal.budget.auth.UserException;
import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@Getter
public class User {

    private final String name;
    private final String email;
    private final String pwd;

    @Builder
    User(String name, String email, String pwd) {
        if (name == null) {
            throw new UserException("User name can't be null.");
        }
        if (email == null) {
            throw new UserException("Email can't be null.");
        }
        if (pwd == null) {
            throw new UserException("Password can't be null.");
        }
        this.name = name;
        this.email = email;
        this.pwd = pwd;

    }

}
