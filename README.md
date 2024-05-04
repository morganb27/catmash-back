# CatMash API

CatMash is a Spring Boot-based application that provides a platform for users to vote on their favorite cat pictures in a fun and interactive manner. This backend handles all data management and API services for the CatMash web application.

## Features

- REST API to retrieve cat pictures and vote counts.
- Endpoint to increment vote counts for individual cats.
- Secure API endpoints using Spring Security.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

Before you begin, ensure you have the following installed:
- JDK 17 or later
- Maven (to handle project dependencies and building)
- PostgreSQL (for the database)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/morganb27/catmash-back.git
   cd catmash

2. Configure PostgreSQL:
- Create a database named catmash.
- Update the application.properties with your database credentials.

3. Build the project
    ```bash
    mvn clean install
    ```

4. Run the application
    ```bash
    mvn spring-boot:run
   ```
### API Endpoints

- GET /cats - Retrieves all cats along with their vote counts.
- PATCH /cats/{id}/vote - Increments the vote count for the specified cat.

