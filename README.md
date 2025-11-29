<div align="center">

# 📚 LibraZone
### Full-Stack Library Management System

<img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java"/>
<img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" alt="Spring Boot"/>
<img src="https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL"/>
<img src="https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white" alt="Thymeleaf"/>
<img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white" alt="Maven"/>

<p align="center">
  <strong>A robust and modern system built to streamline all library administration and book distribution processes.</strong>
</p>

[Features](#-core-modules--functionality) • [Tech Stack](#-tech-stack--dependencies) • [Installation](#-getting-started) • [Screenshots](#-system-screenshots)

---

</div>

## ✨ Project Showcase & Technical Highlights

> 💡 **This project represents my first major full-stack application** built using the Java enterprise ecosystem. It focuses on **clean code architecture** and **complex business logic implementation**.

<br>

## 🎯 Core Modules & Functionality

<table>
<tr>
<td width="50%">

### 🔐 Admin Security
```
✅ Authenticated Login System
✅ Admin Registration Portal
✅ Unique ID Generation
✅ Role-Based Access Control
```
Ensures secure, role-based access to the entire system with encrypted credentials.

</td>
<td width="50%">

### 📖 Books & Inventory
```
✅ Complete CRUD Operations
✅ Dynamic Stock Tracking
✅ Total vs Available Copies
✅ Author & Genre Management
```
Real-time inventory tracking with comprehensive book metadata management.

</td>
</tr>

<tr>
<td width="50%">

### 👥 Library Members
```
✅ Full Member CRUD
✅ Contact Management
✅ Email Integration
✅ Eligibility Tracking
```
Comprehensive member database with contact details and borrowing history.

</td>
<td width="50%">

### 📅 Issue & Return Tracking
```
✅ Issue Date Logging
✅ Auto Due Date Calculation
✅ Return Date Recording
✅ Complete History Tracking
```
Central repository for all book distribution and return operations.

</td>
</tr>
</table>

<br>

## 🧠 Advanced Backend Logic

<div align="center">

| Feature | Technical Implementation |
|---------|-------------------------|
| 🔍 **Multi-Parameter Search** | Implemented sophisticated search capabilities across all entities using custom SQL queries. Search by ID, Name, Title, Author, or Genre with LIKE operations for partial matching. |
| 💰 **Progressive Fine Calculator** | Custom business logic with escalating fine structure: **₹50/day** base rate, increasing by **₹50 every week** of delay. Calculates automatically on return. |
| 🏗️ **MVC Architecture** | Strict adherence to Spring Boot MVC pattern with clear separation: **Controllers** → **Services** → **Repositories** → **Models** |
| 📊 **Dynamic SQL Queries** | Hand-crafted JDBC queries with proper exception handling, connection pooling, and result set mapping for optimal performance. |
| ⚡ **Real-Time Updates** | Instant inventory updates when books are issued or returned, maintaining data consistency across all modules. |

</div>

<br>

## 🛠️ Tech Stack & Dependencies

<table>
<tr>
<th>Layer</th>
<th>Technology</th>
<th>Purpose</th>
</tr>

<tr>
<td rowspan="2"><strong>🔧 Backend</strong></td>
<td><img src="https://img.shields.io/badge/Java_17-ED8B00?style=flat&logo=openjdk&logoColor=white"/></td>
<td>Core programming language with modern features</td>
</tr>
<tr>
<td><img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=flat&logo=spring-boot&logoColor=white"/></td>
<td>Enterprise framework for rapid development</td>
</tr>

<tr>
<td rowspan="2"><strong>💾 Database</strong></td>
<td><img src="https://img.shields.io/badge/MySQL-005C84?style=flat&logo=mysql&logoColor=white"/></td>
<td>Relational database for persistent storage</td>
</tr>
<tr>
<td><img src="https://img.shields.io/badge/JDBC-ED8B00?style=flat&logo=java&logoColor=white"/></td>
<td>Direct database access in Repository layer</td>
</tr>

<tr>
<td><strong>🎨 Frontend</strong></td>
<td><img src="https://img.shields.io/badge/Thymeleaf-005F0F?style=flat&logo=thymeleaf&logoColor=white"/></td>
<td>Server-side template engine for dynamic views</td>
</tr>

<tr>
<td><strong>🔨 Build Tool</strong></td>
<td><img src="https://img.shields.io/badge/Maven-C71A36?style=flat&logo=apache-maven&logoColor=white"/></td>
<td>Dependency management and project building</td>
</tr>
</table>

<br>

## 🚀 Getting Started

### 📋 Prerequisites

Before running LibraZone, ensure you have the following installed:

- ☕ **Java 17+** (or compatible JDK)
- 🔨 **Maven** (3.6 or higher)
- 🐬 **MySQL Server** (8.0 or higher)

<br>

### 📥 Installation Steps

#### 1️⃣ Clone the Repository
```bash
git clone https://github.com/yourusername/librazone.git
cd librazone
```

#### 2️⃣ Database Setup
Create a MySQL database and configure the connection:
```sql
CREATE DATABASE library;
USE library;
```

Update `src/main/resources/application.properties`:
```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/library
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Server Configuration
server.port=8080
```

#### 3️⃣ Build the Project
```bash
mvn clean install
```

#### 4️⃣ Run the Application
```bash
mvn spring-boot:run
```

Or run the JAR directly:
```bash
java -jar target/LibraZone-1.0.jar
```

#### 5️⃣ Access the Application
Open your browser and navigate to:
```
http://localhost:8080
```

<br>

## 📂 Project Structure
```
librazone/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── riku/spring/
│   │   │       ├── controllers/     # MVC Controllers
│   │   │       ├── models/          # Entity Models
│   │   │       ├── repository/      # Data Access Layer (JDBC)
│   │   │       ├── services/        # Business Logic Layer
│   │   │       └── LibraZoneApplication.java
│   │   │
│   │   └── resources/
│   │       ├── static/              # CSS, JS, Images
│   │       ├── templates/           # Thymeleaf HTML Templates
│   │       └── application.properties
│   │
│   └── test/                        # Unit Tests
│
├── pom.xml                          # Maven Dependencies
└── README.md
```

<br>

## 📸 System Screenshots

<details>
<summary>🖼️ Click to view screenshots</summary>

### 🏠 Login Page
<!-- ![Login Page](screenshots/login.png) -->

### 📊 Dashboard
<!-- ![Dashboard](screenshots/dashboard.png) -->

### 📚 Book Management
<!-- ![Book Management](screenshots/books.png) -->

### 👥 Member Management
<!-- ![Member Management](screenshots/members.png) -->

### 📅 Issue Tracking
<!-- ![Issue Tracking](screenshots/tracking.png) -->

</details>

<br>

## 🎓 Key Learning Outcomes

<div align="center">

| Concept | Implementation |
|---------|---------------|
| 🏛️ **MVC Architecture** | Implemented clean separation of concerns across layers |
| 💉 **Dependency Injection** | Leveraged Spring's IoC container for loose coupling |
| 🗄️ **Database Design** | Designed normalized relational schema with proper constraints |
| 🔒 **Security** | Implemented authentication and authorization mechanisms |
| 🎨 **Template Engines** | Mastered Thymeleaf for dynamic server-side rendering |
| 🧪 **Testing** | Wrote unit tests for critical business logic |

</div>

<br>

## 🚧 Future Enhancements

- [ ] 📱 Responsive mobile design
- [ ] 📧 Email notifications for due dates
- [ ] 📊 Advanced analytics dashboard
- [ ] 🔔 Real-time notifications
- [ ] 📖 Book reservation system
- [ ] 🌐 REST API for external integrations
- [ ] 🔐 Spring Security integration
- [ ] 📄 PDF report generation

<br>

## 🤝 Contributing

Contributions, issues, and feature requests are welcome!

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<br>

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

<br>

## 🙏 Acknowledgments

<div align="center">

**This project was a major milestone in my journey toward full-stack development.**

I am grateful for the opportunity to apply comprehensive knowledge of:
- ☕ Java Enterprise Development
- 🍃 Spring Boot Ecosystem
- 🗄️ Database Management & SQL
- 🎨 Frontend Integration
- 🏗️ Software Architecture Patterns

<br>

### 💻 Built with passion and dedication

<img src="https://img.shields.io/badge/Made%20with-☕%20Java-ED8B00?style=for-the-badge"/>
<img src="https://img.shields.io/badge/Powered%20by-🍃%20Spring%20Boot-6DB33F?style=for-the-badge"/>
<img src="https://img.shields.io/badge/Status-✅%20Active-success?style=for-the-badge"/>

</div>

<br>

---

<div align="center">

### ⭐ Don't forget to star this repository if you found it helpful!

Made with ❤️ by [Your Name](https://github.com/yourusername)

</div>
