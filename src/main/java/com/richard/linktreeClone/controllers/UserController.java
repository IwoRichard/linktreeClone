package com.richard.linktreeClone.controllers;

import com.richard.linktreeClone.dtos.ChangePasswordDto;
import com.richard.linktreeClone.dtos.UpdateProfileDto;
import com.richard.linktreeClone.dtos.UserResponse;
import com.richard.linktreeClone.entities.CustomLink;
import com.richard.linktreeClone.entities.SocialLink;
import com.richard.linktreeClone.entities.User;
import com.richard.linktreeClone.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Var;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long userId){
        var user = userService.findById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/updateProfile/{userId}")
    public ResponseEntity<UserResponse> updateProfile(
            @PathVariable Long userId, @RequestBody UpdateProfileDto updateProfileDto){

        var user = userService.updateProfile(userId, updateProfileDto);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateSocial/{userId}")
    public ResponseEntity<UserResponse> updateSocial(
            @PathVariable Long userId, @RequestBody SocialLink socialLink){

        var user = userService.updateSocialLink(userId, socialLink);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    @PostMapping("/addCustomLink/{userId}")
    public ResponseEntity<CustomLink> addCustomLink(
            @PathVariable Long userId, @RequestBody CustomLink customLink){

        CustomLink link = userService.addCustomLink(userId, customLink);
        return new ResponseEntity<>(link, HttpStatus.CREATED);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<String> changePasswordResponseResponse(@RequestBody ChangePasswordDto changePasswordDto){
        userService.changePassword(changePasswordDto);
        return new ResponseEntity<>("Password changed successfully", HttpStatus.OK);
    }

    @DeleteMapping("{userId}/deleteCustomLink/{customLinkId}")
    public ResponseEntity<String> deleteCustomLink(
            @PathVariable Long userId, @PathVariable Long customLinkId){

        String link = userService.deleteCustomLink(userId, customLinkId);
        return new ResponseEntity<>(link, HttpStatus.OK);
    }
}
