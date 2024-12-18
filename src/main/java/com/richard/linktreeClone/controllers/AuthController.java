package com.richard.linktreeClone.controllers;

import com.richard.linktreeClone.dtos.LoginDto;
import com.richard.linktreeClone.dtos.LoginResponse;
import com.richard.linktreeClone.dtos.RegisterDto;
import com.richard.linktreeClone.dtos.UserResponse;
import com.richard.linktreeClone.entities.User;
import com.richard.linktreeClone.services.JwtService;
import com.richard.linktreeClone.services.interfaces.AuthService;
import com.richard.linktreeClone.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.richard.linktreeClone.mappers.UserMapper.toUserResponse;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final JwtService jwtService;
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody RegisterDto registerDto){
        var user = authService.registerUser(registerDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Boolean> confirmUserAccount(@RequestParam("token") String token){
        Boolean isSuccess = authService.verifyToken(token);
        return new ResponseEntity<>(isSuccess, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserResponse> findByUsername(@PathVariable String username){
        UserResponse user = userService.findByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginDto loginDto) {
        User authenticatedUser = authService.loginUser(loginDto);
        UserResponse response = toUserResponse(authenticatedUser);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setAccessToken(jwtToken);
        loginResponse.setUser(response);

        return ResponseEntity.ok(loginResponse);
    }
}
