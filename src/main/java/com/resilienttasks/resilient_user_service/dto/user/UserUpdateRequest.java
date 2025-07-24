package com.resilienttasks.resilient_user_service.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequest {
    private String name;
    private String lastName;
}