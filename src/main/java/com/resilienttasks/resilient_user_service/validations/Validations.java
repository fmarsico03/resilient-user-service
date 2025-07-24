package com.resilienttasks.resilient_user_service.validations;

import com.resilienttasks.resilient_user_service.entity.User;
import com.resilienttasks.resilient_user_service.exception.BadRequestException;
import com.resilienttasks.resilient_user_service.exception.ForbiddenException;
import com.resilienttasks.resilient_user_service.exception.NotFoundException;



public class Validations {

    public static void validatePassword(String email, String requierd, String actual) {
        if (!actual.equals(requierd)) {
            throw new ForbiddenException("Invalid password for user: " + email);
        }
    }

    public static void validateEmail(User user) {
        if (user != null) {
            throw new BadRequestException("User already exists with email: " + user.getEmail());
        }
    }

    public static void notFoundUser (User user, String message) {
        if (user == null) {
            throw new NotFoundException("User not found with " + message);
        }
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message + " cannot be null");
        }
    }
}