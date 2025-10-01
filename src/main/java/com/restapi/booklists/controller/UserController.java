package com.restapi.booklists.controller;

import com.restapi.booklists.dto.UserResponseDTO;
import com.restapi.booklists.entity.UserEntity;
import com.restapi.booklists.service.UserService;
import com.restapi.booklists.utility.JwtUtil;
import com.restapi.booklists.utility.bookConstant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
@Tag(name = "User", description = "User management APIs")
public class UserController implements bookConstant {


    private final UserService userService;

    private final JwtUtil jwtUtil;

    @Operation(summary = "Get Profile", description = "Get detail for user profile")
    @GetMapping("/profile")
    public ResponseEntity<UserResponseDTO> profile(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String email = jwtUtil.extractEmail(token);
        UserEntity user = userService.findByEmail(email).orElse(null);
        return ResponseEntity.ok().body(UserResponseDTO.toDTO(user));
    }

}
