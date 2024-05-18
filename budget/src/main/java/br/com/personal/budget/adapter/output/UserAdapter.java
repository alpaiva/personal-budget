package br.com.personal.budget.adapter.output;

import br.com.personal.budget.adapter.mapper.UserMapper;
import br.com.personal.budget.adapter.to.UserSignUpTO;
import br.com.personal.budget.core.User;
import br.com.personal.budget.database.UserEntity;
import br.com.personal.budget.usecase.port.UserPort;
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
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        userEntity.setName(user.getName());
        userEntity.setPwd(user.getPwdEncode());
        userRepository.save(userEntity);
        return user;
    }

    @Override
    public Optional<UserSignUpTO> findById(Long id) {

        Optional<UserEntity> userEntity = userRepository.findById(id);
        return userEntity.map(userMapper::map);
    }

    @Override
    public Optional<UserSignUpTO> findByEmail(String email) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);

        return userEntity.map(userMapper::map);
    }
}
