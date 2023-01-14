package com.learn.kharis.users.service;

import com.learn.kharis.users.dto.request.ForgotPasswordRequest;
import com.learn.kharis.users.dto.request.SignInRequest;
import com.learn.kharis.users.dto.request.SignUpRequest;
import com.learn.kharis.users.dto.response.ForgotPasswordResponse;
import com.learn.kharis.users.dto.response.SignInResponse;
import com.learn.kharis.users.dto.response.SignUpResponse;
import com.learn.kharis.users.model.AppUser;
import com.learn.kharis.users.model.Role;
import com.learn.kharis.users.repository.UserRepo;
import com.learn.kharis.utils.JWTUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authManager;

    private final JWTUtils jwtUtils;

    public SignUpResponse register(SignUpRequest signUpRequest) {
        AppUser user = AppUser.builder()
                .email(signUpRequest.getEmail())
                .encryptedPassword(passwordEncoder.encode(signUpRequest.getPassword()))
                .username(signUpRequest.getUsername())
                .role(Role.USER)
                .insertedAt(Date.from(Instant.now()))
                .updatedAt(Date.from(Instant.now()))
                .build();
        userRepo.save(user);

        String jwtToken = jwtUtils.generateToken(user);
        return SignUpResponse.builder()
                .token(jwtToken)
                .build();
    }

    public SignInResponse login(SignInRequest signInRequest) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequest.getUsername(),
                        signInRequest.getPassword()
                )
        );
        AppUser user = userRepo.findByUsername(signInRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String jwtToken = jwtUtils.generateToken(user);

        return SignInResponse.builder()
                .token(jwtToken)
                .build();
    }

    public ForgotPasswordResponse forgotPassword(ForgotPasswordRequest forgotPasswordRequest) {
        return ForgotPasswordResponse.builder()
                .build();
    }
}
