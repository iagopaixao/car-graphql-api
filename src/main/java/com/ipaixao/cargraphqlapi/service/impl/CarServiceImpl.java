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
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Service
@GraphQLApi
@Transactional(
    propagation = REQUIRES_NEW,
    isolation = READ_COMMITTED,
    rollbackFor = {EntityNotFoundException.class, SQLException.class}
)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CarServiceImpl implements CarService {

  private final CarRepository repository;

  private final CarMapper mapper;

  @Transactional(readOnly = true)
  @GraphQLQuery(name = "cars", description = "Gets all cars ")
  public List<CarDTO> getAll() {
    return mapper.toDto(repository.findAll());
  }

  @Transactional(readOnly = true)
  @GraphQLQuery(name = "car", description = "Gets a car by id")
  public CarDTO getById(@GraphQLArgument(name = "id") Long id) {
    return mapper.toDto(
        repository.findById(id).orElseThrow(() -> new EntityNotFoundException(Car.class))
    );
  }

  @GraphQLMutation(name = "saveCar", description = "Save a car")
  public CarDTO save(@GraphQLArgument(name = "car") CarDTO car) {
    return mapper.toDto(repository.save(mapper.toEntity(car)));
  }

  @GraphQLMutation(name = "updateCar")
  public CarDTO update(@GraphQLArgument(name = "car", description = "Update a car") CarDTO car) {
    return mapper.toDto(repository.save(mapper.toEntity(car)));
  }

  @GraphQLMutation(name = "deleteCar", description = "Deletes a car by id")
  public HttpStatus delete(@GraphQLArgument(name = "id") Long id) {
    repository.deleteById(id);

    return OK;
  }
}
