package com.resilienttasks.resilient_user_service.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private String createdAt;
    private String updatedAt;
}