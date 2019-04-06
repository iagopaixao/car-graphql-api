package com.ipaixao.cargraphqlapi.service;

import com.ipaixao.cargraphqlapi.service.dto.CarDTO;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface CarService {

  List<CarDTO> getAll();

  CarDTO getById(Long id);

  CarDTO save(CarDTO car);

  CarDTO update(CarDTO car);

  HttpStatus delete(Long id);
}
