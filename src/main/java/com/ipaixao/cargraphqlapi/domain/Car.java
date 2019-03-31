package com.ipaixao.cargraphqlapi.domain;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Car {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @GraphQLQuery(name = "id", description = "A car's id")
  private Long id;

  @Column(nullable = false)
  @GraphQLQuery(name = "brand", description = "A car's brand")
  private String brand;

  @Column(nullable = false)
  @GraphQLQuery(name = "model", description = "A car's model")
  private String model;

  @Column(nullable = false)
  @GraphQLQuery(name = "color", description = "A car's color")
  private String color;

  @Column(nullable = false)
  @GraphQLQuery(name = "year", description = "A car's year")
  private Integer year;

  @Column(nullable = false)
  @GraphQLQuery(name = "price", description = "A car's price")
  private BigDecimal price;

  @GraphQLQuery(name = "description", description = "A car's description")
  private String description;

  @Column(nullable = false)
  @GraphQLQuery(name = "isNew", description = "If a car is new")
  private Boolean isNew;

  @GraphQLQuery(name = "isNew", description = "Creation date")
  private LocalDate createdAt;

  @GraphQLQuery(name = "isNew", description = "Update date")
  private LocalDate updatedAt;
}
