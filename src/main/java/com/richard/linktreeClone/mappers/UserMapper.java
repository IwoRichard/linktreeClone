package com.richard.linktreeClone.mappers;

import com.richard.linktreeClone.dtos.UserResponse;
import com.richard.linktreeClone.entities.User;

public class UserMapper {
    public static UserResponse toUserResponse(User user) {
        if (user == null) {
            return null;
        }

        return UserResponse.builder()
                .id(user.getId())
                .urlUsername(user.getUrlUsername())
                .email(user.getEmail())
                .isEnabled(user.isEnabled())
                .createdDate(user.getCreatedDate())
                .profileTitle(user.getProfileTitle())
                .profileBio(user.getProfileBio())
                .socialLink(user.getSocialLink())
                .customLinks(user.getCustomLinks())
                .build();
    }
}
