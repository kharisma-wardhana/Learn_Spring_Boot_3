package com.learn.kharis.users.controller;

import com.learn.kharis.common.ApiResponse;
import com.learn.kharis.users.model.AppUser;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @GetMapping("/profile")
    public ApiResponse<AppUser> profile(@AuthenticationPrincipal AppUser appUser) {
        return ApiResponse.<AppUser>builder()
                .code(HttpStatus.OK.value())
                .msg("SUCCESS GET PROFILE")
                .data(appUser)
                .build();
    }
}
