package com.gvp.unit4.service;

import com.gvp.unit4.dto.JwtResponse;
import com.gvp.unit4.dto.LoginRequest;
import com.gvp.unit4.dto.RegisterRequest;
import com.gvp.unit4.exception.DuplicateResourceException;
import com.gvp.unit4.model.Role;
import com.gvp.unit4.model.User;
import com.gvp.unit4.repository.UserRepository;
import com.gvp.unit4.security.jwt.JwtService;
import java.util.EnumSet;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public void register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateResourceException("Username '" + request.getUsername() + "' is already taken");
        }
        String hash = passwordEncoder.encode(request.getPassword());
        User user = new User(null, request.getUsername(), hash, EnumSet.of(Role.ROLE_USER));
        userRepository.save(user);
        log.info("Registered new user '{}' with role ROLE_USER", user.getUsername());
    }

    @Override
    public JwtResponse login(LoginRequest request) {
        // Throws BadCredentialsException (mapped to 401 by GlobalExceptionHandler) on bad username/password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(principal);
        var roles = principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());

        log.info("Issued JWT for user '{}'", principal.getUsername());
        return new JwtResponse(token, principal.getUsername(), roles, jwtService.getExpirationMs());
    }
}