--Создание таблицы USERS для авторизации JWT.
CREATE TABLE USERS
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(2000) NOT NULL UNIQUE,
    password VARCHAR(2000) NOT NULL,
    enabled  BOOLEAN DEFAULT TRUE
);