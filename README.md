
# 🚀 Roadmap.AI – User Authentication & Management API

**Roadmap.AI** is a Spring Boot-based backend application that provides secure **user authentication** and **user management APIs** using **JWT (JSON Web Token)** authentication. It supports user registration, login, listing users, finding a user by ID, and deleting users securely.

---

## 🔐 Features

- ✅ User Registration (`/register`)
- 🔐 User Login with JWT (`/login`)
- 👥 Get All Users (`/users`)
- 🔎 Find User by ID (`/users/{id}`)
- ❌ Delete User by ID (`/users/{id}`)
- 🔑 JWT Token-based Authorization for secured endpoints

---

## 📂 Project Structure

```text
roadmap-ai/
├── controller/             # API endpoint controllers
├── service/                # Business logic layer
├── model/                  # Entity classes (e.g., User)
├── repository/             # Spring Data JPA repositories
├── security/               # JWT filters, configs, and token provider
├── util/                   # Utility classes
└── RoadmapAiApplication.java  # Main entry point
```

---

## 🧑‍💻 How to Use This Project

Follow these steps to set up and run the project on your local machine.

### ✅ Prerequisites

- Java 17+
- Maven
- MySQL (or H2 if configured)
- Postman or any API testing tool

---

### 🛠️ Step-by-Step Setup

#### 1️⃣ Clone the Repository

```bash
git clone https://github.com/gautamrawat543/Roadmap.AI.git
cd Roadmap.AI
```

#### 2️⃣ Configure Application Properties

Open `src/main/resources/application.properties` and set your database and JWT secret:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/roadmap_ai
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

jwt.secret=your_jwt_secret_key
jwt.expiration=3600000
```

> 💡 Use H2 for in-memory DB if you're just testing.

---

#### 3️⃣ Build & Run the App

```bash
mvn spring-boot:run
```

Your app will start on `http://localhost:8080`

---

## 🔑 JWT Authentication Flow

1. **Register a User**
   - Endpoint: `POST /register`
   - Sample Body:
     ```json
     {
       "username": "gautam",
       "password": "securepassword"
     }
     ```

2. **Login to Get JWT Token**
   - Endpoint: `POST /login`
   - Sample Body:
     ```json
     {
       "username": "gautam",
       "password": "securepassword"
     }
     ```

   - Response:
     ```json
     {
       "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
     }
     ```

3. **Use Token for Secured Endpoints**
   - Include the token in the header:
     ```
     Authorization: Bearer <your-jwt-token>
     ```

4. **Example Secured Requests**
   - `GET /users`
   - `GET /users/{id}`
   - `DELETE /users/{id}`

---

## 🧪 Example API Testing with Postman

| Method | Endpoint         | Body Required | Auth Required |
|--------|------------------|---------------|----------------|
| POST   | `/register`      | ✅ Yes        | ❌ No          |
| POST   | `/login`         | ✅ Yes        | ❌ No          |
| GET    | `/users`         | ❌ No         | ✅ Yes         |
| GET    | `/users/{id}`    | ❌ No         | ✅ Yes         |
| DELETE | `/users/{id}`    | ❌ No         | ✅ Yes         |

---

## 🔐 Security Design

- **Password Encryption** using `BCryptPasswordEncoder`
- **Stateless Authentication** with JWT
- **Custom Filters & Handlers** for token parsing and exception handling

---

## 📃 License

This project is licensed under the MIT License.

---

## 👤 Author

- **Gautam Rawat**  
  GitHub: [@gautamrawat543](https://github.com/gautamrawat543)

---

> 🛡️ *This project is ideal as a backend base for future full-stack applications needing user authentication and access control.*
