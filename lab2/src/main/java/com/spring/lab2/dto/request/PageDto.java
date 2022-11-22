package com.spring.lab2.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PageDto {

  @NotNull(message = "must not be null")
  @Min(value = 1, message = "must be >= 0")
  private Integer page;

  @Min(value = 3, message = "must be >= 3")
  @Max(value = 30, message = "must be <= 30")
  @NotNull(message = "must not be null")
  private Integer size;

}
