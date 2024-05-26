package br.com.personal.budget.adapter.output;

import br.com.personal.budget.adapter.output.entity.UserEntity;
import br.com.personal.budget.adapter.output.mapper.UserMapper;
import br.com.personal.budget.core.domain.User;
import br.com.personal.budget.core.usecase.port.UserPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserAdapter implements UserPort {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserAdapter(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User save(User user) {

        UserEntity userEntity = userMapper.mapToEntity(user);

        UserEntity save = userRepository.save(userEntity);

        return userMapper.mapToUser(save);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::mapToUser);

    }

    @Override
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId)
                .map(userMapper::mapToUser);
    }
}
