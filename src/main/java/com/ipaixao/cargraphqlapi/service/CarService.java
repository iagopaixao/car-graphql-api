package com.ipaixao.cargraphqlapi.service;

import com.ipaixao.cargraphqlapi.service.dto.CarDTO;

import java.util.List;

public interface CarService {

  List<CarDTO> getCars();

  CarDTO getCarById(Long id);

  CarDTO saveCar(CarDTO car);

  CarDTO updateCar(CarDTO car);

  void deleteCar(Long id);
}
