package com.ipaixao.cargraphqlapi.service;

import com.ipaixao.cargraphqlapi.service.dto.CarDTO;

import java.util.List;

public interface CarService {

  List<CarDTO> getAll();

  CarDTO getById(Long id);

  CarDTO save(CarDTO car);

  CarDTO update(CarDTO car);

  void delete(Long id);
}
