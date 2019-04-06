package com.ipaixao.cargraphqlapi.domain;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "car", schema = "public")
public class Car {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  @GraphQLQuery(name = "id", description = "A car's id")
  private Long id;

  @Column(nullable = false, length = 40)
  @GraphQLQuery(name = "brand", description = "A car's brand")
  private String brand;

  @Column(nullable = false, length = 50)
  @GraphQLQuery(name = "model", description = "A car's model")
  private String model;

  @Column(nullable = false, length = 30)
  @GraphQLQuery(name = "color", description = "A car's color")
  private String color;

  @Column(nullable = false)
  @GraphQLQuery(name = "year", description = "A car's year")
  private Integer year;

  @Column(nullable = false, scale = 2)
  @GraphQLQuery(name = "price", description = "A car's price")
  private BigDecimal price;

  @GraphQLQuery(name = "description", description = "A car's description")
  private String description;

  @Column(name = "is_new", nullable = false)
  @GraphQLQuery(name = "isNew", description = "If a car is new")
  private Boolean isNew;

  @Embedded @Builder.Default private AuditLog auditLog = new AuditLog();
}
