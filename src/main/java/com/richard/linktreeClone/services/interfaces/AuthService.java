package com.richard.linktreeClone.services.interfaces;

import com.richard.linktreeClone.dtos.RegisterDto;
import com.richard.linktreeClone.entities.User;

public interface AuthService {

    User registerUser(RegisterDto registerDto);

    Boolean verifyToken(String token);
}
