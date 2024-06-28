package com.richard.linktreeClone.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;

    @JsonIgnore
    private String password;

    private String profileTitle;
    private String profileBio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "social_id", referencedColumnName = "id")
    private SocialLink socialLink;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CustomLink> customLinks;
}
