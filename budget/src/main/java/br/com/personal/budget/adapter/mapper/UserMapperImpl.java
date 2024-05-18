package br.com.personal.budget.adapter.mapper;

import br.com.personal.budget.adapter.to.UserSignUpPwdTO;
import br.com.personal.budget.adapter.to.UserSignUpTO;
import br.com.personal.budget.core.User;
import br.com.personal.budget.database.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserSignUpTO map(User user) {
        return new UserSignUpTO(user.getName(), user.getEmail());
    }

    @Override
    public User map(UserSignUpPwdTO userSignUp) {
        return new User(userSignUp.name(), userSignUp.email(), userSignUp.pwd());
    }

    @Override
    public UserSignUpTO map(UserEntity userEntity) {
        return new UserSignUpTO(userEntity.getName(), userEntity.getEmail());
    }
}
