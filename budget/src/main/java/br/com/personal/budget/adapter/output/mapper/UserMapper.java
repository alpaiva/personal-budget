package br.com.personal.budget.adapter.output.mapper;

import br.com.personal.budget.adapter.output.entity.UserEntity;
import br.com.personal.budget.auth.User;
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

    public User mapToUser(UserEntity entity) {
        return User.builder()
                .name(entity.getName())
                .email(entity.getEmail())
                .pwd(entity.getPwd())
                .id(entity.getId())
                .build();
    }
}
