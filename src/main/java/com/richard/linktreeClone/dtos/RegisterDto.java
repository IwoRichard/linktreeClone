package com.richard.linktreeClone.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class RegisterDto {

    private String username;
    private String email;
    private String password;
}
