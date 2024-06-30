package com.richard.linktreeClone.services;

import com.richard.linktreeClone.dtos.RegisterDto;
import com.richard.linktreeClone.entities.EmailConfirmation;
import com.richard.linktreeClone.entities.User;
import com.richard.linktreeClone.exceptions.ResourceNotFoundException;
import com.richard.linktreeClone.exceptions.UserAlreadyExistsException;
import com.richard.linktreeClone.repositories.EmailConfirmationRepository;
import com.richard.linktreeClone.repositories.UserRepository;
import com.richard.linktreeClone.services.interfaces.AuthService;
import com.richard.linktreeClone.services.interfaces.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final EmailConfirmationRepository emailConfirmationRepository;
    @Override
    public User registerUser(RegisterDto register) {
        if (userRepository.findByUsername(register.getUsername()).isPresent()){
            throw new UserAlreadyExistsException("username already exists");
        }
        if (userRepository.findByEmail(register.getEmail()).isPresent()){
            throw new UserAlreadyExistsException("email already in use");
        }
        User user = User.builder()
                .username(register.getUsername())
                .email(register.getEmail())
                .createdDate(LocalDateTime.now())
                .isEnabled(false)
                .password(register.getPassword())
                .build();

        userRepository.save(user);

        EmailConfirmation emailConfirmation = EmailConfirmation.builder()
                .user(user)
                .token(UUID.randomUUID().toString())
                .createdDate(LocalDateTime.now())
                .build();

        emailConfirmationRepository.save(emailConfirmation);

        emailService.sendSimpleMailMessage(user.getUsername(), user.getEmail(), emailConfirmation.getToken());
        return user;
    }

    @Override
    public Boolean verifyToken(String token) {
        EmailConfirmation confirmation = emailConfirmationRepository.findByToken(token)
                .orElseThrow(() -> new ResourceNotFoundException("token not found"));

        User user = userRepository.findByEmail(confirmation.getUser().getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("user not found"));

        if (user != null){
            user.setEnabled(true);
            userRepository.save(user);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
