# URL Shortener with Analytics

A REST API built with Spring Boot that shortens long URLs and tracks click analytics.

## Tech Stack
- Java 17
- Spring Boot 3.4
- Spring Data JPA
- MySQL
- Maven

## Features
- Generate a short code for any long URL
- Track how many times a short URL was clicked
- View analytics for any short URL
- Get all stored URLs

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/urls` | Create a short URL |
| GET | `/api/urls` | Get all URLs |
| GET | `/api/urls/{shortCode}` | Redirect and track click |
| GET | `/api/urls/{shortCode}/analytics` | Get click analytics |

## Request Example

POST `/api/urls`
```json
{
  "originalUrl": "https://www.google.com"
}
```

## Response Example
```json
{
  "id": 1,
  "originalUrl": "https://www.google.com",
  "shortCode": "a1b2c3",
  "clickCount": 0,
  "createdAt": "2026-06-06T12:00:00"
}
```

## Setup
1. Clone the repo
2. Create a MySQL database named `urlshortener`
3. Add your DB credentials in `application.properties`
4. Run the project with Maven