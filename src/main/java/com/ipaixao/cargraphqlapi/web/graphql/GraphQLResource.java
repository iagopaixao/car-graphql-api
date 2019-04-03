package com.ipaixao.cargraphqlapi.web.graphql;

import com.ipaixao.cargraphqlapi.factory.GraphQLSchemaFactory;
import com.ipaixao.cargraphqlapi.service.CarService;
import com.ipaixao.cargraphqlapi.util.GraphQLUtils;
import graphql.ExecutionInput;
import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class GraphQLResource {

  private final GraphQL graphQL;

  @Autowired
  public GraphQLResource(final CarService service, final GraphQLSchemaFactory factory) {
    graphQL = factory.createSchema(service);
  }

  @PostMapping(
      value = "/graphql",
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE
  )
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
