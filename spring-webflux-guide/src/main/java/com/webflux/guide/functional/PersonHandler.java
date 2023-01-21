package com.webflux.guide.functional;

import com.webflux.guide.domain.Person;
import com.webflux.guide.domain.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class PersonHandler {
    private PersonRepository personRepository;

    public PersonHandler(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Mono<ServerResponse> savePerson(ServerRequest request) {
        Mono<Person> personMono = request.bodyToMono(Person.class);
        return personMono
                .flatMap(person -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(personRepository.save(person), Person.class));
    }

    public Mono<ServerResponse> getPersonById(ServerRequest request) {
        Mono<Person> personMono = personRepository.findById(Long.parseLong(request.pathVariable("id")));
        return personMono
                .flatMap(person -> ServerResponse
                        .status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(person))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getAllPerson(ServerRequest request) {
        Flux<Person> personFlux = personRepository.findAll();
        return ServerResponse
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(personFlux, Person.class);
    }
}
