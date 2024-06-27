package com.richard.linktreeClone.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;

    private String email;
    private String username;
    private String profileTitle;
    private String bioDescription;

    @JsonIgnore
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private SocialLinks socialLinks;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CustomLinks> customLinks;
}
