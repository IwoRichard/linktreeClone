package com.richard.linktreeClone.services;

import com.richard.linktreeClone.dtos.ChangePasswordDto;
import com.richard.linktreeClone.dtos.UpdateProfileDto;
import com.richard.linktreeClone.dtos.UserResponse;
import com.richard.linktreeClone.entities.CustomLink;
import com.richard.linktreeClone.entities.SocialLink;
import com.richard.linktreeClone.entities.User;
import com.richard.linktreeClone.exceptions.ResourceNotFoundException;
import com.richard.linktreeClone.repositories.CustomLinkRepository;
import com.richard.linktreeClone.repositories.SocialLinkRepository;
import com.richard.linktreeClone.repositories.UserRepository;
import com.richard.linktreeClone.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import static com.richard.linktreeClone.mappers.UserMapper.toUserResponse;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SocialLinkRepository socialLinkRepository;
    private final CustomLinkRepository customLinkRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse findById(Long userId) {
        User user =  userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User with ID - " + userId + " not found"));

        return toUserResponse(user);
    }

    @Override
    public UserResponse findByUsername(String username) {
        User user =  userRepository.findByUrlUsername(username).orElseThrow(
                () -> new ResourceNotFoundException("User with username - " + username + " not found")
        );
        return toUserResponse(user);
    }

    @Override
    public void changePassword(ChangePasswordDto changePasswordDto) {
        User user = userRepository.findByEmail(changePasswordDto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User with email doesn't exists"));
        if (!passwordEncoder.matches(changePasswordDto.getOldPassword(), user.getPassword())){
            throw new ResourceNotFoundException("Old password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
        userRepository.save(user);
    }


    @Override
    public UserResponse updateProfile(Long userId, UpdateProfileDto updateProfileDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID - " + userId + " not found"));
        if (user != null){
            user.setProfileBio(updateProfileDto.getProfileBio());
            user.setProfileTitle(updateProfileDto.getProfileTitle());
            userRepository.save(user);
        }
        return toUserResponse(user);
    }

    @Override
    public UserResponse updateSocialLink(Long userId, SocialLink socialLink) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID - " + userId + " not found"));
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
        return toUserResponse(user);
    }

    @Override
    public CustomLink addCustomLink(Long userId, CustomLink customLink) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID - " + userId + " not found"));
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
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID - " + userId + " not found"));
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
