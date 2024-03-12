package com.hcltech.sportique.usermicroservice.dto;

import com.hcltech.sportique.usermicroservice.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthenticationResponse {
    private String token;
    private String refreshToken;
    private Role role;
    private Integer id;
}
