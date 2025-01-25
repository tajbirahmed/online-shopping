package com.userservice.service;

import com.userservice.dto.UserLoginRequestDTO;
import com.userservice.dto.UserLoginResponseDTO;
import com.userservice.dto.UserRegisterRequestDTO;
import com.userservice.mapper.UserRegisterRequestDTOtoCustomUserMapper;
import com.userservice.model.CustomUser;
import com.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserRegisterRequestDTOtoCustomUserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public String register(UserRegisterRequestDTO user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        CustomUser customUser = mapper.userRegisterRequestDTOtoCustomUserMapper(user);
        customUser = userRepository.save(customUser);
        return "User registered successfully with id " + customUser.getId();
    }

    public UserLoginResponseDTO login(UserLoginRequestDTO user) throws Exception {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return new UserLoginResponseDTO(jwtService.generateToken(user));
        } else {
            throw new Exception("User not authenticated");
        }
    }
}
