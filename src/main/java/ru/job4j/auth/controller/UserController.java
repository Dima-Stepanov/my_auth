package ru.job4j.auth.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.auth.domain.User;
import ru.job4j.auth.service.UserService;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.6. Rest
 * 3. Авторизация JWT [#9146]
 * UserController REST API контроллер модели User.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 24.04.2023
 */
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService users;

    @PostMapping("/sign-up")
    public ResponseEntity<User> signUp(@RequestBody User user) {
        return new ResponseEntity<>(
                this.users.save(user).orElseThrow(),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/all")
    public Iterable<User> findAll() {
        return this.users.findAllUsers();
    }
}
