package com.resilienttasks.resilient_user_service.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String name;
    private String lastName;
    private String email;
    private String password;
}