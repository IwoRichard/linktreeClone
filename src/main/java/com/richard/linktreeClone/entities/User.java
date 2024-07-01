package com.richard.linktreeClone.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String urlUsername;
    private String email;

    @JsonIgnore
    private String password;

    private boolean isEnabled;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private LocalDateTime createdDate;

    private String profileTitle;
    private String profileBio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "social_id", referencedColumnName = "id")
    private SocialLink socialLink;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CustomLink> customLinks;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @JsonIgnore
    @Transient
    private List<GrantedAuthority> authorities;
    @JsonIgnore
    @Transient
    private String username;
    @JsonIgnore
    @Transient
    private boolean accountNonLocked;
    @JsonIgnore
    @Transient
    private boolean accountNonExpired;
    @JsonIgnore
    @Transient
    private boolean credentialsNonExpired;
}
