# 💰 Money Controller

A Spring Boot-based personal finance management application that helps users track budgets, expenses, incomes, goals, categories, and transactions.

---

## 📋 Features
- Budget management (create, view, update, delete)
- Expense and income tracking
- Goal setting and progress tracking
- Transaction management
- Category management
- User management
- Pagination and sorting for large data sets
- Logging to file for monitoring and debugging
- RESTful API endpoints for all operations

---

## 🛠️ Tech Stack
| Technology | Purpose |
|---|---|
| Java 17+ | Core language |
| Spring Boot | Application framework |
| Spring Data JPA (Hibernate) | ORM / Data access |
| MySQL | Relational database |
| Maven | Build tool |
| Swagger | API documentation |

---

## 📁 Project Structure
```
src/
  main/
    java/com/springboot/moneyy/
      Controller/    # REST controllers
      Service/       # Business logic
      Repository/    # Data access
      Entity/        # JPA entities
      Logger/        # Logging aspect
      Configuration/ # App configuration (e.g., Swagger)
    resources/
      application.properties
  test/
    java/com/springboot/moneyy/
      MoneyyApplicationTests.java
```
---

## ✅ Prerequisites
Make sure you have the following installed before running the project:
- Java 17+
- MySQL (running locally)
- Maven

---

## 🚀 Getting Started

1. **Clone the repository:**
```bash
   git clone https://github.com/srija0501/Money-Controller.git
   cd Money-Controller
```

2. **Configure MySQL** in `src/main/resources/application.properties`:
```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/moneycontroller
   spring.datasource.username=your_mysql_username
   spring.datasource.password=your_mysql_password
   spring.jpa.hibernate.ddl-auto=update
```

3. **Build and run the application:**
```bash
   ./mvnw spring-boot:run
```
   Or run `MoneyyApplication.java` directly from your IDE.

4. The app runs at **http://localhost:8080** by default.

---

## 📡 API Endpoints

### Budget
| Method | Endpoint | Description |
|---|---|---|
| GET | `/budgets` | Get all budgets |
| GET | `/budgets/pagesort` | Get paginated & sorted budgets |
| GET | `/budgets/page?page=0&size=3` | Get budgets with custom page size |
| POST | `/budgets` | Create a new budget |
| PUT | `/budgets/{id}` | Update a budget |
| DELETE | `/budgets/{id}` | Delete a budget |

### Expense
| Method | Endpoint | Description |
|---|---|---|
| GET | `/expenses` | Get all expenses |
| POST | `/expenses` | Add a new expense |

### Income
| Method | Endpoint | Description |
|---|---|---|
| GET | `/incomes` | Get all incomes |
| POST | `/incomes` | Add a new income |

### Goals
| Method | Endpoint | Description |
|---|---|---|
| GET | `/goals` | Get all goals |
| POST | `/goals` | Add a new goal |

> 💡 Use **Postman** or **Thunder Client** to test the endpoints.

---

## 📝 Usage Example

**Add a new budget** — send a `POST` request to `/budgets`:

```json
{
  "name": "Groceries",
  "amount": 5000
}
```

**Response:**
```json
{
  "id": 1,
  "name": "Groceries",
  "amount": 5000
}
```

Then verify with `GET /budgets`.

---

## 👩‍💻 Author

**Srija Velusamy** — [GitHub](https://github.com/srija0501)