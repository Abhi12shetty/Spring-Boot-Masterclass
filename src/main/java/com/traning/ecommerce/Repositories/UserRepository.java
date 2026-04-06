package com.traning.ecommerce.Repositories;

import com.traning.ecommerce.DTOs.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
//Day 16: Custom User Authentication & Password Encoding
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
