package ru.job4j.auth.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.auth.domain.Person;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.6. Rest
 * 1. RESTFul. Описание архитектуры [#6884]
 * PersonRepository хранилище модели данных Person
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 24.04.2023
 */
public interface PersonRepository extends CrudRepository<Person, Integer> {
}
