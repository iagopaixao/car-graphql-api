package com.ipaixao.cargraphqlapi.config;

import com.ipaixao.cargraphqlapi.web.rest.CarResource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.any;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

@Configuration
@EnableSwagger2
@ComponentScan(basePackageClasses = CarResource.class)
@EnableConfigurationProperties(SwaggerUIPropertiesConfig.class)
public class SwaggerUIConfig {

  @Bean
  public Docket swaggerSpringMvcPlugin(SwaggerUIPropertiesConfig property) {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo(property))
        .select()
        .apis(basePackage(property.getBasePackage()))
        .paths(any())
        .build();
  }

  private ApiInfo apiInfo(SwaggerUIPropertiesConfig property) {
    return new ApiInfoBuilder()
        .title(property.getTitle())
        .description(property.getDescription())
        .license(property.getLicense())
        .contact(contract(property))
        .build();
  }

  private Contact contract(SwaggerUIPropertiesConfig property) {
    return new Contact(
        property.getContractName(),
        property.getContractUrl(),
        property.getContractEmail()
    );
  }
}
