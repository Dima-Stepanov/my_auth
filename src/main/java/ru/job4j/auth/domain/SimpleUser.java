package ru.job4j.auth.domain;

import lombok.*;

import javax.persistence.*;

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
    private int id;
    @EqualsAndHashCode.Include
    @Column(nullable = false, unique = true)
    private String username;
    @EqualsAndHashCode.Include
    @Column(nullable = false)
    private String password;
    private boolean enabled = true;

    public boolean getEnabled() {
        return enabled;
    }
}
