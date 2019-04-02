package com.ipaixao.cargraphqlapi;

import com.ipaixao.cargraphqlapi.service.CarService;
import com.ipaixao.cargraphqlapi.service.dto.CarDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;

@Slf4j
@SpringBootApplication
public class CarGraphqlApplication {

  public static void main(String[] args) {
    SpringApplication.run(CarGraphqlApplication.class, args);
  }

  @Bean
  public ApplicationRunner init(CarService carService) {
    return args -> carService.saveCar(car());
  }

  private CarDTO car() {
    return CarDTO.builder()
        .brand("Ferrari")
        .model("Hatch")
        .isNew(true)
        .year(LocalDate.now().getYear())
        .price(BigDecimal.valueOf(87000.00))
        .color("Red")
        .createdAt(LocalDate.now())
        .updatedAt(LocalDate.now())
        .build();
  }
}
