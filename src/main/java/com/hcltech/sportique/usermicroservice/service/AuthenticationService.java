package com.hcltech.sportique.usermicroservice.service;

import com.hcltech.sportique.usermicroservice.dto.JwtAuthenticationResponse;
import com.hcltech.sportique.usermicroservice.dto.RefreshTokenRequest;
import com.hcltech.sportique.usermicroservice.dto.SignInRequest;
import com.hcltech.sportique.usermicroservice.dto.SignUpRequest;
import com.hcltech.sportique.usermicroservice.entity.User;

public interface AuthenticationService {
    User signup(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signIn(SignInRequest signinRequest);
    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
