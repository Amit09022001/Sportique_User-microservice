package com.hcltech.sportique.usermicroservice.controller;

import com.hcltech.sportique.usermicroservice.dto.JwtAuthenticationResponse;
import com.hcltech.sportique.usermicroservice.dto.RefreshTokenRequest;
import com.hcltech.sportique.usermicroservice.dto.SignInRequest;
import com.hcltech.sportique.usermicroservice.dto.SignUpRequest;
import com.hcltech.sportique.usermicroservice.entity.User;
import com.hcltech.sportique.usermicroservice.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> SignIn(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.signIn(signInRequest));
    }
    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.refreshToken(refreshTokenRequest));
    }
    @PostMapping("/user/signup")
    public ResponseEntity<User> signUp(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.signup(signUpRequest));
    }
    @PostMapping("/user/refresh")
    public ResponseEntity<JwtAuthenticationResponse> userRefresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }

}
