# To-Do App in 7 Days — Spring Boot

A beginner-friendly Spring Boot + MySQL + REST API + browser frontend project based on the supplied 7-day trainer guide.

## Stack

- Java 21
- Spring Boot 3.5.16
- Maven
- Spring Web
- Spring Data JPA
- MySQL Driver
- Bean Validation
- HTML + CSS + JavaScript
- Postman

Spring Boot 3.5 supports Java 21, and Java 21 is explicitly supported by the Spring Boot 3.5 line. See the official system requirements for details.

## 1. MySQL setup

Open MySQL Workbench and run:

```sql
CREATE DATABASE todo;
SHOW DATABASES;
```

The project is configured for:

```text
Host: localhost
Port: 3306
Database: todo
Username: root
Password: 9696
```

If your MySQL root password is different, edit:

`src/main/resources/application.properties`

Change only:

```properties
spring.datasource.password=YOUR_PASSWORD
```

## 2. Open in IntelliJ

Open the project folder in IntelliJ IDEA.

Wait for Maven to resolve the dependencies. Then open:

`src/main/java/com/sheat/todo/TodoApplication.java`

Right-click the file → Run `TodoApplication`.

Expected console text contains:

```text
Started TodoApplication
```

## 3. Open the web page

After the application starts, open:

```text
http://localhost:8080/
```

The browser page lets you:

- add tasks
- list tasks
- filter by date
- change status
- delete tasks

Every action uses the REST API and is persisted in MySQL.

## 4. REST endpoints

| Method | URL | Purpose |
|---|---|---|
| POST | `/api/tasks` | Create a task |
| GET | `/api/tasks` | Get all tasks |
| GET | `/api/tasks/{id}` | Get one task |
| GET | `/api/tasks/by-date?date=2026-09-25` | Filter by date |
| PUT | `/api/tasks/{id}` | Update complete task |
| PATCH | `/api/tasks/{id}/status?status=FINISHED` | Update status |
| DELETE | `/api/tasks/{id}` | Delete task |

Allowed status values:

```text
ONGOING
FINISHED
NOT_COMPLETED
REJECTED
```

## 5. Postman

Import:

`postman/TodoAPI.postman_collection.json`

The collection includes all seven API operations plus validation and not-found demos.

Default collection variables:

```text
baseUrl = http://localhost:8080
taskId = 1
taskDate = 2026-09-25
```

## 6. Project structure

```text
src/main/java/com/sheat/todo
├── controller
│   └── TaskController.java
├── entity
│   ├── Task.java
│   └── TaskStatus.java
├── exception
│   ├── GlobalExceptionHandler.java
│   └── TaskNotFoundException.java
├── repository
│   └── TaskRepository.java
├── service
│   └── TaskService.java
└── TodoApplication.java
```

## 7. Day-wise mapping

### Day 1
MySQL database, Spring Boot project generation, package structure, MySQL connection.

### Day 2
`Task` entity + `TaskStatus` enum. Hibernate creates the `tasks` table.

### Day 3
`TaskRepository` + `TaskService`. Java code stores data in MySQL through JPA.

### Day 4
`TaskController`, POST + GET, Postman collection, browser API check.

### Day 5
Full CRUD + date filter + status update.

### Day 6
Validation + custom `TaskNotFoundException` + global exception handler.

### Day 7
Static frontend with table, add-task form, status dropdown, date filter, delete button, and submission files.

## 8. Validation examples

### Empty title
Returns HTTP `400 Bad Request` with a short JSON message such as:

```json
{
  "title": "Title is required"
}
```

### Missing task
`GET /api/tasks/9999` returns HTTP `404 Not Found` when the ID does not exist:

```json
{
  "error": "Task not found with id: 9999"
}
```

## 9. Common errors

**Access denied for user 'root'@'localhost'**

Check the password in `application.properties`.

**Unknown database 'todo'**

Run:

```sql
CREATE DATABASE todo;
```

**Communications link failure**

Make sure the MySQL server is running.

**Port 8080 already in use**

Stop the old app, or change:

```properties
server.port=8081
```

Then use `http://localhost:8081/`.

**404 on `/api/tasks`**

Check that the application is running and the URL is exactly:

`http://localhost:8080/api/tasks`

**415 Unsupported Media Type**

In Postman choose Body → raw → JSON and keep the `Content-Type: application/json` header.

**Cannot deserialize LocalDate**

Use `YYYY-MM-DD`, for example `2026-09-25`.

**Cannot deserialize LocalTime**

Use `HH:mm:ss`, for example `18:30:00`.

## 10. Trainer/demo note

The code keeps the guide's package/class/field names to reduce classroom confusion. The frontend is served by Spring Boot itself from `src/main/resources/static/index.html`, so a separate frontend server is not required.
