package br.com.personal.budget.usecase.port;

import br.com.personal.budget.adapter.to.UserSignUpTO;
import br.com.personal.budget.core.User;

import java.util.Optional;

public interface UserPort {
    User save(User userEntity);

    Optional<UserSignUpTO> findById(Long id);

    Optional<UserSignUpTO> findByEmail(String email);

    Optional<User> findByEmail2(String email);
}
