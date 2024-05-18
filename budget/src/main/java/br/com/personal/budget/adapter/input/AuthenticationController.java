package br.com.personal.budget.adapter.input;

import br.com.personal.budget.adapter.to.UserSignInTO;
import br.com.personal.budget.adapter.to.UserSignUpPwdTO;
import br.com.personal.budget.adapter.to.UserSignUpTO;
import br.com.personal.budget.auth.AuthenticationService;
import br.com.personal.budget.usecase.exception.SignInException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody(required = true) UserSignInTO userSignInTO) {
        String token;
        try {
            token = authenticationService.signIn(userSignInTO.email(), userSignInTO.pwd());
        } catch (SignInException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserSignUpTO> signUp(@RequestBody(required = true)
                                               UserSignUpPwdTO userSignUpPwdTO) {

        authenticationService.signUp(userSignUpPwdTO.name(),
                userSignUpPwdTO.name(),
                userSignUpPwdTO.pwd());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new UserSignUpTO(userSignUpPwdTO.name(), userSignUpPwdTO.email()));
    }
}
