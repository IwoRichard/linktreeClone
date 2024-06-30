package com.richard.linktreeClone.services;

import com.richard.linktreeClone.dtos.UpdateProfileDto;
import com.richard.linktreeClone.entities.CustomLink;
import com.richard.linktreeClone.entities.SocialLink;
import com.richard.linktreeClone.entities.User;
import com.richard.linktreeClone.exceptions.ResourceNotFoundException;
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
    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User with ID - " + userId + " not found"));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new ResourceNotFoundException("User with username - " + username + " not found")
        );
    }


    @Override
    public User updateProfile(Long userId, UpdateProfileDto updateProfileDto) {
        User user = findById(userId);
        if (user != null){
            user.setProfileBio(updateProfileDto.getProfileBio());
            user.setProfileTitle(updateProfileDto.getProfileTitle());
            userRepository.save(user);
        }
        return user;
    }

    @Override
    public User updateSocialLink(Long userId, SocialLink socialLink) {
        User user = findById(userId);
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
        User user = findById(userId);
        if (user != null){
            customLink.setUser(user);
            user.getCustomLinks().add(customLink);
            customLinkRepository.save(customLink);
            userRepository.save(user);
        }
        return customLink;
    }

    @Override
    public String deleteCustomLink(Long userId, Long customLinkId) {
        User user = findById(userId);
        if (user != null){
            CustomLink customLink = customLinkRepository.findById(customLinkId).orElseThrow(
                    () -> new ResourceNotFoundException("custom link with ID - " + customLinkId + " not found"));

            if (customLink != null){
                if (customLink.getUser().equals(user)){
                    user.getCustomLinks().remove(customLink);
                    customLinkRepository.delete(customLink);
                    userRepository.save(user);
                }
            }
        }
        return "Deleted Successfully";
    }

}
