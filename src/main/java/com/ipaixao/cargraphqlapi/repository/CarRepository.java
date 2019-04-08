package com.ipaixao.cargraphqlapi.repository;

import com.ipaixao.cargraphqlapi.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

  @Query("from #{#entityName} c where c.model = :model and c.color = :color")
  Optional<List<Car>> findDuplicate(@Param("model") String model, @Param("color") String color);

  @Query("from #{#entityName} c where c.model = :model and c.color = :color and c.id <> :id")
  Optional<List<Car>> findDuplicate(
      @Param("model") String model, @Param("color") String color, @Param("id") Long id
  );
}
