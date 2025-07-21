package com.codewithmosh.store.dtos;

import lombok.Data;

@Data // this is the combination of getter setter to string and much more
public class RegisterUserRequest {
    private String name;
    private String email;
    private String password;    
}
