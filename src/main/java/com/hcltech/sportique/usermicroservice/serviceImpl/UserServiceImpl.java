package com.hcltech.sportique.usermicroservice.serviceImpl;

import com.hcltech.sportique.usermicroservice.repository.UserRepository;
import com.hcltech.sportique.usermicroservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByEmailId(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User NOt Found"));
            }
        };
    }
}
