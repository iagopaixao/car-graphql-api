package com.ipaixao.cargraphqlapi.web.graphql;

import com.ipaixao.cargraphqlapi.service.CarService;
import com.ipaixao.cargraphqlapi.service.dto.CarDTO;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Component
@GraphQLApi
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CarGraphQL {

  private final CarService service;

  @GraphQLQuery(name = "cars", description = "Gets all cars")
  public List<CarDTO> getAll() {
    return service.getAll();
  }

  @GraphQLQuery(name = "car", description = "Gets a car by id")
  public CarDTO getById(@GraphQLArgument(name = "id") Long id) {
    return service.getById(id);
  }

  @GraphQLMutation(name = "saveCar", description = "Save a car")
  public CarDTO save(@GraphQLArgument(name = "car") CarDTO car) {
    return service.save(car);
  }

  @GraphQLMutation(name = "updateCar")
  public CarDTO update(@GraphQLArgument(name = "car", description = "Update a car") CarDTO car) {
    return service.update(car);
  }

  @GraphQLMutation(name = "deleteCar", description = "Deletes a car by id")
  public HttpStatus delete(@GraphQLArgument(name = "id") Long id) {
    service.delete(id);

    return OK;
  }
}
