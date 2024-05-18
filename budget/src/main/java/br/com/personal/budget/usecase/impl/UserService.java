package br.com.personal.budget.usecase.impl;

import br.com.personal.budget.adapter.to.UserSignUpTO;
import br.com.personal.budget.core.User;
import br.com.personal.budget.usecase.UserUseCase;
import br.com.personal.budget.usecase.exception.BudgetBadRequest;
import br.com.personal.budget.usecase.exception.SignInException;
import br.com.personal.budget.usecase.port.UserPort;
import jakarta.inject.Named;

import java.util.Optional;

@Named
public class UserService implements UserUseCase {

    private final UserPort userPort;

    public UserService(UserPort userPort) {
        this.userPort = userPort;
    }

    @Override
    public String signIn(String email, String pwd) throws SignInException {
        Optional<User> userOpt = userPort.findByEmail2(email);
        if (userOpt.isEmpty()) {
            throw new SignInException("Email not found.");
        }

        User user = userOpt.get();
        user.matches(pwd);

        return "TOKEN";
    }

    @Override
    public User signUp(User user) {

        // checks duplicate
        Optional<UserSignUpTO> userOpt = userPort.findByEmail(user.getEmail());
        if (userOpt.isPresent()) {
            throw new BudgetBadRequest();
        }

        return userPort.save(user);
    }

    @Override
    public Optional<UserSignUpTO> findById(Long id) {

        return userPort.findById(id);

    }
}
