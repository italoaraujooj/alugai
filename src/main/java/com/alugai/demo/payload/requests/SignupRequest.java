package com.alugai.demo.payload.requests;

import java.util.Set;

import jakarta.validation.constraints.*;
import lombok.*;


@Data
@Getter
@Setter
public class SignupRequest {
    private String username;
    private String fullName;
    private String email;
    private String cpf;
    private String phoneNumber;
    private String password;
    private String confirmPassword;
}
