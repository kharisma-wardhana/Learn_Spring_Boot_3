package com.learn.kharis.users.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpResponse {
    private String username;
    private String email;
    private String role;
    private String token;
}
