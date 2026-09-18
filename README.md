# Faculty Duty & Workload Management System

## 📌 Project Overview

The Faculty Duty & Workload Management System is a Java Swing desktop application developed to manage faculty members, subjects, workload, duties, duty assignments, and timetables efficiently.

The system uses MySQL as the database and JDBC for database connectivity. It provides role-based access for Admin, HOD, and Faculty users.

## 🚀 Features

- Faculty Management
- Department Management
- Subject Management
- Faculty Workload Management
- Automatic Workload Distribution
- Duty Management
- Duty Assignment
- Timetable Management
- Faculty Profile
- Faculty Weekly & Today's Timetable
- Reports
- Role-Based Login
- MySQL Database Connectivity
- CRUD Operations
- Input Validation

## 👥 User Roles

### Admin
- Manage faculty records
- Manage departments
- Manage subjects
- Manage users
- View reports

### HOD
- Manage faculty workload
- Automatically distribute workload
- Manage duties and duty assignments
- Create and manage faculty timetable
- View reports

### Faculty
- View profile
- View assigned subjects
- View workload
- View assigned duties
- View duty assignments
- View today's timetable
- View weekly timetable
- View reports

## 🛠️ Technologies Used

- Java
- Java Swing
- JDBC
- MySQL
- IntelliJ IDEA
- Git & GitHub

## 🗄️ Database

The application uses MySQL database tables for managing:

- Users
- Faculty
- Departments
- Subjects
- Workload
- Duties
- Duty Assignments
- Timetable

## ⚙️ Workload Management

The system provides workload calculation and automatic workload distribution among faculty members. The automatic distribution feature attempts to balance workload by assigning workload records among available faculty members.

## 🕒 Timetable Management

The HOD can create, update, delete, and view faculty timetables.

Faculty members have view-only access to their timetable and can view:

- Today's timetable
- Weekly timetable

Timetable is organized from Monday to Saturday using fixed time slots.

## 🔐 Role-Based Access

Different dashboards and functionalities are provided according to the logged-in user's role:

**Admin → Admin Dashboard**

**HOD → HOD Dashboard**

**Faculty → Faculty Dashboard**

## 📂 Project Structure

```text
src/
└── com/
    └── faculty/
        └── management/
            ├── dao/
            ├── db/
            ├── model/
            └── ui/
```
## 🎯 Objective

The main objective of this project is to reduce manual work involved in faculty management and provide a centralized system for managing faculty workload, duties, assignments, and timetables.

## Project Status

Completed

## Author

Harsh Chaudhary
