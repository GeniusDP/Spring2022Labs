package com.spring.lab2.dto.request;

import com.spring.lab2.entities.LotStatus;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class LotUpdateDto {
    @NotNull
    @Size(min = 5, max = 100, message = "length must be >=5 and <=100")
    private String lotName;

    @NotNull
    private LotStatus lotStatus;

    @NotNull
    @Min(value = 1, message = "user ID must be >= 1")
    private Integer userId;
}
