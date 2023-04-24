--Создание таблицы persons
CREATE TABLE persons
(
    id SERIAL PRIMARY KEY,
    login VARCHAR(2000) not null unique,
    password VARCHAR(2000) not null
);