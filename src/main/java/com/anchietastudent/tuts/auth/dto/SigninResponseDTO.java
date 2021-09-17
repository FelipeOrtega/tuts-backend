package com.anchietastudent.tuts.auth.dto;

import java.util.List;
import java.util.UUID;

public class SigninResponseDTO {

    private UUID id;
    private String name;
    private String email;
    private final String jwtToken;
    private List<String> roles;

    public SigninResponseDTO(UUID id, String name, String email, String jwtToken, List<String> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.jwtToken = jwtToken;
        this.roles = roles;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return this.jwtToken;
    }

    public List<String> getRoles() {
        return roles;
    }
}
