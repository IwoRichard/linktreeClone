package com.richard.linktreeClone.services.interfaces;

import com.richard.linktreeClone.dtos.LoginDto;
import com.richard.linktreeClone.dtos.RegisterDto;
import com.richard.linktreeClone.dtos.UserResponse;
import com.richard.linktreeClone.entities.User;

public interface AuthService {

    UserResponse registerUser(RegisterDto registerDto);

    Boolean verifyToken(String token);

    User loginUser(LoginDto loginDto);
}
