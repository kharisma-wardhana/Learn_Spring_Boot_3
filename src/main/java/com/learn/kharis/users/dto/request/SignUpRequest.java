package com.learn.kharis.users.dto.request;

import lombok.Data;

@Data
public class SignUpRequest {
    private String email;
    private String password;
    private String username;
}
