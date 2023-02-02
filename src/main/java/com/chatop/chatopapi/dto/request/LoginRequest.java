package com.chatop.chatopapi.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class LoginRequest {
    @NotBlank(message = "email is mandatory")
    private String email;
    @NotBlank(message = "password is mandatory")
    private String password;
}
