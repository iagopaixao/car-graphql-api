package com.ipaixao.cargraphqlapi.web.graphql;

import com.ipaixao.cargraphqlapi.factory.GraphQLSchemaFactory;
import com.ipaixao.cargraphqlapi.service.CarService;
import com.ipaixao.cargraphqlapi.util.GraphQLUtils;
import graphql.ExecutionInput;
import graphql.GraphQL;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GraphQLResource {

  private final CarService service;

  private final GraphQLSchemaFactory factory;

  private final GraphQL graphQL = factory.createSchema(service);

  @PostMapping("/graphql")
  public Map<String, Object> graphql(@RequestBody Map<String, String> request, HttpServletRequest servletRequest) {
    return graphQL
        .execute(
            ExecutionInput.newExecutionInput()
                .query(request.get(GraphQLUtils.QUERY_PARAM))
                .operationName(request.get(GraphQLUtils.OPERATION_NAME))
                .context(servletRequest)
                .build()
        ).toSpecification();
  }
}
