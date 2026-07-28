package com.gvp.unit4.service;

import com.gvp.unit4.dto.JwtResponse;
import com.gvp.unit4.dto.LoginRequest;
import com.gvp.unit4.dto.RegisterRequest;

public interface AuthService {
    void register(RegisterRequest request);
    JwtResponse login(LoginRequest request);
}
