package com.ipaixao.cargraphqlapi.service.mapper;

import com.ipaixao.cargraphqlapi.domain.Car;
import com.ipaixao.cargraphqlapi.service.dto.CarDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;
import static org.mapstruct.ReportingPolicy.ERROR;

@Mapper(
    componentModel = "spring",
    injectionStrategy = CONSTRUCTOR,
    unmappedSourcePolicy = ERROR,
    unmappedTargetPolicy = ERROR,
    nullValueCheckStrategy = ALWAYS,
    nullValuePropertyMappingStrategy = IGNORE
)
public interface CarMapper extends EntityMapper<CarDTO, Car> {

  @Override
  @Mapping(target = "auditLog", source = "auditLog", ignore = true)
  Car toEntity(CarDTO dto);

  @Override
  @Mapping(target = "auditLog", source = "auditLog", ignore = true)
  CarDTO toDto(Car entity);
}
