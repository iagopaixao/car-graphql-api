package com.ipaixao.cargraphqlapi.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ipaixao.cargraphqlapi.domain.AuditLog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {

  private Long id;

  @Size(max = 40)
  @NotEmpty
  private String brand;

  @Size(max = 50)
  @NotEmpty
  private String model;

  @Size(max = 30)
  @NotEmpty
  private String color;

  @NotNull
  @Positive(groups = int.class)
  private Integer year;

  @NotNull
  @Positive(groups = BigDecimal.class)
  private BigDecimal price;

  private String description;

  @NotNull private Boolean isNew;

  @JsonIgnore private AuditLog auditLog;
}
