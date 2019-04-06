package com.ipaixao.cargraphqlapi.domain;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@Getter
@Embeddable
public class CarAudit {

  @Column(name = "created_at", nullable = false)
  @GraphQLQuery(name = "isNew", description = "Creation date")
  private LocalDateTime createdAt;

  @Column(name = "updated_at", nullable = false)
  @GraphQLQuery(name = "isNew", description = "Updating date")
  private LocalDateTime updatedAt;

  CarAudit() {
    setup();
  }

  @PrePersist
  private void setup() {
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }
}
