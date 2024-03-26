package com.alugai.demo.payload.responses;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignupResponse {
    private Long id;
    private String username;
    private String fullName;
    private String email;
    private String cpf;
    private String phoneNumber;
    private String password;
}
