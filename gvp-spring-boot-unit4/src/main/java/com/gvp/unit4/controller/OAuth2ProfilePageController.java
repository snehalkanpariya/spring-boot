package com.gvp.unit4.controller;

import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuth2ProfilePageController {

    @GetMapping(value = "/profile", produces = MediaType.TEXT_HTML_VALUE)
    public String profilePage(@AuthenticationPrincipal OAuth2User principal) {
        String name = principal.getAttribute("name");
        String email = principal.getAttribute("email");
        String picture = principal.getAttribute("picture");
        return """
                <html><body>
                <img src="%s">
                <h1>Welcome, %s!</h1>
                <p>%s</p>
                </body></html>
                """.formatted(picture, name, email);
    }
}