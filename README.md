# spring-jwt-auth-example
Пример работы с jwt в Spring Security

Перед запуском приложение ожидает переменную окружения `JWT_SECRET`,
которая используется для подписи JWT‑токенов. Срок жизни refresh‑токена
настраивается свойством `jwt.refresh-ttl` (в секундах) в `application.yml`.

Проект содержит простой `Dockerfile` для сборки контейнера:

```bash
docker build -t spring-jwt .
```
