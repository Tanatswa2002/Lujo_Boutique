# Lujo_Boutique Backend

A Spring Boot 3 backend API for the Lujo Boutique E-commerce Platform.  
This system handles authentication, product management, orders, carts, reviews, and core business logic.

---

## 🚀 Tech Stack

- **Java JDK 25**
- **Spring Boot 3**
- **Maven**
- **Spring Security + JWT**
- **MySQL Database**
- **JPA / Hibernate**

---

## 📦 Project Setup

### **1. Requirements**
Make sure your environment has:

- Java **JDK 25**
- Maven **3.9+**
- MySQL **8+**
- IDE (IntelliJ recommended)

---

## ⚙️ Installation & Running

### **Clone the repository**
```bash
git clone <your-repo-url>
cd Lujo_Boutique

**Build Project **
mvn spring-boot:run

**Run Project**
mvn spring-boot:run

Lujo Boutique Database
Overview

This project contains the database schema and data for the Lujo Boutique e-commerce platform.
The database is designed to manage:

Customers

Products and categories

Orders and carts

Refunds and reviews

Analytics and admin data

The database was created and exported using MySQL Workbench and can be imported into any MySQL server, including XAMPP.

Database Details

Database Name: lujo_boutique_db

Tables:

customer

product

category

sub_category

cart

cart_item

customer_order

order_item

review

refunds

refund_item

refund_documents

analytics

admin

Relations: Foreign keys implemented where necessary

Data: Optional inclusion depending on export choice

Requirements

MySQL Server (>= 8.0 recommended)

XAMPP (optional, for local development)

MySQL Workbench (optional, for editing or viewing schema)

Installation / Import Instructions
Using phpMyAdmin (XAMPP)

Start MySQL in XAMPP Control Panel.

Open phpMyAdmin: http://localhost/phpmyadmin

Create a new database called lujo_boutique_db.

Click the database → Import → select Dump20251118.sql → Go.

Using Command Line

Open XAMPP Shell.

Navigate to the folder containing the dump:

cd C:\Users\kimbl\OneDrive\Documentos\dumps


Run the import:

mysql -u root -p lujo_boutique_db < Dump20251118.sql


Leave password empty if using default XAMPP MySQL settings.

Usage

Access the database via MySQL Workbench, phpMyAdmin, or a backend application.

The schema supports CRUD operations for an e-commerce platform.

Notes

This dump includes tables, relationships, and optional data.

Make sure MySQL server is running before importing.

Modify the database name or credentials as needed for your local environment.
