package br.com.personal.budget.core;

import jakarta.annotation.Nonnull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class User {

    private final String name;
    private final String email;
    private final String pwd;
    private final String pwdEncode;

    public User(@Nonnull String name, @Nonnull String email, @Nonnull String pwd) {
        this.name = name;
        this.email = email;
        this.pwd = pwd;
        this.pwdEncode = encode();
    }

    private String encode() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        return encoder.encode(pwd);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPwdEncode() {
        return pwdEncode;
    }
}
