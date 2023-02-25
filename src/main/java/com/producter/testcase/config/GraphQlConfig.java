package com.producter.testcase.config;

import graphql.scalars.ExtendedScalars;
import graphql.schema.*;
import graphql.schema.idl.RuntimeWiring;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.util.List;


@Configuration
public class GraphQlConfig {

    @Bean
    public GraphQLScalarType dateTimeScalarType() {
        return ExtendedScalars.DateTime;
    }

    @Bean
    public static RuntimeWiringConfigurer graphqlConfigurer(List<GraphQLScalarType> graphQLScalarTypes) {
        return new RuntimeWiringConfigurer() {
            @Override
            public void configure(RuntimeWiring.Builder builder) {
                for (GraphQLScalarType scalarType : graphQLScalarTypes) {
                    builder.scalar(scalarType);
                }
            }
        };
    }
}
