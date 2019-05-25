package com.example.userDetails.model;

public class SuccessResponseDTO {

    Boolean success;
    String token;
    String message;
    String email;

    public SuccessResponseDTO(Boolean success, String token, String message,String email) {
        this.success = success;
        this.token = token;
        this.message = message;
        this.email = email;
    }

    public SuccessResponseDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
