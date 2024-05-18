package br.com.personal.budget.usecase;

import br.com.personal.budget.adapter.to.UserSignUpTO;
import br.com.personal.budget.core.User;
import br.com.personal.budget.usecase.exception.SignInException;

import java.util.Optional;

public interface UserUseCase {
    String signIn(String email, String pwd) throws SignInException;

    User signUp(User userSignUp);

    Optional<UserSignUpTO> findById(Long id);
}
