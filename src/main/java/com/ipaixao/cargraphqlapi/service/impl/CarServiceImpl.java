package com.ipaixao.cargraphqlapi.service.impl;

import com.ipaixao.cargraphqlapi.domain.Car;
import com.ipaixao.cargraphqlapi.exception.EntityNotFoundException;
import com.ipaixao.cargraphqlapi.repository.CarRepository;
import com.ipaixao.cargraphqlapi.service.CarService;
import com.ipaixao.cargraphqlapi.service.dto.CarDTO;
import com.ipaixao.cargraphqlapi.service.mapper.CarMapper;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CarServiceImpl implements CarService {

  private final CarRepository repository;

  private final CarMapper mapper;

  @GraphQLQuery(name = "cars")
  public List<CarDTO> getCars() {
    return mapper.toDto(repository.findAll());
  }

  @GraphQLQuery(name = "car")
  public CarDTO getCarById(@GraphQLArgument(name = "id") Long id) {
    return mapper.toDto(
        repository.findById(id).orElseThrow(() -> new EntityNotFoundException(() -> Car.class))
    );
  }

  @GraphQLMutation(name = "saveCar")
  public CarDTO saveCar(@GraphQLArgument(name = "car") CarDTO car) {
    return mapper.toDto(repository.save(mapper.toEntity(car)));
  }

  @GraphQLMutation(name = "updateCar")
  public CarDTO updateCar(@GraphQLArgument(name = "car") CarDTO car) {
    return mapper.toDto(repository.save(mapper.toEntity(car)));
  }

  @GraphQLMutation(name = "deleteCar")
  public void deleteCar(@GraphQLArgument(name = "id") Long id) {
    repository.deleteById(id);
  }
}
