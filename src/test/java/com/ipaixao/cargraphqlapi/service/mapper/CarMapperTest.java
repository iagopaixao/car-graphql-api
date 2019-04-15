package com.ipaixao.cargraphqlapi.service.mapper;

import com.ipaixao.cargraphqlapi.domain.Car;
import com.ipaixao.cargraphqlapi.service.dto.CarDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.ipaixao.cargraphqlapi.CarMockFactory.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CarMapperTest {

  @Autowired private CarMapper mapper;

  @Test
  public void shouldReturnCarEntity_whenToDTOIsCalled() {
    final var result = mapper.toEntity(carDTO());
    result.setAuditLog(null);

    assertThat(result, is(equalTo(car())));
  }

  @Test
  public void shouldReturnCarDTO_whenToEntityIsCalled() {
    final var result = mapper.toDto(car());
    result.setAuditLog(null);

    assertThat(result, is(equalTo(carDTO())));
  }

  @Test
  public void shouldReturnCarEntityList_whenToDTOIsCalled() {
    final var result = mapper.toEntity(carDTOs());
    result.forEach(i -> i.setAuditLog(null));

    assertThat(result, is(equalTo(cars())));
  }

  @Test
  public void shouldReturnCarDTOList_whenToEntityIsCalled() {
    final var result = mapper.toDto(cars());
    result.forEach(i -> i.setAuditLog(null));

    assertThat(result, is(equalTo(carDTOs())));
  }

  @Test
  public void shouldReturnNull_whenToEntityIsCalled() {
    final CarDTO dto = null;
    final List<CarDTO> dtos = null;

    assertNull(mapper.toEntity(dto));
    assertNull(mapper.toEntity(dtos));
  }

  @Test
  public void shouldReturnNull_whenToDTOIsCalled() {
    final Car car = null;
    final List<Car> cars = null;

    assertNull(mapper.toDto(car));
    assertNull(mapper.toDto(cars));
  }
}
