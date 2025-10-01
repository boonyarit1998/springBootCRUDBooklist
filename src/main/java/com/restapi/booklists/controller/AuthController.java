package com.restapi.booklists.controller;

import com.restapi.booklists.dto.LoginRequestDTO;
import com.restapi.booklists.dto.LoginResponseDTO;
import com.restapi.booklists.dto.UserRequestDTO;
import com.restapi.booklists.entity.Role;
import com.restapi.booklists.entity.UserEntity;
import com.restapi.booklists.exception.ResourceNotFoundException;
import com.restapi.booklists.service.UserService;
import com.restapi.booklists.utility.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "Authen", description = "Authen management APIs")
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    @Operation(summary = "Register user", description = "Register new User")
    public ResponseEntity<String> register(@Valid @RequestBody UserRequestDTO request) {
        if (userService.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        userService.registerUser(user);
        return ResponseEntity.ok("Register success");
    }

    @PostMapping("/login")
    @Operation(summary = "Login", description = "User and Admin login")
    public ResponseEntity<LoginResponseDTO> login( @Valid @RequestBody LoginRequestDTO loginRequestDTO) {

        UserEntity user = userService.findByEmail(loginRequestDTO.getEmail()).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().build();
        }

        String token = jwtUtil.generateToken(user);
        LoginResponseDTO login = new LoginResponseDTO(token);
        return ResponseEntity.ok(login);
    }


}
