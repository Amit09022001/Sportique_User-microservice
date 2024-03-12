package com.hcltech.sportique.usermicroservice.repository;

import com.hcltech.sportique.usermicroservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    public Optional<User> findByEmailId(String emailId);
}
