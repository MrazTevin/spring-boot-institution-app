package com.ke.institutions.restApi;

import com.ke.institutions.Dto.JwtAuthenticationResponse;
import com.ke.institutions.Dto.SignInRequest;
import com.ke.institutions.Dto.SignUpRequest;
import com.ke.institutions.entity.Role;
import com.ke.institutions.entity.User;
import com.ke.institutions.respository.UserRepository;
import com.ke.institutions.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationRequest {


    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authenticationService.signup(signUpRequest));
    }


    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse>  signin(@RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(authenticationService.signin(signInRequest));
    }

}
