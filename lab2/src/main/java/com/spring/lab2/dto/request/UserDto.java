package com.spring.lab2.dto.request;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDto {

    @NotNull(message = "must not be null")
    private String username;

    @NotNull(message = "must not be null")
    private String password;
}
