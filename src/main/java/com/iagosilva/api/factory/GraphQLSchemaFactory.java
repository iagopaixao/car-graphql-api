package com.iagosilva.api.factory;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import io.leangen.geantyref.TypeToken;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.metadata.strategy.query.AnnotatedResolverBuilder;
import io.leangen.graphql.metadata.strategy.value.jackson.JacksonValueMapperFactory;
import org.springframework.stereotype.Component;

@Component
public class GraphQLSchemaFactory {

    public GraphQL createSchema(Object service) {
        GraphQLSchema schema = new GraphQLSchemaGenerator()
            .withResolverBuilders(new AnnotatedResolverBuilder())
            .withOperationsFromSingleton(service, new TypeToken<Object>(){}.getType())
            .withValueMapperFactory(new JacksonValueMapperFactory())
            .generate();

        return GraphQL.newGraphQL(schema).build();
    }
}
