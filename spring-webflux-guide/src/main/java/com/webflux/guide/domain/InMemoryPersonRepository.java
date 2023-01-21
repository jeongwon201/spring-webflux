package com.webflux.guide.domain;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;

@Repository
public class InMemoryPersonRepository implements PersonRepository {

    private static Map<Long, Person> store = new HashMap<>();
    private static long SEQUENCE = 5L;

    static {
        store.put(0L, new Person(0L, "Lee", 20));
        store.put(1L, new Person(1L, "Choi", 20));
        store.put(2L, new Person(2L, "Park", 20));
        store.put(3L, new Person(3L, "Hong", 20));
        store.put(4L, new Person(4L, "Kim", 20));
    }

    @Override
    public Mono<Person> save(Person person) {
        person.setId(SEQUENCE++);
        store.put(person.getId(), person);
        return Mono.just(person);
    }

    @Override
    public Mono<Person> findById(long id) {
        if(store.containsKey(id)) {
            return Mono.just(store.get(id));
        } else {
            return Mono.empty();
        }
    }

    @Override
    public Flux<Person> findAll() {
        return Flux.fromStream(store.values().stream());
    }
}