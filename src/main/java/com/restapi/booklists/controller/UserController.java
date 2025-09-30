package com.restapi.booklists.controller;

import com.restapi.booklists.dto.LoginRequestDTO;
import com.restapi.booklists.dto.UserResponseDTO;
import com.restapi.booklists.entity.UserEntity;
import com.restapi.booklists.dto.CommonResponse;
import com.restapi.booklists.dto.ErrorResponse;
import com.restapi.booklists.service.UserService;
import com.restapi.booklists.utility.JwtUtil;
import com.restapi.booklists.utility.bookConstant;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController implements bookConstant {


    private final UserService userService;

    private final JwtUtil jwtUtil;

    @GetMapping("/profile")
    public ResponseEntity<UserResponseDTO> profile(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String email = jwtUtil.extractEmail(token);
        UserEntity user = userService.findByEmail(email).orElse(null);
        return ResponseEntity.ok().body(UserResponseDTO.toDTO(user));
    }

}
