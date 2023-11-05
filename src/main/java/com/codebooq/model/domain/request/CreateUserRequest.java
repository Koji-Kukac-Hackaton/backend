package com.codebooq.model.domain.request;

import lombok.Getter;
import lombok.ToString;

@Getter
public class CreateUserRequest {

    private String email;
    private String password;
    private String firstName;
    private String lastName;

}
