package com.richard.linktreeClone.controllers;

import com.richard.linktreeClone.dtos.LoginDto;
import com.richard.linktreeClone.dtos.LoginResponse;
import com.richard.linktreeClone.dtos.RegisterDto;
import com.richard.linktreeClone.entities.User;
import com.richard.linktreeClone.services.JwtService;
import com.richard.linktreeClone.services.interfaces.AuthService;
import com.richard.linktreeClone.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final JwtService jwtService;
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody RegisterDto registerDto){
        User user = authService.registerUser(registerDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Boolean> confirmUserAccount(@RequestParam("token") String token){
        Boolean isSuccess = authService.verifyToken(token);
        return new ResponseEntity<>(isSuccess, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> findByUsername(@PathVariable String username){
        User user = userService.findByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginDto loginDto) {
        User authenticatedUser = authService.loginUser(loginDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setUser(authenticatedUser);

        return ResponseEntity.ok(loginResponse);
    }
}
