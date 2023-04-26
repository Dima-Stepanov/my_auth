# RestFul API архитектура.

### RestFul API архитектура - это архитектура клиент-серверного приложения, базирующаяся на 6 принципах.

<h3>Описание проекта</h3>
<h6>
Учебный проект демонстрирующий авторизацию JWT и так же валидацию, обработку исключений, <br>
на основе RestFul API. В проекте демонстрируется CRUD операции, <br>
Так демонстрируется разграничение прав доступа авторизации и регистрации. Получение токена авторизации. <br>
Валидация входных параметров на уровне контроллера. <br>
Обработка исключения на уровне глобальных обработчиков контроллеров и локальных специфических ответов ResponseEntity.
</h6>

<h3>Стек технологий </h3>
Java 17 <br>
PostgreSQL 14 <br>
Spring BOOT 2.7.4 <br>
Spring data jpa <br>
Spring security <br>
Java JWT  <br>
Spring test <br>
Liqubase 4.15 <br>
Hibernate 5.6.11 <br>
Lombok 1.18 <br>
REST API <br>

<h3>Требование к окружению</h3>
JDK 17, Maven 3.8, PostgreSQL 14. Postman v.10<br>

<h3>Запуск приложения</h3>

1. Создайте базу данных auth_db при помощи консоли PostgreSQL или терминала pgAdmin:<br>
   """CREATE DATABASE auth_db"""
2. Скопировать проект из репозитория по ссылке:
   <a href=https:https://github.com/Dima-Stepanov/my_auth><b>Проект авторизация</b></a>
3. Перейдите в корень проекта и при помощи Maven соберите проект командой:<br>
   """mvn install"""
4. После успешной сборки проекта перейдите в каталог собранного проекта <b>target</b> и запустите приложение
   командой:<br>
   """java -jar my_auth-0.0.1-SNAPSHOT.jar"""
5. Все операции осуществляются через POSTMAN

<h3>Взаимодействие с приложением</h3>
Взаимодействие запросы и ответы через Postman

![01_reister_user.jpg](img%2F01_reister_user.jpg) <br>

Рисунок 1 регистрация пользователя <br>

![02_login_user.jpg](img%2F02_login_user.jpg) <br>

Рисунок 2 авторизация пользователя <br>

![03_all_person.jpg](img%2F03_all_person.jpg) <br>

Рисунок 3 все сотрудники <br>

![04_delete_person.jpg](img%2F04_delete_person.jpg) <br>

Рисунок 4 удалить сотрудника <br>

![05_create_person.jpg](img%2F05_create_person.jpg) <br>

Рисунок 5 создать сотрудника <br>

![06_get_by_id_person.jpg](img%2F06_get_by_id_person.jpg) <br>

Рисунок 6 найти сотрудника <br>

![07_put_update_person.jpg](img%2F07_put_update_person.jpg) <br>

Рисунок 7 обновить сотрудника put <br>

![08_patch_update_person.jpg](img%2F08_patch_update_person.jpg) <br>

Рисунок 8 частично обновить сотрудника patch <br>

<h3>Контакты</h3>
email: <b>haoos@inbox.ru</b> <br>
tl: <a href=https://t.me/Dima202020><b>Dima202020<b></a> <br>
github.com: <a href=https://github.com/Dima-Stepanov><b>Dima-Stepanov<b></a>