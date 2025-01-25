package com.userservice.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserRegisterRequestDTO {
    private String username;
    private String password;
    private String role;
}
