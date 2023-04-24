package ru.job4j.auth.service;

import ru.job4j.auth.domain.Person;

import java.util.Optional;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.6. Rest
 * 1. RESTFul. Описание архитектуры [#6884]
 * PersonService интерфейс описывает поведение бизнес логики обработки модели Person.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 24.04.2023
 */
public interface PersonService {
    Person save(Person person);

    Optional<Person> findById(int personId);

    void update(Person person);

    void deleteById(int personId);

    Iterable<Person> findAll();
}
