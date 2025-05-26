# Order Processing System – Group 1

## 📦 Описание

Это учебный проект для реализации упрощённого обработчика заказов, состоящий из нескольких микросервисов на Spring Boot. В рамках упражнения реализуются:

* Приём и валидация заказов (Сервис A)
* Отправка заказов в очередь RabbitMQ с рандомной задержкой (0–5000 мс)
* Проверка заказов через Camunda (условие: `total > 0`) (Сервис B)
* Сохранение данных в PostgreSQL

---

## 🚀 Быстрый старт

1. Убедитесь, что у вас установлен [Docker](https://docs.docker.com/get-docker/) и [Docker Compose](https://docs.docker.com/compose/install/)
2. В корне проекта выполните:

   ```bash
   docker compose up --build
   ```
3. Swagger UI доступен по адресу:
   [http://localhost:8060/swagger-ui/index.html](http://localhost:8060/swagger-ui/index.html)

---

## 🛠 Структура

```
.
├── serviceA/            # HTTP API + PostgreSQL + RabbitMQ Producer
├── serviceB/            # Camunda Consumer
├── docker-compose.yml   # Общий конфиг
└── README.md
```

---

## 🧾 Описание задачи (группа 1)

Сервис A

Сервис A отвечает за приём, валидацию заказов и отправку их в очередь RabbitMQ с рандомной задержкой (0–5000 мс).

### Сервис B (Camunda)

* Консьюмит заказы из RabbitMQ.
* Отправляет их в Camunda для проверки по условию `total > 0`.
* Сохраняет результат (`approved`/`rejected`) в PostgreSQL.
* По запросу Сервиса A выдаёт результат проверки.

---

## ⚙️ Технологии

* Java 17
* Spring Boot (Web, Data JPA, AMQP, Security)
* Gradle
* PostgreSQL
* RabbitMQ
* Camunda 8
* Swagger (Springdoc)

---
