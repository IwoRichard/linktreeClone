package com.richard.linktreeClone.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class CustomLinks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String linkTitle;
    private String urlLink;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
