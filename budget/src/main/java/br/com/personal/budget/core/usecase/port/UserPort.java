package br.com.personal.budget.core.usecase.port;

import br.com.personal.budget.core.domain.User;

import java.util.Optional;

public interface UserPort {

    User save(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long userId);
}
