package com.richard.linktreeClone.services.interfaces;

import com.richard.linktreeClone.dto.authDTO.RegisterDTO;
import com.richard.linktreeClone.dto.userDTO.UserDTO;
import com.richard.linktreeClone.entities.User;

public interface UserService {

    User registerUser(RegisterDTO registerDTO);

    User claimUsername(Long id, String username);

    User updateUser(Long id, UserDTO userDTO);
}
