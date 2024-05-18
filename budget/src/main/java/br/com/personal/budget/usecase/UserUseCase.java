package br.com.personal.budget.usecase;

import br.com.personal.budget.adapter.to.UserSignUpTO;
import br.com.personal.budget.core.User;

import java.util.Optional;

public interface UserUseCase {
    User save(User userSignUp);

    Optional<UserSignUpTO> findById(Long id);
}
