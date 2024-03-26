package com.alugai.demo.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserDTO {
    private String fullName;
    private String email;
    private String cpf;
    private String phoneNumber;
    private String password;
    private String confirmPassword;
}
