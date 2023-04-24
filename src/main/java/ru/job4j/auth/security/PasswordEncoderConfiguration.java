package ru.job4j.auth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.6. Rest
 * 3. Авторизация JWT [#9146]
 * PasswordEncoderConfiguration создания BEAN PasswordEncoder
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 24.04.2023
 */
@Component
public class PasswordEncoderConfiguration {

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
