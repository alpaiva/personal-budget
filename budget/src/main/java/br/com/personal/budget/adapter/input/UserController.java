package br.com.personal.budget.adapter.input;

import br.com.personal.budget.adapter.mapper.UserMapper;
import br.com.personal.budget.adapter.to.UserSignUpPwdTO;
import br.com.personal.budget.adapter.to.UserSignUpTO;
import br.com.personal.budget.core.User;
import br.com.personal.budget.usecase.UserUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    private final UserUseCase userUseCase;

    private final UserMapper userMapper;

    public UserController(UserUseCase userUseCase, UserMapper userMapper) {
        this.userUseCase = userUseCase;
        this.userMapper = userMapper;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserSignUpTO> getUser(@PathVariable(value = "id") Long id) {
        Optional<UserSignUpTO> user = userUseCase.findById(id);

        return user
                .map(userSignUpTO -> ResponseEntity.ok().body(userSignUpTO))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/user")
    public ResponseEntity<UserSignUpTO> post(@RequestBody(required = true) UserSignUpPwdTO userSignUpPwdTO) {

        User user = userMapper.map(userSignUpPwdTO);

        User save = userUseCase.save(user);

        UserSignUpTO signUpTO = userMapper.map(save);

        return ResponseEntity.status(HttpStatus.CREATED).body(signUpTO);
    }

}
