package com.richard.linktreeClone.controllers;

import com.richard.linktreeClone.dtos.RegisterDto;
import com.richard.linktreeClone.entities.User;
import com.richard.linktreeClone.services.interfaces.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

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
}
