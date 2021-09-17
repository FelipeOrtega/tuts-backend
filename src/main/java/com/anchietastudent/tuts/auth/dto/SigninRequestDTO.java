package com.anchietastudent.tuts.auth.dto;

public class SigninRequestDTO {


    private String email;
    private String password;

    public SigninRequestDTO() {}

    public SigninRequestDTO(String username, String password) {
        this.setEmail(username);
        this.setPassword(password);
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
