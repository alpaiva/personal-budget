package br.com.personal.budget.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder(toBuilder = true)
@Getter
public class User {

    private final Long id;
    private final String name;
    private final String email;
    private final String pwd;

    @Builder
    User(Long id, String name, String email, String pwd) {
        this.name = name;
        this.email = email;
        this.pwd = pwd;
        this.id = id;
    }

}
