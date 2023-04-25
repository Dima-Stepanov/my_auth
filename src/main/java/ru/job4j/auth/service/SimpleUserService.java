package ru.job4j.auth.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.auth.domain.SimpleUser;
import ru.job4j.auth.repository.UserRepository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Optional;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.6. Rest
 * 3. Авторизация JWT [#9146]
 * SimpleUserService слой бизнес обработки модели SimpleUser
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
    public Optional<SimpleUser> save(SimpleUser simpleUser) {
        try {
            simpleUser.setPassword(passwordEncoder.encode(simpleUser.getPassword()));
            userRepository.save(simpleUser);
            return Optional.of(simpleUser);
        } catch (Exception e) {
            log.error("Error save simpleUser: {}, exception: {}", simpleUser, e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<SimpleUser> updatePatch(SimpleUser simpleUser) throws InvocationTargetException, IllegalAccessException {
        var currentUser = userRepository.findById(simpleUser.getId());
        if (currentUser.isEmpty()) {
            throw new NullPointerException("User id: " + simpleUser.getId() + " , not found");
        }
        var methods = currentUser.get().getClass().getDeclaredMethods();
        var namePerMethod = new HashMap<String, Method>();
        for (var method : methods) {
            String name = method.getName();
            if (name.startsWith("get") || name.startsWith("set")) {
                namePerMethod.put(name, method);
            }
        }
        for (var name : namePerMethod.keySet()) {
            if (name.startsWith("get")) {
                Method getMethod = namePerMethod.get(name);
                Method setMethod = namePerMethod.get(name.replace("get", "set"));
                if (setMethod == null) {
                    return Optional.empty();
                }

                Object newValue = getMethod.invoke(simpleUser);
                if ("getPassword".equals(getMethod.getName()) && newValue != null) {
                    newValue = passwordEncoder.encode(newValue.toString());
                }
                if (newValue != null) {
                    setMethod.invoke(currentUser.get(), newValue);
                }
            }
        }
        userRepository.save(currentUser.get());
        return currentUser;
    }

    @Override
    public Optional<SimpleUser> findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public Iterable<SimpleUser> findAllUsers() {
        return userRepository.findAll();
    }
}
