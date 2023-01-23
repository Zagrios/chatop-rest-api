package com.chatop.chatopapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class RegisterRequest {

    @NotBlank(message = "email is mandatory")
    private String email;
    @NotBlank(message = "name is mandatory")
    private String name;
    @NotBlank(message = "password is mandatory")
    private String password;

}
