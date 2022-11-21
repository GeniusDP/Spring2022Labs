package com.spring.lab2.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class LotDto {

  @NotNull
  @Size(min = 5, max = 100)
  private String lotName;

  @NotNull
  @Min(1)
  private Integer startPrice;

  @NotNull
  private String username;

}
