package com.userservice.repository;

import com.userservice.model.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<CustomUser, String> {
    CustomUser findByUsername(String username);
}
