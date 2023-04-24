package ru.job4j.auth.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.auth.service.UserService;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.6. Rest
 * 3. Авторизация JWT [#9146]
 * UserDetailService
 * Как и в Spring MVC нужно создать сервис UserDetailsService.
 * Этот сервис будет загружать в SecurityHolder детали авторизованного пользователя.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 24.04.2023
 */
@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private UserService users;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = users.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException(username));
        return new User(user.get().getUsername(),
                user.get().getPassword(),
                user.get().getAuthorities());
    }
}
