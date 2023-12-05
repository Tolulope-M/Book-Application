# Book Application

The Book Application is a Java Spring Boot application for managing books and categories. It uses an H2 database and defaults to starting on port 8080.

## Table of Contents

- [Features](#features)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
- [Usage](#usage)
- [Documentation](#documentation)
    - [Postman Collection](#postman-collection)
    - [Swagger UI](#swagger-ui)

## Features

- Add, edit, delete books
- Add, edit, delete book categories
- Get all books and categories
- Add books to category
- Add book to favourite
- Get all favourite books

### Prerequisites

- Java Development Kit (JDK) 17
- Maven
- Java IDE (IntelliJ IDEA)

### Installation

1. Clone the repository:

   ```bash
   https://github.com/Tolulope-M/BookApplication.git
   
   cd book-application

   mvn clean install
 
   java -jar target/Book-Application.jar

## Documentation

### Postman Collection

Explore and test the API using [Postman Collection](https://documenter.getpostman.com/view/22898298/2s9YeLY9hY)

### Swagger UI

Interactive API documentation available via Swagger UI: [Swagger UI](http://localhost:8080/swagger-ui/index.html)

