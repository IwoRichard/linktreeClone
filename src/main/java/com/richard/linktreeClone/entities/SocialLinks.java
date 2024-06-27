package com.richard.linktreeClone.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class SocialLinks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String userEmail;
    private String twitterUrl;
    private String facebookUrl;
    private String instagramUrl;
    private String linkedInUrl;
    private String githubUrl;
    private String tiktokUrl;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public void setUserEmail(String userEmail) {
        this.userEmail = user.getEmail();
    }
}
