package ru.job4j.auth.service;

import ru.job4j.auth.domain.SimpleUser;

import java.util.Optional;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.6. Rest
 * 3. Авторизация JWT [#9146]
 * UserService интерфейс описывает поведение слой бизнес логики модели SimpleUser
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 24.04.2023
 */
public interface UserService {
    Optional<SimpleUser> save(SimpleUser simpleUser);

    Optional<SimpleUser> updatePatch(SimpleUser simpleUser) throws Exception;

    Optional<SimpleUser> findByUsername(String username);

    Iterable<SimpleUser> findAllUsers();
}
