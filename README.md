https://github.com/user-attachments/assets/c1eb4a56-7c41-45a5-82af-74ce96e3177a


# Hostel Management System - Microservices Project

## Table of Contents

1. [Project Overview](#project-overview)
2. [Architecture](#architecture)
3. [Technologies Used](#technologies-used)
4. [Services Description](#services-description)
5. [Database Schemas](#database-schemas)
6. [Dependencies](#dependencies)
7. [Setup Instructions](#setup-instructions)
8. [Usage](#usage)
9. [APIs](#apis)
10. [Future Enhancements](#future-enhancements)

---

## Project Overview

The **Hostel Management System** is a microservices-based application designed to manage hostel operations effectively. It streamlines processes such as student management, room assignment, and payment tracking. Built using Spring Boot, the project leverages Eureka for service discovery, and microservices communicate seamlessly using REST APIs.

### Key Features:
- **Student Management**: Handles student records and assigns rooms dynamically.
- **Room Management**: Tracks room availability and prevents overbooking.
- **Payment Management**: Manages payment records for students, ensuring accurate tracking.

---

## Architecture

### High-Level Design:
1. **Eureka Server**: Acts as a service registry to discover and manage microservices.
2. **Student Service**: Manages student records and assigns rooms in real time.
3. **Room Service**: Tracks room availability and ensures room capacity is maintained.
4. **Payment Service**: Handles fee payments and payment history.

### Communication:
- Services communicate using REST APIs.
- Inter-service requests are made using `RestTemplate`.

---

## Technologies Used

- **Spring Boot 3.x**
- **Spring Cloud Netflix Eureka**
- **Spring Data JPA**
- **MySQL**
- **Postman** (for API testing)

---

## Services Description

### 1. **Eureka Server**:
The service registry that enables other services to register and discover each other dynamically.

### 2. **Student Service**:
Manages the records of students and assigns rooms.
- Endpoints to create, update, and retrieve student details.
- Notifies the Room Service when a student is assigned a room.

### 3. **Room Service**:
Manages room availability and occupancy.
- Tracks assigned students and prevents overbooking.
- Provides APIs to create and manage room details.

### 4. **Payment Service**:
Tracks fee payments for students.
- Provides APIs for adding, updating, and retrieving payment records.
- Maintains payment history for future reference.

---

## Database Schemas

### `students` Database Schema
The `students` database stores information about students.

**Table: students**
| Column       | Type        | Constraints           |
|--------------|-------------|-----------------------|
| id           | BIGINT      | PRIMARY KEY, AUTO_INCREMENT |
| name         | VARCHAR(255)| NOT NULL             |
| email        | VARCHAR(255)| UNIQUE, NOT NULL     |
| roomNumber   | VARCHAR(50) |                       |

### `rooms` Database Schema
The `rooms` database stores room information.

**Table: rooms**
| Column       | Type        | Constraints           |
|--------------|-------------|-----------------------|
| id           | BIGINT      | PRIMARY KEY, AUTO_INCREMENT |
| roomNumber   | VARCHAR(50) | UNIQUE, NOT NULL     |
| capacity     | INT         | NOT NULL             |
| assignedStudents | INT     |                       |
| availability | BOOLEAN     | NOT NULL             |
| hostelName   | VARCHAR(255)|                       |

### `payments` Database Schema
The `payments` database stores payment details.

**Table: payments**
| Column       | Type        | Constraints           |
|--------------|-------------|-----------------------|
| id           | BIGINT      | PRIMARY KEY, AUTO_INCREMENT |
| studentId    | BIGINT      | FOREIGN KEY           |
| amount       | DOUBLE      | NOT NULL             |
| paymentMethod| VARCHAR(50) |                       |
| paymentDate  | DATE        | NOT NULL             |

---

## Dependencies

### Common Dependencies (for all services):
- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- Spring Boot Starter Test
- MySQL Driver

### Eureka Server:
- Spring Cloud Netflix Eureka Server

### Student, Room, and Payment Services:
- Spring Cloud Netflix Eureka Client

---

## Setup Instructions

### Prerequisites:
- Java 17 or higher
- MySQL installed and running
- Maven

### Steps to Run:
1. **Clone the Repository:**
   ```bash
   git clone <repository_url>
   cd hostel-management-system
   ```

2. **Run Eureka Server:**
   Navigate to the `eureka-server` folder and start the application:
   ```bash
   mvn spring-boot:run
   ```

3. **Run Services:**
   - Update the `application.properties` with MySQL credentials for each service.
   - Start each service using:
     ```bash
     mvn spring-boot:run
     ```

4. **Test the Services:**
   Use Postman or any API client to test the endpoints.

---

## Usage

### Step 1: Add a Room
- **Endpoint:** `POST /rooms`
- **Payload:**
  ```json
  {
    "roomNumber": "101",
    "capacity": 2,
    "assignedStudents": 0,
    "availability": true,
    "hostelName": "Hostel A"
  }
  ```

### Step 2: Add a Student
- **Endpoint:** `POST /students`
- **Payload:**
  ```json
  {
    "name": "John Doe",
    "email": "john.doe@example.com",
    "roomNumber": "101"
  }
  ```

### Step 3: Record Payment
- **Endpoint:** `POST /payments`
- **Payload:**
  ```json
  {
    "studentId": 1,
    "amount": 5000.0,
    "paymentMethod": "Online",
    "paymentDate": "2025-01-15"
  }
  ```

---

## APIs

### Student Service
| Method | Endpoint           | Description              |
|--------|--------------------|--------------------------|
| GET    | /students/getAll   | Fetch all students       |
| POST   | /students          | Add a new student        |
| GET    | /students/{id}     | Fetch student by ID      |

### Room Service
| Method | Endpoint           | Description              |
|--------|--------------------|--------------------------|
| GET    | /rooms/getAll      | Fetch all rooms          |
| POST   | /rooms             | Add a new room           |
| PUT    | /rooms/assignStudent | Assign a student to a room |

### Payment Service
| Method | Endpoint           | Description              |
|--------|--------------------|--------------------------|
| GET    | /payments/getAll   | Fetch all payments       |
| POST   | /payments          | Add a new payment        |

---

## Future Enhancements

- Implement authentication and authorization using JWT.
- Add a web-based interface for easier user interaction.
- Integrate cloud-based deployment using Docker and Kubernetes.
- Enhance logging and monitoring with tools like ELK Stack.

