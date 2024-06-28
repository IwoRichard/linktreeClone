package com.richard.linktreeClone.services;

import com.richard.linktreeClone.dtos.RegisterDto;
import com.richard.linktreeClone.dtos.UpdateProfileDto;
import com.richard.linktreeClone.entities.CustomLink;
import com.richard.linktreeClone.entities.SocialLink;
import com.richard.linktreeClone.entities.User;
import com.richard.linktreeClone.repositories.CustomLinkRepository;
import com.richard.linktreeClone.repositories.SocialLinkRepository;
import com.richard.linktreeClone.repositories.UserRepository;
import com.richard.linktreeClone.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SocialLinkRepository socialLinkRepository;
    private final CustomLinkRepository customLinkRepository;

    @Override
    public User registerUser(RegisterDto register) {
        if (userRepository.findByUsername(register.getUsername()).isPresent()){
            throw new RuntimeException("username already exists");
        }
        if (userRepository.findByEmail(register.getEmail()).isPresent()){
            throw new RuntimeException("email already exists");
        }
        User user = User.builder()
                .username(register.getUsername())
                .email(register.getEmail())
                .password(register.getPassword())
                .build();

        userRepository.save(user);
        return user;
    }

    @Override
    public User updateProfile(Long userId, UpdateProfileDto updateProfileDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        if (user != null){
            user.setProfileBio(updateProfileDto.getProfileBio());
            user.setProfileTitle(updateProfileDto.getProfileTitle());
            userRepository.save(user);
        }
        return user;
    }

    @Override
    public User updateSocialLink(Long userId, SocialLink socialLink) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        if (user != null){
            SocialLink userSocialLink = user.getSocialLink();
            if (userSocialLink != null){
                userSocialLink.setLinkedinUrl(socialLink.getLinkedinUrl());
                userSocialLink.setTwitterUrl(socialLink.getTwitterUrl());
                userSocialLink.setGithubUrl(socialLink.getGithubUrl());
                socialLinkRepository.save(userSocialLink);
            }else {
                userSocialLink = socialLink;
                user.setSocialLink(userSocialLink);
            }
            userRepository.save(user);
        }
        return user;
    }

    @Override
    public CustomLink addCustomLink(Long userId, CustomLink customLink) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        if (user != null){
            customLink.setUser(user);
            user.getCustomLinks().add(customLink);
            customLinkRepository.save(customLink);
            userRepository.save(user);
        }
        return customLink;
    }

    /* TODO - Delete custom link*/
    /* TODO - Exceptions*/
    /* TODO - Validations*/
    /* TODO - Custom link to return user*/
    /* TODO - Security*/

}
