package com.webflux.guide.domain;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonRepository {
    Mono<Person> save(Person person);
    Mono<Person> findById(long id);
    Flux<Person> findAll();
}
