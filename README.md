Bank Management System (Java + MySQL)
This is a simple Bank Management System built using Java and a MySQL database. The application allows users to register, log in, open bank accounts, and manage transactions like deposits, withdrawals, and transfers. Below are the key features, a brief overview of the structure, and setup instructions.

Features:
Register: Create a new user account and store user details in the MySQL database.
Login: Log in to the system using the registered email and password.
Open Account: Open a new bank account if the user does not already have one.
Debit Money: Withdraw money from the user's account.
Credit Money: Deposit money into the user's account.
Transfer Money: Transfer money between accounts.
Check Balance: Display the current balance of the user's account.
Exit: Terminate the application.
Core Classes:
Main: Contains the main application loop and user interface, including options for user registration, login, and account management.
Users: Handles user registration and login functionality.
Accounts: Manages the creation of new bank accounts and checks if an account exists for a user.
Account_Manager: Handles transactions such as debit, credit, transfer, and balance inquiries.
Database Interaction:
MySQL JDBC Connection: The application uses JDBC to connect to a MySQL database and interact with tables for user and account management.
Transaction Management: SQL queries are used to debit, credit, and transfer money between accounts, with checks in place for sufficient balances.
Setup
Clone this repository to your local machine:

sh
Copy code
git clone https://github.com/your-username/Bank_Management_System.git
Configure your MySQL database settings in the Main.java file:

java
Copy code
private static final String url = "jdbc:mysql://localhost:3306/Banking_system_db";
private static final String username = "your_username";
private static final String password = "your_password";
Create a MySQL database named Banking_system_db and ensure you have the necessary tables (Users, Accounts, Transactions, etc.).

Compile and run the application:

sh
Copy code
javac Main.java
java Main
Follow the on-screen menu options to register, log in, manage accounts, and perform transactions.

This description provides an overview of the system, its features, setup steps, and instructions for running the code.
