# Blockchain-Based Supply Chain Management System

A Java-based application implementing a **blockchain model** for achieving transparency, traceability, and security in supply chain processes.  
Built using:

- **Java (OOP)**
- **Java Swing (GUI)**
- **MySQL + JDBC**
- **Custom Blockchain Logic**
- **Multithreading & Synchronization**
- **DAO Pattern**

This project satisfies the **Java GUI-Based Project Marking Rubric** and is designed for academic demonstration and evaluation.

---

## Features

### Supply Chain Manager
- View the entire blockchain
- Monitor all supply chain transactions
- Validate block integrity
- Review compliance and history

### Supplier
- Record new supply transactions
- Add shipments to blockchain
- Update transaction status

### Retailer
- Track and verify product origin
- View complete product movement history
- Authenticate product legitimacy through blockchain

---

##  Blockchain Implementation

Each block contains:

- Index  
- Timestamp  
- Previous Hash  
- Current Hash  
- Nonce  
- List of transactions  

Blockchain is secured through:

- SHA-256 hashing  
- Block validation  
- Previous-hash linking  
- Proof-of-Work style mining (difficulty-based)

A **Genesis Block** is created automatically when the system starts.

---

## Object-Oriented Programming Used

### Inheritance  
`User` → `SupplyChainManager`, `Supplier`, `Retailer`

### Polymorphism  
Dashboard loading & behavior differ based on runtime user type.

### Interfaces  
Service abstraction and DAO pattern for cleaner modular code.

### Exception Handling  
Custom exceptions:
- `InvalidTransactionException`
- `BlockchainValidationException`

### ✔ Encapsulation  
All model classes encapsulate their fields with getters/setters.

---

## 💾 MySQL Database Schema

Tables used:

- **users**
- **products**
- **blocks**
- **transactions**

### Database Operations include:
- User login (authentication)
- Transaction insertion
- Block persistence
- Product traceability queries
- Blockchain verification queries

All operations follow the **DAO (Data Access Object)** pattern.

---

##  Multithreading

A background validator thread runs every 10 seconds:

- Verifies blockchain integrity  
- Ensures no tampering  
- Uses synchronized access to blockchain resources

---

##  GUI (Java Swing)

- Login Screen  
- Supply Chain Manager Dashboard  
- Supplier Dashboard  
- Retailer Dashboard  
- Scrollable blockchain viewer  
- Product traceability window  

All screens built using **Swing** with event-based listeners.

---

##  Project Structure

```
src/
└─ com/blockchain/supplychain/
├─ model/
├─ dao/
├─ service/
├─ ui/
├─ exception/
├─ util/
└─ Main.java
```
yaml
Copy code

---

## 🛠 Technologies Used

- Java 17+  
- Java Swing GUI  
- MySQL 8.x  
- JDBC  
- SHA-256 hashing  
- Eclipse IDE  
- Git & GitHub  

---

## How to Run the Project

### 1. Clone the repository:
git clone https://github.com/SIAkash07/blockchain-supply-chain.git

shell
Copy code

### 2. Import into Eclipse:
File → Import → Existing Projects into Workspace

php
Copy code

### 3. Add MySQL Connector/J to Classpath  
(Eclipse → Build Path → Add External JAR → Add to Classpath)

### 4. Configure Database  
Edit `DBConnection.java`:

java
```
private static final String URL = "jdbc:mysql://localhost:3306/blockchain_supplychain";
private static final String USER = "root";
private static final String PASSWORD = "your_password";
```

### 5. Run the project
Execute:
`Main.java`

