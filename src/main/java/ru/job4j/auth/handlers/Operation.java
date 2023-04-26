package ru.job4j.auth.handlers;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.6. Rest
 * 8. Валидация моделей в Spring REST [#504801]
 * Группы валидации
 * Бывают ситуации, когда валидация должна отрабатывать только для некоторых операций.
 * В этом случае мы можем задавать группы,
 * точнее перечень классов маркеров, для указания ситуаций,
 * для которых нужно делать валидацию.
 * Например, в примере выше нам необязательно указывать id при создании Book.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 26.04.2023
 */
public class Operation {
    public interface OnCreate {
    }

    public interface OnDelete {
    }

    public interface OnUpdate {
    }
}
