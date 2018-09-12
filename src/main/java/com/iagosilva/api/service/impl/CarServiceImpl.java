package com.iagosilva.api.service.impl;

import com.iagosilva.api.exception.EntityNotFoundException;
import com.iagosilva.api.repository.CarRepository;
import com.iagosilva.api.service.CarService;
import com.iagosilva.api.service.dto.CarDTO;
import com.iagosilva.api.service.mapper.CarMapper;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    private final CarRepository repository;

    private final CarMapper mapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, CarMapper mapper) {
        this.repository = carRepository;
        this.mapper = mapper;
    }

    @GraphQLQuery(name = "cars")
    public ResponseEntity<List<CarDTO>> getCars() { return ResponseEntity.ok(mapper.toDto(repository.findAll())); }

    @GraphQLQuery(name = "car")
    public ResponseEntity<CarDTO> getCarById(@GraphQLArgument(name = "id") Long id) {
        return ResponseEntity.ok(mapper.toDto(repository.findById(id).orElseThrow(EntityNotFoundException::new)));
    }

    @GraphQLMutation(name = "saveCar")
    public ResponseEntity<CarDTO> saveCar(@GraphQLArgument(name = "car") CarDTO car) throws URISyntaxException {
        CarDTO carDTO = mapper.toDto(repository.save(mapper.toEntity(car)));
        return ResponseEntity.created(new URI("saveCar" + carDTO.getId())).body(carDTO);
    }

    @GraphQLMutation(name = "updateCar")
    public ResponseEntity<CarDTO> updateCar(@GraphQLArgument(name = "car") CarDTO car) {
        return ResponseEntity.ok(mapper.toDto(repository.save(mapper.toEntity(car))));
    }

    @GraphQLMutation(name = "deleteCar")
    public void deleteCar(@GraphQLArgument(name = "id") Long id) { repository.deleteById(id); }
}
