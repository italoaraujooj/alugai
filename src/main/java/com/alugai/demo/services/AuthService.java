package com.alugai.demo.services;

import com.alugai.demo.models.MyUser;
import com.alugai.demo.payload.requests.LoginRequest;
import com.alugai.demo.payload.requests.SignupRequest;
import com.alugai.demo.payload.responses.JwtResponse;
import com.alugai.demo.payload.responses.SignupResponse;
import com.alugai.demo.repositories.MyUserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    TokenService tokenService;

    @Autowired
    private MyUserRepository myUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtEncoder jwtEncoder;

    public SignupResponse createUser(SignupRequest signupRequest) throws Exception{
        if (myUserRepository.existsByUsername(signupRequest.getUsername())) {
            throw new Exception("Username already in use.");
        }

        if (myUserRepository.existsByEmail(signupRequest.getEmail())) {
            throw new Exception("Email already in use.");
        }
        if (!signupRequest.getPassword().equals(signupRequest.getConfirmPassword())){
            throw new Exception("Passwords don't match.");
        }

        MyUser newUser = new MyUser();
        newUser.setUsername(signupRequest.getUsername());
        newUser.setFullName(signupRequest.getFullName());
        newUser.setEmail(signupRequest.getEmail());
        newUser.setCpf(signupRequest.getCpf());
        newUser.setPhone(signupRequest.getPhoneNumber());
        newUser.setPassword("{bcrypt}"+ passwordEncoder.encode(signupRequest.getPassword()));
        newUser.setRole("USER");
        myUserRepository.save(newUser);
        return new SignupResponse(newUser.getId(), newUser.getUsername(), newUser.getFullName(), newUser.getEmail(), newUser.getCpf(), newUser.getPhone(), newUser.getPassword());
    }

    public JwtResponse login(LoginRequest loginRequest, Authentication authentication){
        String jwt = tokenService.generateJwt(authentication, jwtEncoder);
        System.err.println(jwt);
        return new JwtResponse(jwt, "Bearer",
                loginRequest.getUsername(),
                myUserRepository.findByUsername(loginRequest.getUsername()).get().getEmail(),  "USER");
    }
}
