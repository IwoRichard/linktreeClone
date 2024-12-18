package com.richard.linktreeClone.services.interfaces;


import com.richard.linktreeClone.dtos.ChangePasswordDto;
import com.richard.linktreeClone.dtos.UpdateProfileDto;
import com.richard.linktreeClone.dtos.UserResponse;
import com.richard.linktreeClone.entities.CustomLink;
import com.richard.linktreeClone.entities.SocialLink;

public interface UserService {

    UserResponse updateProfile(Long userId, UpdateProfileDto updateProfileDto);

    UserResponse updateSocialLink(Long userId, SocialLink socialLink);

    CustomLink addCustomLink(Long userId, CustomLink customLink);

    String deleteCustomLink(Long userId, Long customLinkId);

    UserResponse findById(Long userId);

    UserResponse findByUsername(String username);

    void changePassword(ChangePasswordDto changePasswordDto);
}
