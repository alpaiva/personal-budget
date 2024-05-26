package br.com.personal.budget.adapter.input.mapper;

import br.com.personal.budget.adapter.input.to.UserSignUpTO;
import br.com.personal.budget.adapter.input.to.UserTO;
import br.com.personal.budget.core.domain.User;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationMapper {

    public User mapToUser(UserSignUpTO dto) {
        return User.builder()
                .name(dto.name())
                .email(dto.email())
                .pwd(dto.pwd())
                .build();
    }

    public UserTO mapToDTO(User userSaved) {

        return new UserTO(userSaved.getName(), userSaved.getEmail());
    }
}
