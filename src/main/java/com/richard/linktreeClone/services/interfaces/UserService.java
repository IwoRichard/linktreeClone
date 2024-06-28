package com.richard.linktreeClone.services.interfaces;

import com.richard.linktreeClone.dtos.RegisterDto;
import com.richard.linktreeClone.dtos.UpdateProfileDto;
import com.richard.linktreeClone.entities.CustomLink;
import com.richard.linktreeClone.entities.SocialLink;
import com.richard.linktreeClone.entities.User;

public interface UserService {

    User registerUser(RegisterDto registerDto);

    User updateProfile(Long userId, UpdateProfileDto updateProfileDto);

    User updateSocialLink(Long userId, SocialLink socialLink);

    CustomLink addCustomLink(Long userId, CustomLink customLink);
}
