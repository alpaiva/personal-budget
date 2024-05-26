package br.com.personal.budget.adapter.output.mapper;

import br.com.personal.budget.adapter.output.entity.UserEntity;
import br.com.personal.budget.core.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity mapToEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        userEntity.setName(user.getName());
        userEntity.setPwd(user.getPwd());
        return userEntity;
    }

    public User mapToUser(UserEntity save) {
        return User.builder()
                .name(save.getName())
                .email(save.getEmail())
                .pwd(save.getPwd())
                .build();
    }
}
