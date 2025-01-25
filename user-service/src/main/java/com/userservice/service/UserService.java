package com.userservice.service;

import com.userservice.dto.UserRegisterRequestDTO;
import com.userservice.mapper.UserRegisterRequestDTOtoCustomUserMapper;
import com.userservice.model.CustomUser;
import com.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserRegisterRequestDTOtoCustomUserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public String register(UserRegisterRequestDTO user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        CustomUser customUser = mapper.userRegisterRequestDTOtoCustomUserMapper(user);
        customUser = userRepository.save(customUser);
        return "User registered successfully with id " + customUser.getId();
    }
}
