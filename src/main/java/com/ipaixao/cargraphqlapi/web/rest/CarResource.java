package com.ipaixao.cargraphqlapi.web.rest;

import com.ipaixao.cargraphqlapi.exception.BusinessException;
import com.ipaixao.cargraphqlapi.exception.EntityNotFoundException;
import com.ipaixao.cargraphqlapi.service.CarService;
import com.ipaixao.cargraphqlapi.service.dto.CarDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.net.URI.create;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.ResponseEntity.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import static org.springframework.web.bind.annotation.RequestMethod.OPTIONS;

@CrossOrigin(
    origins = "*",
    methods = {GET, POST, PUT, PATCH, DELETE, OPTIONS}
)
@Api("Car Resource")
@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CarResource {

  private final CarService service;

  @GetMapping(
      value = "/{id}",
      consumes = APPLICATION_JSON_UTF8_VALUE,
      produces = APPLICATION_JSON_UTF8_VALUE
  )
  @ApiOperation("Gets a car by id")
  public ResponseEntity getById(@PathVariable("id") @Valid Long id) {
    try{
      return ok(service.getById(id));
    } catch (EntityNotFoundException e) {
      return status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }

  @ApiOperation("Gets all cars")
  @GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<List<CarDTO>> getAll() {
      return ok(service.getAll());
  }

  @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
  @ApiOperation(value = "Save a car", code = 201)
  public ResponseEntity save(@RequestBody @Valid CarDTO car) {
    try{
      return ResponseEntity.created(create("/cars")).body(service.save(car));
    } catch (BusinessException e) {
      return unprocessableEntity().body(e.getMessage());
    }
  }

  @ApiOperation("Update a car")
  @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity update(@RequestBody @Valid CarDTO car) {
    try{
      return ok(service.update(car));
    } catch (BusinessException e) {
      return unprocessableEntity().body(e.getMessage());
    }
  }

  @DeleteMapping(
      value = "/{id}",
      consumes = APPLICATION_JSON_UTF8_VALUE,
      produces = APPLICATION_JSON_UTF8_VALUE
  )
  @ApiOperation("Deletes a car by id")
  public HttpStatus delete(@PathVariable("id") @Valid Long id) {
    service.delete(id);
    return OK;
  }
}