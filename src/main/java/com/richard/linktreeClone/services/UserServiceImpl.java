package com.richard.linktreeClone.services;

import com.richard.linktreeClone.dto.authDTO.RegisterDTO;
import com.richard.linktreeClone.dto.userDTO.UserDTO;
import com.richard.linktreeClone.entities.User;
import com.richard.linktreeClone.repositories.UserRepository;
import com.richard.linktreeClone.services.interfaces.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(RegisterDTO registerDTO) {
        Optional<User> user = userRepository.findByEmail(registerDTO.getEmail());
        if (user.isPresent()){
            throw new RuntimeException("User with email exist");
        }
        User newUser = User.builder()
                    .email(registerDTO.getEmail())
                    .password(registerDTO.getPassword())
                    .build();
        userRepository.save(newUser);
        return newUser;
    }

    @Override
    public User claimUsername(Long id, String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()){
            throw new RuntimeException("User with username already exist");
        }
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setUsername(username);
        userRepository.save(existingUser);
        return null;
    }

    @Override
    public User updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setProfileTitle(userDTO.getProfileTitle());
        existingUser.setBioDescription(userDTO.getBioDescription());
        return null;
    }
}
