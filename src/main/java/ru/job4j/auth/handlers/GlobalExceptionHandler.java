package ru.job4j.auth.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.6. Rest
 * 5. Обработка исключений и Spring REST [#504797]
 * GlobalExceptionHandler обработчик исключений возникающие в контролере во всем приложении.
 * Решить проблему выше,
 * помогает аннотация @ControllerAdvice используемая совместно с @ExceptionHandler.
 * Код ниже обрабатывает все исключения NullPointerException,
 * которые возникают во всех контроллерах.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 25.04.2023
 */
@ControllerAdvice
@AllArgsConstructor
@Slf4j
public class GlobalExceptionHandler {
    private final ObjectMapper objectMapper;

    @ExceptionHandler(value = {NullPointerException.class})
    public void handleNullPointerException(Exception e,
                                           HttpServletRequest request,
                                           HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() {{
            put("message", "Some of fields empty");
            put("details", e.getMessage());
        }}));
        log.error(e.getMessage());
    }

    /**
     * ValidatorControllerAdvise для  MethodArgumentNotValidException.class
     *
     * @param exception MethodArgumentNotValidException
     * @return ResponseEntity
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handelMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return ResponseEntity.badRequest().body(
                exception.getFieldErrors().stream()
                        .map(f -> Map.of(
                                f.getField(),
                                String.format("%s. Actual values: %s", f.getDefaultMessage(), f.getRejectedValue())
                        ))
                        .collect(Collectors.toList())
        );
    }
}
