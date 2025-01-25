package com.userservice.mapper;


import com.userservice.dto.UserRegisterRequestDTO;
import com.userservice.model.CustomUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserRegisterRequestDTOtoCustomUserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "password", source = "user.password")
    @Mapping(target = "role", source = "user.role")
    public CustomUser userRegisterRequestDTOtoCustomUserMapper(UserRegisterRequestDTO user);
}
