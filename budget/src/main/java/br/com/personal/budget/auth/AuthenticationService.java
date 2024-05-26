package br.com.personal.budget.auth;

import br.com.personal.budget.core.domain.User;
import br.com.personal.budget.core.usecase.exception.SignInException;

public interface AuthenticationService {
    String signIn(String email, String pwd) throws SignInException;

    User signUp(User user);
}
