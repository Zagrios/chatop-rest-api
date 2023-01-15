package com.chatop.chatopapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

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
