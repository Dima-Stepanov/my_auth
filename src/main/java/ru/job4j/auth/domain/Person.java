package ru.job4j.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.job4j.auth.handlers.Operation;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.6. Rest
 * 1. RESTFul. Описание архитектуры [#6884]
 * Person DAO модель данных хранения данных пользователей.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 24.04.2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "persons")
public class Person {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Id must be non null", groups = {
            Operation.OnUpdate.class, Operation.OnDelete.class
    })
    private int id;

    @Column(nullable = false, unique = true)
    @NotNull(message = "Login must be not null", groups = {
            Operation.OnCreate.class
    })
    @Size(min = 3, max = 8, message = "Login must be more 3 and less 5")
    private String login;

    @Column(nullable = false)
    @NotNull(message = "Password must be not null", groups = {
            Operation.OnCreate.class
    })
    @Size(min = 4, max = 8, message = "Password must be more 4 and less 6")
    private String password;
}
