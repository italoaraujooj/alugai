package com.alugai.demo.controllers;

import com.alugai.demo.payload.requests.LoginRequest;
import com.alugai.demo.payload.requests.SignupRequest;
import com.alugai.demo.payload.responses.SignupResponse;
import com.alugai.demo.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class AccessController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@Valid @RequestBody SignupRequest signUpRequest) {
        SignupResponse signupResponse;
        try {
            signupResponse = authService.createUser(signUpRequest);
        } catch (Exception e) { // TODO EXCEPTIONS
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok(signupResponse);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // TODO EXCEPTIONS

        return ResponseEntity.ok(authService.login(loginRequest, authentication));
    }


}
