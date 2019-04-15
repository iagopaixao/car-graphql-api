package com.ipaixao.cargraphqlapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ipaixao.cargraphqlapi.domain.Car;
import com.ipaixao.cargraphqlapi.service.dto.CarDTO;
import org.hamcrest.Factory;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static org.apache.commons.lang3.math.NumberUtils.LONG_ONE;

public final class CarMockFactory {

  private static final BigDecimal DEFAULT_PRICE =
      new BigDecimal("100000").setScale(2, RoundingMode.HALF_UP);

  private static final String DEFAULT_BRAND = "BMW";

  private static final String DEFAULT_MODEL = "BMW M3 Sedan";

  private static final String DEFAULT_DESCRIPTION = "A sophisticated car";

  private static final String DEFAULT_COLOR = "Black";

  private static final int DEFAULT_YEAR = 2019;

  public static final ObjectMapper OBJECT_MAPPER;

  public static final String URI = "/api/cars";

  static {
    OBJECT_MAPPER =
        new ObjectMapper()
            .registerModules(new Jdk8Module(), new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
  }

  private CarMockFactory() {
    throw new IllegalStateException("Utility Class");
  }

  @Factory
  public static List<CarDTO> carDTOs() {
    return Collections.singletonList(carDTO());
  }

  @Factory
  public static List<Car> cars() {
    return Collections.singletonList(car());
  }

  @Factory
  public static Car car() {
    final var car =
        Car.builder()
            .id(LONG_ONE)
            .brand(DEFAULT_BRAND)
            .price(DEFAULT_PRICE)
            .model(DEFAULT_MODEL)
            .description(DEFAULT_DESCRIPTION)
            .color(DEFAULT_COLOR)
            .isNew(FALSE)
            .year(DEFAULT_YEAR)
            .build();
    car.setAuditLog(null);

    return car;
  }

  @Factory
  public static CarDTO carDTO() {
    return CarDTO.builder()
        .id(LONG_ONE)
        .brand(DEFAULT_BRAND)
        .price(DEFAULT_PRICE)
        .model(DEFAULT_MODEL)
        .description(DEFAULT_DESCRIPTION)
        .color(DEFAULT_COLOR)
        .isNew(FALSE)
        .year(DEFAULT_YEAR)
        .build();
  }

  @Factory
  public static String payload() throws IOException {
    final var payload = carDTO();
    payload.setId(null);

    return OBJECT_MAPPER.writeValueAsString(payload);
  }
}
