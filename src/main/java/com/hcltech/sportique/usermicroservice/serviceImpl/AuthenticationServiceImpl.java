package com.hcltech.sportique.usermicroservice.serviceImpl;

import com.hcltech.sportique.usermicroservice.dto.JwtAuthenticationResponse;
import com.hcltech.sportique.usermicroservice.dto.RefreshTokenRequest;
import com.hcltech.sportique.usermicroservice.dto.SignInRequest;
import com.hcltech.sportique.usermicroservice.dto.SignUpRequest;
import com.hcltech.sportique.usermicroservice.entity.Role;
import com.hcltech.sportique.usermicroservice.entity.User;
import com.hcltech.sportique.usermicroservice.repository.UserRepository;
import com.hcltech.sportique.usermicroservice.service.AuthenticationService;
import com.hcltech.sportique.usermicroservice.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    @Override
    public User signup(SignUpRequest signUpRequest) {
       User user=new User();
        user.setEmailId(signUpRequest.getEmail());
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setMobileNumber(signUpRequest.getMobileNumber());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setDateOfBirth(signUpRequest.getDateOfBirth());
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    @Override
    public JwtAuthenticationResponse signIn(SignInRequest signinRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));
        User user = userRepository.findByEmailId(signinRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var userJwt = jwtService.generateToken(user);
        var userRefreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

        jwtAuthenticationResponse.setToken(userJwt);
        jwtAuthenticationResponse.setRefreshToken(userRefreshToken);
        jwtAuthenticationResponse.setId(user.getUserId());
        jwtAuthenticationResponse.setRole(user.getRole());
        return jwtAuthenticationResponse;
    }

    @Override
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user=userRepository.findByEmailId(userEmail).orElseThrow();
        if(jwtService.isTokenValid(refreshTokenRequest.getToken(), user)){
            var jwt=jwtService.generateToken(user);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            jwtAuthenticationResponse.setId(user.getUserId());
            jwtAuthenticationResponse.setRole(user.getRole());
            return jwtAuthenticationResponse;
        }
        return null;
    }
}
