package ru.job4j.auth;

import liquibase.integration.spring.SpringLiquibase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.6. Rest
 * 1. RESTFul. Описание архитектуры [#6884]
 * AuthApplication вход в приложение содержит метод MAIN.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 24.04.2023
 */
@SpringBootApplication
@Slf4j
public class AuthApplication {
    private static final String URL_API = "http://localhost:8080/";

    @Bean
    public SpringLiquibase liquibase(DataSource ds) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:db/changelog/liquibase-changeLog.xml");
        liquibase.setDataSource(ds);
        return liquibase;
    }

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
        log.info("Start page RestFul API: {}", URL_API);
    }

}
