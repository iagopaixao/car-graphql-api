package com.ipaixao.cargraphqlapi.web.rest;

import com.ipaixao.cargraphqlapi.service.CarService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.ipaixao.cargraphqlapi.CarMockFactory.*;
import static com.ipaixao.cargraphqlapi.enumeration.Messages.MODEL_NAME_DUPLICATION;
import static com.ipaixao.cargraphqlapi.util.MessageUtils.getMessage;
import static org.apache.commons.lang3.RandomUtils.nextLong;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CarResourceIntegrationTest {

  @Autowired private CarService service;

  private MockMvc mockMvc;

  @Before
  public void setup() {
    this.mockMvc =
        MockMvcBuilders.standaloneSetup(new CarResource(service))
            .setMessageConverters(new MappingJackson2HttpMessageConverter(OBJECT_MAPPER))
            .build();
  }

  @Test
  public void shouldSaveACar_whenSaveIsCalled() throws Exception {
    final var carDTO = carDTO();

    mockMvc
        .perform(
            post(URI)
                .accept(APPLICATION_JSON_UTF8)
                .contentType(APPLICATION_JSON)
                .content(payload())
        )
        .andExpect(status().isCreated())
        .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
        .andExpect(jsonPath("$.brand").value(carDTO.getBrand()))
        .andExpect(jsonPath("$.color").value(carDTO.getColor()))
        .andExpect(jsonPath("$.description").value(carDTO.getDescription()))
        .andExpect(jsonPath("$.isNew").value(carDTO.getIsNew()))
        .andExpect(jsonPath("$.model").value(carDTO.getModel()))
        .andExpect(jsonPath("$.price").value(carDTO.getPrice().doubleValue()))
        .andExpect(jsonPath("$.year").value(carDTO.getYear()));
  }

  @Test
  public void shouldReturnUnprocessableEntity_forValidateDuplication_whenSaveIsCalled() throws Exception {
    final var dto = carDTO();
    dto.setId(null);
    dto.setColor("Red");

    final var carDTO = service.save(dto);
    carDTO.setId(null);

    mockMvc
        .perform(
            post(URI)
                .accept(APPLICATION_JSON_UTF8)
                .contentType(APPLICATION_JSON)
                .content(OBJECT_MAPPER.writeValueAsString(carDTO))
        )
        .andExpect(status().isUnprocessableEntity())
        .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
        .andExpect(
            jsonPath("$").value(
                getMessage(MODEL_NAME_DUPLICATION.getValue(), carDTO.getColor())
            )
        );
  }

  @Test
  public void shouldUpdateACar_whenUpdateIsCalled() throws Exception {
    final var dto = carDTO();
    dto.setId(null);
    dto.setColor("Yellow");

    final var carDTO = service.save(dto);
    carDTO.setColor("White");

    mockMvc
        .perform(
            put(URI)
                .accept(APPLICATION_JSON_UTF8)
                .contentType(APPLICATION_JSON)
                .content(OBJECT_MAPPER.writeValueAsString(carDTO))
        )
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(carDTO.getId()))
        .andExpect(jsonPath("$.brand").value(carDTO.getBrand()))
        .andExpect(jsonPath("$.color").value(carDTO.getColor()))
        .andExpect(jsonPath("$.description").value(carDTO.getDescription()))
        .andExpect(jsonPath("$.isNew").value(carDTO.getIsNew()))
        .andExpect(jsonPath("$.model").value(carDTO.getModel()))
        .andExpect(jsonPath("$.price").value(carDTO.getPrice().doubleValue()))
        .andExpect(jsonPath("$.year").value(carDTO.getYear()));
  }

  @Test
  public void shouldReturnUnprocessableEntity_forValidateDuplication_whenUpdateIsCalled() throws Exception {
    final var dto1 = carDTO();
    final var dto2 = carDTO();
    dto1.setId(null);
    dto1.setColor("Silver");
    dto2.setId(null);
    dto2.setColor("Blue");

    service.save(dto1);
    final var carDTO = service.save(dto2);
    carDTO.setColor("Silver");

    mockMvc
        .perform(
            put(URI)
                .accept(APPLICATION_JSON_UTF8)
                .contentType(APPLICATION_JSON)
                .content(OBJECT_MAPPER.writeValueAsString(carDTO))
        )
        .andExpect(status().isUnprocessableEntity())
        .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
        .andExpect(
            jsonPath("$").value(
                getMessage(MODEL_NAME_DUPLICATION.getValue(), carDTO.getColor())
            )
        );
  }

  @Test
  public void shouldGetOneCar_whenGetByIdIsCalled() throws Exception {
    final var dto = carDTO();
    dto.setId(null);
    dto.setColor("Green");

    final var carDTO = service.save(dto);

    mockMvc
        .perform(
            get(URI + "/" + carDTO.getId())
                .accept(APPLICATION_JSON_UTF8)
                .contentType(APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(carDTO.getId()))
        .andExpect(jsonPath("$.brand").value(carDTO.getBrand()))
        .andExpect(jsonPath("$.color").value(carDTO.getColor()))
        .andExpect(jsonPath("$.description").value(carDTO.getDescription()))
        .andExpect(jsonPath("$.isNew").value(carDTO.getIsNew()))
        .andExpect(jsonPath("$.model").value(carDTO.getModel()))
        .andExpect(jsonPath("$.price").value(carDTO.getPrice().doubleValue()))
        .andExpect(jsonPath("$.year").value(carDTO.getYear()));
  }

  @Test
  public void shouldThrowEntityNotFoundException_whenGetByIdIsCalled() throws Exception {
    final var dto = carDTO();
    dto.setId(null);
    dto.setModel("Hatch");
    dto.setColor("Black");

    service.save(dto);

    mockMvc
        .perform(
            get(URI + "/" + nextLong())
                .accept(APPLICATION_JSON_UTF8)
                .contentType(APPLICATION_JSON)
        )
        .andExpect(status().isNotFound())
        .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON));
  }

  @Test
  public void shouldGetAllCars_whenGetAllIsCalled() throws Exception {
    final var dto = carDTO();
    dto.setId(null);
    dto.setColor("Purple");

    final var carDTO = service.save(dto);

    mockMvc
        .perform(
            get(URI)
                .accept(APPLICATION_JSON_UTF8)
                .contentType(APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
        .andExpect(jsonPath("$..id").value(hasItem(carDTO.getId().intValue())))
        .andExpect(jsonPath("$..brand").value(hasItem(carDTO.getBrand())))
        .andExpect(jsonPath("$..color").value(hasItem(carDTO.getColor())))
        .andExpect(jsonPath("$..description").value(hasItem(carDTO.getDescription())))
        .andExpect(jsonPath("$..isNew").value(hasItem(carDTO.getIsNew())))
        .andExpect(jsonPath("$..model").value(hasItem(carDTO.getModel())))
        .andExpect(jsonPath("$..price").value(hasItem(carDTO.getPrice().doubleValue())))
        .andExpect(jsonPath("$..year").value(hasItem(carDTO.getYear())));
  }

  @Test
  public void shouldDeleteOnCar_whenDeleteIsCalled() throws Exception {
    final var dto = carDTO();
    dto.setId(null);
    dto.setColor("Grey");

    final var carDTO = service.save(dto);

    mockMvc
        .perform(
            delete(URI + "/" + carDTO.getId())
                .accept(APPLICATION_JSON_UTF8)
                .contentType(APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON));
  }
}
