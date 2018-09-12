package com.iagosilva.api.service;

import com.iagosilva.api.service.dto.CarDTO;
import io.leangen.graphql.annotations.GraphQLArgument;
import java.net.URISyntaxException;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface CarService {

    ResponseEntity<List<CarDTO>> getCars();

    ResponseEntity<CarDTO> getCarById(@GraphQLArgument(name = "id") Long id);

    ResponseEntity<CarDTO> saveCar(@GraphQLArgument(name = "car") CarDTO car) throws URISyntaxException;

    ResponseEntity<CarDTO> updateCar(@GraphQLArgument(name = "car") CarDTO car);

    void deleteCar(@GraphQLArgument(name = "id") Long id);
}
