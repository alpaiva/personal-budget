package br.com.personal.budget.adapter.input;

import br.com.personal.budget.adapter.input.mapper.AuthenticationMapper;
import br.com.personal.budget.adapter.input.to.UserPwdTO;
import br.com.personal.budget.adapter.input.to.UserSignUpTO;
import br.com.personal.budget.adapter.input.to.UserTO;
import br.com.personal.budget.auth.AuthenticationService;
import br.com.personal.budget.core.domain.User;
import br.com.personal.budget.core.usecase.exception.SignInException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private final AuthenticationMapper mapper;

    public AuthenticationController(AuthenticationService authenticationService,
                                    AuthenticationMapper mapper) {
        this.authenticationService = authenticationService;
        this.mapper = mapper;
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody(required = true) UserPwdTO userPwdTO) {
        String token;
        try {
            token = authenticationService.signIn(userPwdTO.email(), userPwdTO.pwd());
        } catch (SignInException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserTO> signUp(@RequestBody(required = true)
                                         UserSignUpTO userSignUpTO) {

        User user = mapper.mapToUser(userSignUpTO);

        User userSaved = authenticationService.signUp(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.mapToDTO(userSaved));
    }
}
