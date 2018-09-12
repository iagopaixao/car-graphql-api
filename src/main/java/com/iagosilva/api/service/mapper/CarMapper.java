package com.iagosilva.api.service.mapper;

import com.iagosilva.api.domain.Car;
import com.iagosilva.api.service.dto.CarDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper extends EntityMapper<CarDTO, Car> {}
