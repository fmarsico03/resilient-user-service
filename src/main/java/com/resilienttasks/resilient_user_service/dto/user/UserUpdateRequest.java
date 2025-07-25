package com.resilienttasks.resilient_user_service.dto.user;

import com.resilienttasks.resilient_user_service.entity.Rol;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequest {
    private String name;
    private String lastName;
    private String email;
    private String password;
    private Rol role;
}