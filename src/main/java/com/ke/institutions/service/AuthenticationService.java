package com.ke.institutions.service;

import com.ke.institutions.Dto.JwtAuthenticationResponse;
import com.ke.institutions.Dto.SignInRequest;
import com.ke.institutions.Dto.SignUpRequest;
import com.ke.institutions.entity.User;

public interface AuthenticationService {

    User signup(SignUpRequest signUpRequest);

    JwtAuthenticationResponse signin(SignInRequest signinRequest);

}
