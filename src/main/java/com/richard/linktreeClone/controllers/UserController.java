package com.richard.linktreeClone.controllers;

import com.richard.linktreeClone.dtos.RegisterDto;
import com.richard.linktreeClone.dtos.UpdateProfileDto;
import com.richard.linktreeClone.entities.CustomLink;
import com.richard.linktreeClone.entities.SocialLink;
import com.richard.linktreeClone.entities.User;
import com.richard.linktreeClone.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody RegisterDto registerDto){
        User user = userService.registerUser(registerDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/updateprofile/{id}")
    public ResponseEntity<User> updateProfile(@PathVariable Long id, @RequestBody UpdateProfileDto updateProfileDto){
        User user = userService.updateProfile(id, updateProfileDto);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    @PutMapping("/updatesocial/{id}")
    public ResponseEntity<User> updateSocial(@PathVariable Long id, @RequestBody SocialLink socialLink){
        User user = userService.updateSocialLink(id, socialLink);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    @PostMapping("/addcustomlink/{id}")
    public ResponseEntity<CustomLink> registerUser(@PathVariable Long id, @RequestBody CustomLink customLink){
        CustomLink link = userService.addCustomLink(id, customLink);
        return new ResponseEntity<>(link, HttpStatus.CREATED);
    }
}
