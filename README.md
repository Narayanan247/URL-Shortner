# URL Shortener Application

## Overview
This is a simple URL shortener service built using **Java Spring Boot**. It allows users to convert long URLs into shorter keys that redirect to the original URLs. The project demonstrates concepts like REST API development, hashing, database interaction using JPA, and redirect handling.

---

## Features
- Shorten a single URL via a REST API endpoint.
- Redirect from the shortened URL key to the original long URL.
- Store URL mappings in an embedded H2 in-memory database.
- Basic collision handling for URL keys.
- Modular layered architecture (Controller, Service, Repository, Entity).

---

## Tech Stack
- Java 17+
- Spring Boot 3.5.4
- Spring Web
- Spring Data JPA
- H2 Database (In-memory for ease of dev/testing)
- Maven for build and dependencies management
- IntelliJ IDEA (recommended IDE)


---

## Setup and Running Locally

### Prerequisites
- Java 17 or higher installed and configured in PATH
- Maven installed to build project
- IDE (IntelliJ IDEA recommended)

### Steps
1. Clone the repository:
- git clone <repository_url>
- cd UrlShortener

2. Build the project:
- mvn clean install


3. Run the application:
- Using IntelliJ: Run `UrlShortenerApplication.java`
- Or via command line:
  ```
  mvn spring-boot:run
  ```

4. Application will start on:
- http://localhost:8080

---

## API Usage

### 1. Shorten URL

- **Endpoint:** `POST /shorten`
- **Description:** Shortens a given long URL.
- **Request Body:**
- {
"url": "https://www.example.com"
- }
- **Response:**
- {
"shortUrlKey": "abc123"
- }

### 2. Redirect Short URL

- **Endpoint:** `GET /{shortUrlKey}`
- **Description:** Redirects to the original URL using the short key.
- **Example:** Accessing `http://localhost:8080/abc123` will redirect to the original URL.

---

## Testing

- Use **Postman** or similar API client.
- To shorten a URL: Send POST request to `/shorten` with JSON URL.
- To test redirection: Open a browser and navigate to `http://localhost:8080/{shortUrlKey}`.

---

## Extending the Project

Possible enhancements include:

- Batch URL shortening support.
- User authentication and URL ownership.
- Expiry dates for shortened URLs.
- Click analytics and usage statistics.
- Custom short URL keys.
- Frontend interface.

---

## Troubleshooting

- Missing dependencies? Run `mvn clean install` and reload Maven in your IDE.
- Port conflicts? Change server port in `application.properties`:
- server.port=8081

- Change JDK version if needed in IDE or Maven config.

---



