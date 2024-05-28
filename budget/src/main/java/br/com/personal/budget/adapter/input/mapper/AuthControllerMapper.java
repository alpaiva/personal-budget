package br.com.personal.budget.adapter.input.mapper;

import br.com.personal.budget.adapter.input.to.UserSignUpTO;
import br.com.personal.budget.adapter.input.to.UserTO;
import br.com.personal.budget.auth.User;
import org.springframework.stereotype.Component;

@Component
public class AuthControllerMapper {

    public User mapToUser(UserSignUpTO dto) {
        return User.builder()
                .name(dto.name())
                .email(dto.email())
                .pwd(dto.pwd())
                .build();
    }

    public UserTO mapTO(User user) {
        return new UserTO(user.getName(), user.getEmail());
    }

}
