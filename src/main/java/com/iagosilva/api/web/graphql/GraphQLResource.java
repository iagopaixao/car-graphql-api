package com.iagosilva.api.web.graphql;

import com.iagosilva.api.factory.GraphQLSchemaFactory;
import com.iagosilva.api.service.impl.CarServiceImpl;
import com.iagosilva.api.util.GraphQLUtils;
import graphql.ExecutionInput;
import graphql.GraphQL;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GraphQLResource {

    private final GraphQL graphQL;

    @Autowired
    public GraphQLResource(CarServiceImpl service, GraphQLSchemaFactory factory) {
        graphQL = factory.createSchema(service);
    }

    @PostMapping("/graphql")
    public Map<String, Object> graphql(@RequestBody Map<String, String> request, HttpServletRequest servletRequest) {

        return graphQL.execute(
            ExecutionInput.newExecutionInput()
                          .query(request.get(GraphQLUtils.QUERY_PARAM))
                          .operationName(request.get(GraphQLUtils.OPERATION_NAME))
                          .context(servletRequest)
                          .build()
        ).toSpecification();
    }
}