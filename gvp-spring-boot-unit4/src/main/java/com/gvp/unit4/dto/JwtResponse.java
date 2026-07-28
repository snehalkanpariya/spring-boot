package com.gvp.unit4.dto;

import java.util.Set;

public class JwtResponse {

    private String token;
    private String tokenType = "Bearer";
    private String username;
    private Set<String> roles;
    private long expiresInMs;

    public JwtResponse(String token, String username, Set<String> roles, long expiresInMs) {
        this.token = token;
        this.username = username;
        this.roles = roles;
        this.expiresInMs = expiresInMs;
    }

    public String getToken() {
        return token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getUsername() {
        return username;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public long getExpiresInMs() {
        return expiresInMs;
    }
}