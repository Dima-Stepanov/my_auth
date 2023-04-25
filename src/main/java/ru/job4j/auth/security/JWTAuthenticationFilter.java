package ru.job4j.auth.security;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.job4j.auth.domain.SimpleUser;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.6. Rest
 * 3. Авторизация JWT [#9146]
 * JWTAuthenticationFilter
 * Пароли хешируются и прямом виде не хранятся в базе.
 * JWT - Java web token.
 * По аналогии с servlet создаем фильтр, который отлавливает пользователя.
 * Метод attempt проверяет, что логин и пароль верные.
 * Метод success генерирует token.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 24.04.2023
 */
@AllArgsConstructor
@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; /* 10 days */
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/sign-up";

    private AuthenticationManager auth;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            SimpleUser creds = new ObjectMapper()
                    .readValue(request.getInputStream(), SimpleUser.class);
            return auth.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            Collections.emptyList())
            );
        } catch (IOException e) {
            log.error("JWTAuthenticationFilter Error: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String token = JWT.create()
                .withSubject(((User) authResult.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }
}
