package com.ipaixao.cargraphqlapi.factory;

import graphql.GraphQL;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.metadata.strategy.query.AnnotatedResolverBuilder;
import io.leangen.graphql.metadata.strategy.value.jackson.JacksonValueMapperFactory;
import org.springframework.stereotype.Component;

@Component
public class GraphQLSchemaFactory {

  public GraphQL createSchema(Object service) {
    var schema =
        new GraphQLSchemaGenerator()
            .withResolverBuilders(new AnnotatedResolverBuilder())
            .withOperationsFromSingleton(service)
            .withValueMapperFactory(new JacksonValueMapperFactory())
            .generate();

    return GraphQL.newGraphQL(schema).build();
  }
}
