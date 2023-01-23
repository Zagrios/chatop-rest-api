package com.chatop.chatopapi.dto.request;

import lombok.Getter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class MessageRequest {

    @NotBlank(message = "message is mandatory")
    private String message;

    @NotNull(message = "user_id is mandatory")
    private Long user_id;

    @NotNull(message = "rental_id is mandatory")
    private Long rental_id;

}
