package com.ipaixao.cargraphqlapi.service.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {

  private Long id;

  @Max(40)
  @NotNull
  private String brand;

  @Max(50)
  @NotNull
  private String model;

  @Max(30)
  @NotNull
  private String color;

  @NotNull private Integer year;

  @NotNull private BigDecimal price;

  private String description;

  @NotNull private boolean isNew;

  private LocalDate createdAt;

  private LocalDate updatedAt;
}
