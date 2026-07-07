package com.example.jwt.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.jwt.entity.user;

public interface UserRepository extends JpaRepository<user, Long> 
{
    Optional<user> findByEmail(String email);    
}
