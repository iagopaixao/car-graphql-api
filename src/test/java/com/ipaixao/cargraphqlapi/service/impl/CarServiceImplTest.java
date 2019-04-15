package com.ipaixao.cargraphqlapi.service.impl;

import com.ipaixao.cargraphqlapi.exception.BusinessException;
import com.ipaixao.cargraphqlapi.exception.EntityNotFoundException;
import com.ipaixao.cargraphqlapi.repository.CarRepository;
import com.ipaixao.cargraphqlapi.service.dto.CarDTO;
import com.ipaixao.cargraphqlapi.service.mapper.CarMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static com.ipaixao.cargraphqlapi.CarMockFactory.*;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;
import static org.apache.commons.lang3.math.NumberUtils.LONG_ONE;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceImplTest {

  @Mock private CarRepository repository;

  @Mock private CarMapper mapper;

  @InjectMocks CarServiceImpl service;

  @Test
  public void shouldReturnAllCars_whenGetAllIsCalled() {
    final var carDTOs = carDTOs();
    final var cars = cars();

    when(repository.findAll()).thenReturn(cars);
    when(mapper.toDto(cars)).thenReturn(carDTOs);

    List<CarDTO> result = service.getAll();

    assertThat(result, is(equalTo(carDTOs)));
    verify(mapper, times(INTEGER_ONE)).toDto(cars);
  }

  @Test
  public void shouldReturnOneCar_whenGetCarByIdIsCalled() {
    final var car = car();
    final var carDTO = carDTO();

    when(repository.findById(LONG_ONE)).thenReturn(Optional.of(car));
    when(mapper.toDto(car)).thenReturn(carDTO);

    final var result = service.getById(LONG_ONE);

    assertThat(result, is(equalTo(carDTO)));
    verify(repository, (times(INTEGER_ONE))).findById(LONG_ONE);
    verify(mapper, (times(INTEGER_ONE))).toDto(car);
  }

  @Test(expected = EntityNotFoundException.class)
  public void shouldThrowEntityNotFoundException_whenGetCarByIdIsCalled() {
    when(repository.findById(LONG_ONE)).thenReturn(Optional.empty());

    final var result = service.getById(LONG_ONE);

    assertNull(result);
    verify(repository, (times(INTEGER_ONE))).findById(LONG_ONE);
  }

  @Test
  public void shouldSaveOneCar_whenSaveIsCalled() {
    final var car = car();
    final var carDTO = carDTO();

    when(repository.findDuplicate(car.getModel(), car.getColor())).thenReturn(Optional.empty());
    when(mapper.toEntity(carDTO)).thenReturn(car);
    when(repository.save(car)).thenReturn(car);
    when(mapper.toDto(car)).thenReturn(carDTO);

    final var result = service.save(carDTO);

    assertThat(result, is(equalTo(carDTO)));
    verify(repository, times(INTEGER_ONE)).findDuplicate(car.getModel(), car.getColor());
    verify(mapper, times(INTEGER_ONE)).toEntity(carDTO);
    verify(repository, times(INTEGER_ONE)).save(car);
    verify(mapper, times(INTEGER_ONE)).toDto(car);
  }

  @Test(expected = BusinessException.class)
  public void shouldThrowBusinessException_whenSaveIsCalled() {
    final var car = car();
    final var carDTO = carDTO();

    when(repository.findDuplicate(car.getModel(), car.getColor())).thenReturn(Optional.of(cars()));

    final var result = service.save(carDTO);

    assertNull(result);
    verify(repository, times(INTEGER_ONE)).findDuplicate(car.getModel(), car.getColor());
  }

  @Test
  public void shouldUpdateOneCar_whenUpdateIsCalled() {
    final var car = car();
    final var carDTO = carDTO();

    when(repository.findDuplicate(car.getModel(), car.getColor(), car.getId()))
        .thenReturn(Optional.empty());
    when(mapper.toEntity(carDTO)).thenReturn(car);
    when(repository.save(car)).thenReturn(car);
    when(mapper.toDto(car)).thenReturn(carDTO);

    final var result = service.update(carDTO);

    assertThat(result, is(equalTo(carDTO)));
    verify(repository, times(INTEGER_ONE))
        .findDuplicate(car.getModel(), car.getColor(), car.getId());
    verify(mapper, times(INTEGER_ONE)).toEntity(carDTO);
    verify(repository, times(INTEGER_ONE)).save(car);
    verify(mapper, times(INTEGER_ONE)).toDto(car);
  }

  @Test(expected = BusinessException.class)
  public void shouldThrowBusinessException_whenUpdateIsCalled() {
    final var car = car();
    final var carDTO = carDTO();

    when(repository.findDuplicate(car.getModel(), car.getColor(), car.getId()))
        .thenReturn(Optional.of(cars()));

    final var result = service.update(carDTO);

    assertNull(result);
    verify(repository, times(INTEGER_ONE))
        .findDuplicate(car.getModel(), car.getColor(), car.getId());
  }

  @Test
  public void shouldDeleteOneCar_whenDeleteIsCalled() {
    doNothing().when(repository).deleteById(LONG_ONE);

    service.delete(LONG_ONE);

    verify(repository, times(INTEGER_ONE)).deleteById(LONG_ONE);
  }
}
