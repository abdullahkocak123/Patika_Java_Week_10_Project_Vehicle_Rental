# 🚗 Vehicle Rental System

A simple Java application for managing vehicle rental operations. It supports managing customers, vehicles, and rental transactions, as well as features like user roles and a shopping cart system.

---

## 📌 Features

- 👤 Customer registration and login (individual / corporate)
- 🚗 Vehicle listing, searching, and filtering (by category)
- 🛒 Add, view, and clear vehicles in the cart
- 📦 Rent vehicles from the cart and proceed with payment
- 💳 Choose payment method
- 📜 View rental history
- 🔐 User roles (admin / customer)
- 📆 Rental periods: hourly, daily, weekly, monthly

---

## 🛠️ Technologies Used

- **Java 17+**
- **PostgreSQL**
- **JDBC**
- **Maven**
- **Layered architecture: DAO, Service, Model, Utility classes**

---

## 🗂️ Database Tables

- `users`: System users (admin/customer)
- `customer`: Customer information
- `vehicle`: Rentable vehicles
- `category`: Vehicle categories (car, motorcycle, etc.)
- `cart`, `cart_items`: Shopping cart and cart items
- `rent`, `rent_item`: Rental transactions and rented vehicles
- `payment`: Payment records

---

## ⚙️ Setup

1. Create the necessary tables in PostgreSQL:
   - SQL scripts can be added under resources/sql or src/main/resources.
2. Update the connection settings in DBUtil.java according to your own database:


private static final String URL = "jdbc:postgresql://localhost:5432/arac_kiralama";

private static final String USER = "postgres";

private static final String PASSWORD = "postgres";

