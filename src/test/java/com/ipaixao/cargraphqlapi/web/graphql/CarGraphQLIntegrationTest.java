package com.ipaixao.cargraphqlapi.web.graphql;

import com.ipaixao.cargraphqlapi.service.CarService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.ipaixao.cargraphqlapi.CarMockFactory.OBJECT_MAPPER;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CarGraphQLIntegrationTest {

  @Autowired private CarService service;

  private MockMvc mockMvc;

  @Before
  public void setup() {
    this.mockMvc =
        MockMvcBuilders.standaloneSetup(new CarGraphQL(service))
            .setMessageConverters(new MappingJackson2HttpMessageConverter(OBJECT_MAPPER))
            .build();
  }
}
