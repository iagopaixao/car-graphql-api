package com.ipaixao.cargraphqlapi.repository;

import com.ipaixao.cargraphqlapi.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {}
