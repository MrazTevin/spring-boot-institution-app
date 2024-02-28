package com.ke.institutions.service.impl;


import com.ke.institutions.Dto.JwtAuthenticationResponse;
import com.ke.institutions.Dto.SignInRequest;
import com.ke.institutions.Dto.SignUpRequest;
import com.ke.institutions.entity.Role;
import com.ke.institutions.entity.User;
import com.ke.institutions.respository.UserRepository;
import com.ke.institutions.service.AuthenticationService;
import com.ke.institutions.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor

public class AuthenticationServiceImpl implements AuthenticationService {

        private final UserRepository userRepository;

        private final PasswordEncoder passwordEncoder;

        private final AuthenticationManager authenticationManager;

        private final JwtService jwtService;

        public User signup(SignUpRequest signUpRequest) {

                System.out.println("First name: " + signUpRequest.getFirstname());
                System.out.println("Last name: " + signUpRequest.getLastname());

                User user = new User();

                user.setEmail(signUpRequest.getEmail());
                user.setFirstname(signUpRequest.getFirstname());
                user.setLastname(signUpRequest.getLastname());
                user.setRole(Role.USER);
                user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

                return userRepository.save(user);
        }

        public JwtAuthenticationResponse signin(SignInRequest signinRequest) {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(),
                        signinRequest.getPassword()));

                var user = userRepository.findByEmail(signinRequest.getEmail()).orElseThrow( () -> new IllegalArgumentException("email or password is invalid"));

                var jwt = jwtService.generateToken(user);
                var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

                JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

                jwtAuthenticationResponse.setToken(jwt);
                jwtAuthenticationResponse.setRefreshToken(refreshToken);

                return jwtAuthenticationResponse;
        }

}
