
## Authify - Backend Only

<a href="https://www.linkedin.com/in/natalia-feitosa-cga-cfp" target="_blank" style="text-decoration:none">
    <img src="https://img.shields.io/badge/LinkedIn-000000?style=for-the-badge&logo=linkedin&logoColor=white" alt="LinkedIn">
</a>

## Overview
This is a **Java 21** application built with **Spring Boot**, designed to handle authentication and security services. 

The backend provides powerful APIs for authentication and authorization, integrating the following technologies.

This project is designed to function as a **microservices-based authentication system**.

## Features
- **User registration** with secure credential storage.
- **Login authentication** using **Spring Boot Security** and **JWT tokens**.
- **OTP generation and delivery** via **Starter Mail (Brevo)** for password recovery.
- **Password reset functionality** to enhance security.
- **Microservices-oriented architecture** for scalable authentication solutions.
- **JPA** for efficient database interaction.
- **MySQL** for data persistence.
- Unit and integration tests with **Mockito** and **JUnit**.

## Prerequisites
To set up this project, ensure you have the following installed:

- **Java 21**
- **Maven**
- **MySQL** (running locally or configured remotely)

## Setup Instructions
### 1. Clone the repository:
```bash
git clone https://github.com/tecnologiaefinancas/authify
cd authify
```

### 2. Configure the application:
Update the `application.properties` file with your MySQL credentials and email configuration.

Example:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/<database>
spring.datasource.username=<your-username>
spring.datasource.password=<your-password>
spring.jpa.hibernate.ddl-auto=update

jwt.secret=<JWT_SECRET_KEY>
jwt.expiration=86400000

spring.mail.host=smtp-relay.brevo.com
spring.mail.port=587
spring.mail.username=<your-username>
spring.mail.password=<your-password>
```

### 3. Build and run the application:
```bash
mvn clean install
mvn spring-boot:run
```

## Technology Stack
- **Java 21**
- **Spring Boot**
    - Spring Data JPA
    - Spring Web
    - Spring Security
- **MySQL**
- **JWT Token**
- **Starter Mail (Brevo)**
- **Mockito & JUnit** for testing

## Acknowledgment
Special thanks to **Bushan** for the valuable lessons that inspired this project before further improvements.