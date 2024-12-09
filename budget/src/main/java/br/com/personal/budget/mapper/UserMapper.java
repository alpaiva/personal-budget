package br.com.personal.budget.mapper;

import br.com.personal.budget.adapter.input.to.UserSignUpTO;
import br.com.personal.budget.adapter.input.to.UserTO;
import br.com.personal.budget.adapter.output.entity.UserEntity;
import br.com.personal.budget.auth.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

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

    public UserEntity mapToEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        userEntity.setName(user.getName());
        userEntity.setPwd(user.getPwd());
        return userEntity;
    }

    public User mapToUser(UserEntity entity) {
        return User.builder()
                .name(entity.getName())
                .email(entity.getEmail())
                .pwd(entity.getPwd())
                .id(entity.getId())
                .build();
    }
}
