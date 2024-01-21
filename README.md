# sber-demo-project

## Описание

Реализован сервис с операциями CRUD над базой данных мобильных телефонов.
База данных поднимается в отдельном контейнере, сборка с docker-compose.
* База данных: postgresql
* Документация API: swagger
* Java 17
* Maven
* Покрытие тестами 70%

## Swagger документация

UI:     http://localhost:8080/swagger-ui/index.html

JSON:   http://localhost:8080/v3/api-docs

## Запуск приложения
### Требования
* Docker
* docker-compose

### Запуск
Сборка и запуск осуществляется командой

`make docker_full_up`