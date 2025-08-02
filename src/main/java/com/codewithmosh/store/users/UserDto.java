package com.codewithmosh.store.users;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDto {
    @JsonProperty("user_id")
    private Long id;
    private String name;
    private String email;
    // @JsonFormat(pattern = "yy-MM-dd HH:mm:ss")
    // private LocalDateTime createdAt;
}
