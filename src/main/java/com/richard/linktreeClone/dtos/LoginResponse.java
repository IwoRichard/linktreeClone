package com.richard.linktreeClone.dtos;

import com.richard.linktreeClone.entities.User;
import lombok.Data;

@Data
public class LoginResponse {

    private String accessToken;
    private User user;
}
