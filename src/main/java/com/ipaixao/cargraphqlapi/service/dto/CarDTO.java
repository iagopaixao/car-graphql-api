package com.ipaixao.cargraphqlapi.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize(using = LocalDateSerializer.class)
@JsonDeserialize(using = LocalDateDeserializer.class)
@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
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

  @NotNull
  @Positive(groups = int.class)
  private Integer year;

  @NotNull
  @Positive(groups = BigDecimal.class)
  private BigDecimal price;

  private String description;

  @NotNull private Boolean isNew;

  private LocalDate createdAt;

  private LocalDate updatedAt;
}
