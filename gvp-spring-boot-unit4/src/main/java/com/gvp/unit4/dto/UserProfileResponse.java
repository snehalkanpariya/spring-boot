package com.gvp.unit4.dto;

import java.util.Set;

public class UserProfileResponse {

    private String username;
    private Set<String> roles;
    private String authenticationType;

    public UserProfileResponse(String username, Set<String> roles, String authenticationType) {
        this.username = username;
        this.roles = roles;
        this.authenticationType = authenticationType;
    }

    public String getUsername() {
        return username;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public String getAuthenticationType() {
        return authenticationType;
    }
}