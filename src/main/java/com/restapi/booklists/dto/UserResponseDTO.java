package com.restapi.booklists.dto;

import com.restapi.booklists.entity.Role;
import com.restapi.booklists.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private String username;
    private String email;
    private String role;

    public static UserResponseDTO toDTO(UserEntity entity){
        UserResponseDTO user = new UserResponseDTO();
        user.setUsername(entity.getUsername());
        user.setEmail(entity.getEmail());
        user.setRole(entity.getRole().name());
        return  user;
    }
}
