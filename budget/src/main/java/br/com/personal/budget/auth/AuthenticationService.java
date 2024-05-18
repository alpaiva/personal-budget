package br.com.personal.budget.auth;

import br.com.personal.budget.usecase.exception.SignInException;

public interface AuthenticationService {
    String signIn(String email, String pwd) throws SignInException;

    void signUp(String name, String email, String pwd);
}
