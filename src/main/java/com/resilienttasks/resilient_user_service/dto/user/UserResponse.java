package com.resilienttasks.resilient_user_service.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder    
public class UserResponse {
    private String id;
    private String name;
    private String lastName;
    private String email;
    private String role;
    private String createdAt;
    private String updatedAt;
}