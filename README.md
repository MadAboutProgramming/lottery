# Lottery System REST API
## Overview

This project implements a simple lottery system as a RESTful API using Spring Boot and WebFlux. The lottery system allows users to create and manage tickets, each containing a series of lines with three numbers. The results for each line are calculated based on predefined rules. The system also provides endpoints to amend tickets and retrieve their status.
### Lottery Rules

Each ticket contains several lines, with each line consisting of three numbers (0, 1, or 2). The result for each line is determined by the following rules:

    Sum Equals 2: If the sum of the three numbers on a line equals 2, the result for that line is 10.
    All Numbers Same: If all three numbers on a line are the same, the result is 5.
    First Number Different: If the first number is different from both the second and third numbers, the result is 1.
    Otherwise: The result is 0.

### Endpoints

The system exposes the following RESTful endpoints:

    POST /ticket: Create a new ticket with n lines.
    GET /ticket: Retrieve a list of all tickets.
    GET /ticket/{id}: Retrieve a specific ticket by its ID.
    PUT /ticket/{id}: Amend an existing ticket by adding n new lines.
    PUT /status/{id}: Retrieve the status of a ticket. Once the status is checked, the ticket can no longer be amended.

### Project Structure

    src/main/java/com/game/lottery: Contains the main application code, including the controller, service, model, and repository classes.
    src/test/java/com/game/lottery: Contains unit and integration tests written using WebFlux Test.

### Prerequisites

    Java 11 or higher
    Maven or Gradle
    Docker (optional, for containerized deployment)

### Getting Started
### Build and Run the Application
#### 1. Clone the Repository:
```
git clone https://github.com/your-repo/lottery-system.git
cd lottery-system
```
#### 2. Build the Application:

```
mvn clean install
```

#### 3. Run the Application:

```
mvn spring-boot:run
```

The application will start on http://localhost:8080.

### Running the Tests

The project includes JUnit tests that verify the functionality of the REST API.

#### 1. Run Tests:

```
mvn test
```
This will execute the unit and integration tests, including tests written using WebFlux.

## API Documentation

Swagger is integrated for API documentation. Once the application is running, you can access the Swagger UI at:

http://localhost:8080/swagger-ui/index.html