package ru.job4j.auth.domain;

import lombok.*;
import ru.job4j.auth.handlers.Operation;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.6. Rest
 * 3. Авторизация JWT [#9146]
 * SimpleUser модель данных для хранения пользователей авторизации JWT
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 24.04.2023
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class SimpleUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value = 0, message = "Id must be non null", groups = {
            Operation.OnUpdate.class, Operation.OnDelete.class
    })
    private int id;
    @EqualsAndHashCode.Include
    @Column(nullable = false, unique = true)
    @NotNull(message = "Username must be non null", groups = {Operation.OnCreate.class})
    @Size(min = 3, max = 8, message = "Login must be more 3 and less 5")
    private String username;
    @EqualsAndHashCode.Include
    @Column(nullable = false)
    @Min(value = 4, message = "Password must be more 4", groups = {Operation.OnCreate.class})
    private String password;
    private boolean enabled = true;

    public boolean getEnabled() {
        return enabled;
    }
}
