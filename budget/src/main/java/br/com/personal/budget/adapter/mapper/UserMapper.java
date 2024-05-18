package br.com.personal.budget.adapter.mapper;

import br.com.personal.budget.adapter.to.UserSignUpPwdTO;
import br.com.personal.budget.adapter.to.UserSignUpTO;
import br.com.personal.budget.core.User;
import br.com.personal.budget.database.UserEntity;

public interface UserMapper {
    UserSignUpTO map(User user);

    User map(UserSignUpPwdTO userSignUp);

    UserSignUpTO map(UserEntity userEntity);

    User map2(UserEntity userEntity);
}
