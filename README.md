# 🚗 Car Rental System 

A simple console-based **Car Rental System** built in Java.  
Users can rent and return cars, view rental details, and manage customers interactively.

---

## 📂 Project Structure
CarRentalSystemProject/
│
├── README.md
└── src/
├── Car.java
├── Customer.java
├── Rental.java
├── CarRentalSystem.java
└── Main.java


---

## 🛠️ Features
- Add cars to the rental system
- Rent a car (with price calculation)
- Return a car
- Manage customers automatically
- Console-based interactive menu

---

## 🚀 How to Compile & Run

### 1. Open a terminal and move into the project root:
```bash
cd CarRentalSystemProject

2. Compile all Java files:
javac -d out src/*.java

3. Run the program:
java -cp out Main

Example Usage
===== Car Rental System =====
1. Rent a Car
2. Return a Car
3. Exit
Enter your choice: 1

== Rent a Car ==
Enter your name: Trisha

Available Cars:
T001 - Toyota Allion
H001 - Honda Civic
...

Enter the car ID you want to rent: H001
Enter the number of days for rental: 3

== Rental Information ==
Customer ID: CUS1
Customer Name: Trisha
Car: Honda Civic
Rental Days: 3
Total Price: $450.00

Confirm rental (Y/N): Y
Car rented successfully.
