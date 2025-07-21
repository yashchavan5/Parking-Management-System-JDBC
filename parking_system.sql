CREATE DATABASE IF NOT EXISTS parking_system;

USE parking_system;

CREATE TABLE IF NOT EXISTS parking_slots (
    slot_id INT AUTO_INCREMENT PRIMARY KEY,
    vehicle_number VARCHAR(20) NOT NULL,
    vehicle_type VARCHAR(20),
    in_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    out_time TIMESTAMP NULL
);
