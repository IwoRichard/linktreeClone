package com.richard.linktreeClone.services.interfaces;


import com.richard.linktreeClone.dtos.ChangePasswordDto;
import com.richard.linktreeClone.dtos.UpdateProfileDto;
import com.richard.linktreeClone.entities.CustomLink;
import com.richard.linktreeClone.entities.SocialLink;
import com.richard.linktreeClone.entities.User;

public interface UserService {

    User updateProfile(Long userId, UpdateProfileDto updateProfileDto);

    User updateSocialLink(Long userId, SocialLink socialLink);

    CustomLink addCustomLink(Long userId, CustomLink customLink);

    String deleteCustomLink(Long userId, Long customLinkId);

    User findById(Long userId);

    User findByUsername(String username);

    void changePassword(ChangePasswordDto changePasswordDto);
}
