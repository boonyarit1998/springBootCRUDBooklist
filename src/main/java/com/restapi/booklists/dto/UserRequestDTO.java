package com.restapi.booklists.dto;

import com.restapi.booklists.entity.Role;
import com.restapi.booklists.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    private String username;
    private String email;
    private String password;

    public static UserEntity toEntity(UserRequestDTO request){
        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return  user;
    }

}
