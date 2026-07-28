package com.gvp.unit4.controller;

import com.gvp.unit4.dto.ApiResponse;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/oauth2")
public class OAuth2ProfileController {

    @GetMapping("/me")
    public ApiResponse<Map<String, Object>> me(@AuthenticationPrincipal OAuth2User principal) {
        Map<String, Object> profile = new LinkedHashMap<>();
        profile.put("name", principal.getAttribute("name"));
        profile.put("email", principal.getAttribute("email"));
        profile.put("picture", principal.getAttribute("picture"));
        profile.put("authenticationType", "OAuth2 (Google)");
        return ApiResponse.ok("Logged in via Google OAuth2", profile);
    }
}