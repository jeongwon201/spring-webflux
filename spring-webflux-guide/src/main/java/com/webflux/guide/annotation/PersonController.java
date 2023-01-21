package com.webflux.guide.annotation;

import com.webflux.guide.domain.Person;
import com.webflux.guide.domain.PersonRepository;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/annotation")
public class PersonController {
    private PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping("/person")
    public Mono<Person> savePerson(@RequestBody Person person) {
        return personRepository.save(person);
    }

    @GetMapping("/person/{id}")
    public Mono<Person> getPersonById(@PathVariable long id) {
        return personRepository.findById(id);
    }

    @GetMapping("/person")
    public Flux<Person> getAllPerson() {
        return personRepository.findAll();
    }
}
