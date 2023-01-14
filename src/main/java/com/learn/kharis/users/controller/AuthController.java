package com.learn.kharis.users.controller;

import com.learn.kharis.common.ApiResponse;
import com.learn.kharis.users.dto.request.ForgotPasswordRequest;
import com.learn.kharis.users.dto.request.SignInRequest;
import com.learn.kharis.users.dto.request.SignUpRequest;
import com.learn.kharis.users.dto.response.ForgotPasswordResponse;
import com.learn.kharis.users.dto.response.SignInResponse;
import com.learn.kharis.users.dto.response.SignUpResponse;
import com.learn.kharis.users.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("signup")
    public ApiResponse<SignUpResponse> register(@RequestBody SignUpRequest signUpRequest) {
        return ApiResponse.<SignUpResponse>builder()
                .code(HttpStatus.CREATED.value())
                .msg("Success SignUp")
                .data(authService.register(signUpRequest))
                .build();
    }

    @PostMapping("signin")
    public ApiResponse<SignInResponse> login(@RequestBody SignInRequest signInRequest) {
        return ApiResponse.<SignInResponse>builder()
                .code(HttpStatus.OK.value())
                .msg("Success SignIn")
                .data(authService.login(signInRequest))
                .build();
    }

    @PostMapping("forgot-password")
    public ApiResponse<ForgotPasswordResponse> forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        return ApiResponse.<ForgotPasswordResponse>builder()
                .code(HttpStatus.CREATED.value())
                .msg("Success Send Email")
                .data(authService.forgotPassword(forgotPasswordRequest))
                .build();
    }
}
