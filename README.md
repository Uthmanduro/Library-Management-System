# Library Management System

##  Project Overview
The **Library Management System** is a Java-based application that allows users to manage books, members, and borrowing records. The system is designed to evaluate proficiency in:
- JDBC with the DAO pattern
- File handling
- Java Collections Framework

---

##  Project Requirements

### **1. JDBC using the DAO Pattern**
- Connect to a database (MySQL/PostgreSQL) using JDBC.
- Implement the DAO (Data Access Object) pattern to separate database logic from business logic.
- **Database Schema:**
    - **Books:** `book_id`, `title`, `author`, `genre`, `available_copies`
    - **Members:** `member_id`, `name`, `email`, `phone`
    - **Borrowing Records:** `record_id`, `book_id`, `member_id`, `borrow_date`, `return_date`

### **2. File Handling**
- Use `BufferedReader` and `BufferedWriter` to log system activities (e.g., book borrowing, returning, errors) into `library_log.txt`.
- Implement CSV export for book/member details (`books.csv` or `members.csv`).

### **3. Java Collections Framework**
- Use collections (`ArrayList`, `HashMap`) to manage data before persisting it to the database.
- Implement:
    - Searching for books by title or author.
    - Sorting books by title or genre (`Comparator` or `Comparable`).
    - Tracking borrowed books using a `HashMap` (`book_id -> member_id`).

---

## ⚙ Functional Requirements

### **1. Book Management**
- Add, update, delete books.
- Search books by title, author, or genre.
- Display all books.

### **2. Member Management**
- Add, update, delete members.
- Display all members.

### **3. Borrowing Management**
- Borrow a book (decrease available copies, add record).
- Return a book (increase available copies, update record).
- Display all borrowing records.

### **4. Logging and Reporting**
- Log book borrowing, returning, and errors in `library_log.txt`.
- Export book/member details into CSV files.

---

##  Assessment Criteria
- **Functionality:** Meets all functional requirements.
- **Code Quality:** Clean, modular, and well-documented code.
- **Use of JDBC and DAO Pattern:** Proper DAO pattern implementation.
- **File Handling:** Correct implementation of file operations.
- **Java Collections Framework:** Effective use of collections for data management.
- **Error Handling:** Graceful handling of exceptions.

---

##  Getting Started

### **1. Clone the Repository**
```sh
git clone https://github.com/Uthmanduro/Library-Management-System.git
cd Library-Management-System
```

### **2. Configure Database**
- Create a database in MySQL/PostgreSQL.
- Update `DatabaseConnection.java` with your database credentials.

### **3. Run the Application**
```sh
javac Main.java
java Main
```

---

## 📝 License
This project is open-source and available under the **MIT License**.

---

## 📩 Contact
For questions or suggestions, contact [Uthmanduro](https://github.com/Uthmanduro).

