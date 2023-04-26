package ru.job4j.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.auth.domain.SimpleUser;
import ru.job4j.auth.handlers.Operation;
import ru.job4j.auth.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.6. Rest
 * 3. Авторизация JWT [#9146]
 * 5. Обработка исключений и Spring REST [#504797]
 * UserController REST API контроллер модели SimpleUser.
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
    @Validated(Operation.OnCreate.class)
    public ResponseEntity<SimpleUser> signUp(@Valid @RequestBody SimpleUser simpleUser) {
        if (simpleUser == null || simpleUser.getUsername() == null || simpleUser.getPassword() == null) {
            throw new NullPointerException("Username and password mustn't be empty");
        }
        if (simpleUser.getPassword().length() < SIZE_PASS || simpleUser.getPassword().isEmpty() || simpleUser.getPassword().isBlank()) {
            throw new IllegalArgumentException(
                    "Invalid password. Password length must be more than 2 characters.");
        }
        return new ResponseEntity<>(
                this.users.save(simpleUser).orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                "The simpleUser has not been saved, the username is already taken"
                        )
                ),
                HttpStatus.CREATED
        );
    }

    @PatchMapping("/")
    @Validated(Operation.OnUpdate.class)
    public SimpleUser updatePatch(@Valid @RequestBody SimpleUser simpleUser) throws Exception {
        var updateUser = users.updatePatch(simpleUser);
        return updateUser.orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST)
        );
    }

    @GetMapping("/all")
    public Iterable<SimpleUser> findAll() {
        return this.users.findAllUsers();
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public void exceptionHandler(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() {{
            put("message", e.getMessage());
            put("type", e.getClass());
        }}));
        log.error(e.getLocalizedMessage());
    }
}
