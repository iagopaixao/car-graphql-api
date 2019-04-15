package com.ipaixao.cargraphqlapi.service.impl;

import com.ipaixao.cargraphqlapi.domain.Car;
import com.ipaixao.cargraphqlapi.exception.BusinessException;
import com.ipaixao.cargraphqlapi.exception.EntityNotFoundException;
import com.ipaixao.cargraphqlapi.repository.CarRepository;
import com.ipaixao.cargraphqlapi.service.CarService;
import com.ipaixao.cargraphqlapi.service.dto.CarDTO;
import com.ipaixao.cargraphqlapi.service.mapper.CarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.ipaixao.cargraphqlapi.enumeration.Messages.MODEL_NAME_DUPLICATION;
import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Service
@Transactional(
    propagation = REQUIRES_NEW,
    isolation = READ_COMMITTED
)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CarServiceImpl implements CarService {

  private final CarRepository repository;

  private final CarMapper mapper;

  @Transactional(readOnly = true)
  public List<CarDTO> getAll() {
    return mapper.toDto(repository.findAll());
  }

  @Transactional(readOnly = true)
  public CarDTO getById(final Long id) {
    return mapper.toDto(
        repository.findById(id).orElseThrow(() -> new EntityNotFoundException(Car.class))
    );
  }

  public CarDTO save(final CarDTO car) {
    repository
        .findDuplicate(car.getModel(), car.getColor())
        .ifPresent(
            cars -> {
              throw new BusinessException(MODEL_NAME_DUPLICATION, car.getColor());
            }
        );
    return mapper.toDto(repository.save(mapper.toEntity(car)));
  }

  public CarDTO update(final CarDTO car) {
    repository
        .findDuplicate(car.getModel(), car.getColor(), car.getId())
        .ifPresent(
            cars -> {
              throw new BusinessException(MODEL_NAME_DUPLICATION, car.getColor());
            }
        );
    return mapper.toDto(repository.save(mapper.toEntity(car)));
  }

  public void delete(final Long id) {
    repository.deleteById(id);
  }
}
