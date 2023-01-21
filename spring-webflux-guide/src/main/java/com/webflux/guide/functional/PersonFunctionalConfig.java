package com.webflux.guide.functional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class PersonFunctionalConfig {

    private PersonHandler personHandler;

    public PersonFunctionalConfig(PersonHandler personHandler) {
        this.personHandler = personHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> router() {
        return RouterFunctions.route()
                .path("/functional/person", builder -> builder
                        .POST(personHandler::savePerson)
                        .GET("{id}", personHandler::getPersonById)
                        .GET(personHandler::getAllPerson)
                ).build();
    }
}