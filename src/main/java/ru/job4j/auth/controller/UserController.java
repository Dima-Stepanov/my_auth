package ru.job4j.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.auth.domain.User;
import ru.job4j.auth.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.6. Rest
 * 3. Авторизация JWT [#9146]
 * 5. Обработка исключений и Spring REST [#504797]
 * UserController REST API контроллер модели User.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 24.04.2023
 */
@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Slf4j
public class UserController {
    private final UserService users;
    private final ObjectMapper objectMapper;
    private static final int SIZE_PASS = 2;

    @PostMapping("/sign-up")
    public ResponseEntity<User> signUp(@RequestBody User user) {
        if (user == null || user.getUsername() == null || user.getPassword() == null) {
            throw new NullPointerException("Username and password mustn't be empty");
        }
        if (user.getPassword().length() < SIZE_PASS || user.getPassword().isEmpty() || user.getPassword().isBlank()) {
            throw new IllegalArgumentException(
                    "Invalid password. Password length must be more than 2 characters.");
        }
        return new ResponseEntity<>(
                this.users.save(user).orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                "The user has not been saved, the username is already taken"
                        )
                ),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/all")
    public Iterable<User> findAll() {
        return this.users.findAllUsers();
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public void exceptionHandler(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() {{
            put("message", e.getMessage());
            put("type", e.getClass());
        }}));
        log.error(e.getLocalizedMessage());
    }
}
