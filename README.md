# ThermoTag with Login and Registration Page using Retrofit and MySQL

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Project Structure](#project-structure)
- [Technologies Used](#technologies-used)
- [Setup](#setup)
  - [Android Project](#android-project)
  - [PHP Backend](#php-backend)
- [Usage](#usage)
- [Database Structure](#database-structure)

## Introduction
ThermoTag is an Android application that includes login and registration functionality, implemented using Retrofit and MySQL. The backend is built with PHP and utilizes MySQL for the database.

## Features
- User Registration
- User Login
- Secure Password Storage
- Retrofit Integration with PHP Backend
- Responsive User Interface

## Project Structure

ThermoTag-with-Login-and-Registration-Page-with-Retrofit-and-MySQL/
├── android/
│ ├── app/
│ └── ...
├── backend/
│ ├── login.php
│ ├── register.php
│ └── ...
├── .gitignore
├── README.md
└── ...


## Technologies Used
- **Android**: Java, Retrofit
- **Backend**: PHP, MySQL
- **Others**: Composer for dependency management

## Setup

### Android Project
1. Clone the repository:
   ```sh
   git clone https://github.com/SebastianLukito/ThermoTag-with-Login-and-Registration-Page-with-Retrofit-and-MySQL.git

2. Open the project in Android Studio.
3. Build and run the project on an emulator or a physical device.
   
PHP Backend
1. Navigate to the backend directory
 ```sh
cd ThermoTag-with-Login-and-Registration-Page-with-Retrofit-and-MySQL/backend
```
2. Install dependencies using Composer:
 ```sh
php composer.phar install
```
3. Set up your .env file:
 ```sh
DB_SERVER=________
DB_USERNAME=_________
DB_PASSWORD=_______
DB_NAME=_______
DB_PORT=______
```
4. Start your local server (e.g., XAMPP) and ensure the backend PHP files are accessible.

## Usage
1. Register a new user using the Android app.
2. Log in with the registered credentials.
3. The Android app communicates with the PHP backend to authenticate and manage user sessions.

## Database Structure
Tambahkan bagian ini setelah bagian Setup atau Usage:
## Database Structure

Untuk menggunakan backend PHP, Anda perlu membuat tabel `users` dalam database MySQL Anda. Berikut adalah struktur tabel yang digunakan dalam proyek ini:

```sql
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

