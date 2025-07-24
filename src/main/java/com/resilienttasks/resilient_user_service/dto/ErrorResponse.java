package com.resilienttasks.resilient_user_service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

@Getter
@Setter
@Builder
public class ErrorResponse {
    private String message;
    private String timestamp;
    private int status;
    private String error;
}