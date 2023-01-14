package com.learn.kharis.users.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignInResponse {
    private String username;
    private String email;
    private String roles;
    private String token;
}
