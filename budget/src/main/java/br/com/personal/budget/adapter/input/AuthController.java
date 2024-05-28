package br.com.personal.budget.adapter.input;

import br.com.personal.budget.adapter.input.mapper.AuthControllerMapper;
import br.com.personal.budget.adapter.input.to.UserPwdTO;
import br.com.personal.budget.adapter.input.to.UserSignUpTO;
import br.com.personal.budget.adapter.input.to.UserTO;
import br.com.personal.budget.auth.JwtService;
import br.com.personal.budget.auth.User;
import br.com.personal.budget.auth.UserInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthControllerMapper mapper;
    private final AuthenticationManager authenticationManager;
    private final UserInfoService userInfoService;
    private final JwtService jwtService;

    public AuthController(
            AuthControllerMapper mapper,
            AuthenticationManager authenticationManager,
            UserInfoService userInfoService,
            JwtService jwtService) {
        this.mapper = mapper;
        this.authenticationManager = authenticationManager;
        this.userInfoService = userInfoService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody(required = true) UserPwdTO userPwdTO) {

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userPwdTO.email(), userPwdTO.pwd()));

        if (authenticate.isAuthenticated()) {
            String token = jwtService.generateToken(userPwdTO.email());
            return ResponseEntity.ok().body(token);
        }

        throw new UsernameNotFoundException("Invalid user");
    }

    @PostMapping("/signup")
    public ResponseEntity<UserTO> signUp(@RequestBody(required = true) UserSignUpTO userSignUpTO) {

        User user = mapper.mapToUser(userSignUpTO);

        User added = userInfoService.add(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.mapTO(added));
    }
}
