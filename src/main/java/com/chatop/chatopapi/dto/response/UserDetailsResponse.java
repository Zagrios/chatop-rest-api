package com.chatop.chatopapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Builder
public class UserDetailsResponse {

    private Long id;
    private String name;
    private String email;
    private Date created_at;
    private Date updated_at;

}
