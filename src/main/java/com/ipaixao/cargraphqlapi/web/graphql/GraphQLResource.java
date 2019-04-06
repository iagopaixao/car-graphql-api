package com.ipaixao.cargraphqlapi.web.graphql;

import com.ipaixao.cargraphqlapi.service.CarService;
import com.ipaixao.cargraphqlapi.service.dto.CarDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.net.URI.create;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GraphQLResource {

  private final CarService service;

  @GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<List<CarDTO>> getAll() {
    return ResponseEntity.ok(service.getAll());
  }

  @GetMapping(
      value = "/{id}",
      consumes = APPLICATION_JSON_UTF8_VALUE,
      produces = APPLICATION_JSON_UTF8_VALUE
  )
  public ResponseEntity<CarDTO> getById(@PathVariable("id") @Valid Long id) {
    return ResponseEntity.ok(service.getById(id));
  }

  @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<CarDTO> save(@RequestBody @Valid CarDTO car) {
    return ResponseEntity.created(create("/cars")).body(service.save(car));
  }

  @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<CarDTO> update(@RequestBody @Valid CarDTO car) {
    return ResponseEntity.ok(service.update(car));
  }

  @DeleteMapping(
      value = "/{id}",
      consumes = APPLICATION_JSON_UTF8_VALUE,
      produces = APPLICATION_JSON_UTF8_VALUE
  )
  public HttpStatus delete(@PathVariable("id") @Valid Long id) {
    return service.delete(id);
  }
}
