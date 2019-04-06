package com.ipaixao.cargraphqlapi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS;

@Configuration
public class JacksonMapperConfig {

  @Bean
  public ObjectMapper mapper() {
    return new ObjectMapper()
        .registerModules(new Jdk8Module(), new JavaTimeModule())
        .configure(WRITE_DURATIONS_AS_TIMESTAMPS, false);
  }
}
