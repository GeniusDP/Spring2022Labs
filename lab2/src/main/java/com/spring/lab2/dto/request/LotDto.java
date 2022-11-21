package com.spring.lab2.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class LotDto {

  @NotNull(message = "must not be null")
  @Size(min = 5, max = 100, message = "length must be >=5 and <=100")
  private String lotName;

  @NotNull
  @Min(value = 1, message = "must be >= 1")
  private Integer startPrice;

  @NotNull(message = "must not be null")
  private String username;

}
