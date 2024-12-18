package com.richard.linktreeClone.dtos;

import com.richard.linktreeClone.entities.CustomLink;
import com.richard.linktreeClone.entities.SocialLink;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class UserResponse {
    private Long id;
    private String urlUsername;
    private String email;
    private boolean isEnabled;
    private Date createdDate;
    private String profileTitle;
    private String profileBio;
    private SocialLink socialLink;
    private List<CustomLink> customLinks;
}
