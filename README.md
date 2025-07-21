# Parking-Management-System-JDBC

A smart and efficient Parking Management System designed to streamline vehicle entry/exit, track real-time slot availability, and reduce manual effort using Java, MySQL, and JDBC.

# Project Overview

This system automates the process of vehicle parking in urban and commercial environments. It provides an intuitive interface for users and administrators to manage parking operations digitally.

#Features

- Real-time parking slot availability tracking
- Admin dashboard to manage parking spaces
- Entry/Exit time logging
- Secure authentication system
- GUI-based interface using Java Swing
- MySQL database integration via JDBC

## 🛠️ Technologies Used

| Component     | Technology           |
|---------------|----------------------|
| Language       | Java                 |
| GUI Framework  | Java Swing / JavaFX  |
| Backend        | JDBC                 |
| Database       | MySQL                |
| Tools          | IntelliJ IDEA, XAMPP |

✅ 1. Prerequisites
Make sure the following are installed and configured on your system:

Tool/Software	Description	Link
Java JDK	Java Development Kit (8 or above)	Download
IntelliJ IDEA or Eclipse	Java IDE	Download IntelliJ
MySQL Server	Local MySQL Database	Download
XAMPP (optional)	For quick MySQL setup	Download XAMPP
MySQL JDBC Driver	Connector to link Java & MySQL	Download Connector/J

🔧 2. Add JDBC to Your Project
➤ If Using IntelliJ:
Right-click your project > Open Module Settings (F4)

Go to Libraries > Click + > Java

Select the MySQL Connector JAR file you downloaded

Click OK

➤ If Using Plain Java:
Place mysql-connector-java-x.x.xx.jar in your lib/ folder and include in compile:

bash
Copy
Edit
javac -cp ".;lib/mysql-connector-java-x.x.xx.jar" src/Main.java
🛠️ 3. MySQL Database Setup
🔹 Step 1: Create Database
sql
Copy
Edit
CREATE DATABASE parking_system;
🔹 Step 2: Create Table
sql
Copy
Edit
USE parking_system;

CREATE TABLE parking_slots (
    slot_id INT PRIMARY KEY AUTO_INCREMENT,
    is_occupied BOOLEAN NOT NULL DEFAULT 0,
    vehicle_number VARCHAR(20),
    entry_time DATETIME,
    exit_time DATETIME
);

## 👨‍💻 Team Members

- **Yash Chavan**
- **Gaurav Salunke**
- **Aaditi Bais**
  
# 📈 Future Enhancements

- Mobile App Integration
- RFID-based vehicle detection
- SMS/Email alerts
- Payment gateway for ticketing

# 📞 Contact

For any queries or suggestions, feel free to contact the project team.
Email : yashchavanpatil1@gmail.com




