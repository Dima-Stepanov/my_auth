package ru.job4j.auth.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.auth.domain.User;
import ru.job4j.auth.repository.UserRepository;

import java.util.Optional;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.6. Rest
 * 3. Авторизация JWT [#9146]
 * SimpleUserService слой бизнес обработки модели User
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 24.04.2023
 */
@Service
@AllArgsConstructor
@Slf4j
public class SimpleUserService implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> save(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return Optional.of(user);
        } catch (Exception e) {
            log.error("Error save user: {}, exception: {}", user, e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }
}
