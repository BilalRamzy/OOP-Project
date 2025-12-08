# **Discussion File – Library System Project**

## **1. Introduction**

This project is a simple **Library Management System** developed using Object-Oriented Programming (OOP) in Java.
The goal is to demonstrate understanding of OOP concepts such as classes, inheritance, method overloading, method overriding, association, aggregation, searching, and sorting — all implemented inside **one Java file**.

---

## **2. System Overview**

The system provides basic features needed in a simple library:

* Add items (Books, Magazines)
* Search for items
* Sort items by ID
* Display all items
* Students can borrow and return items

The focus is on applying OOP principles, not on building a complex application.

---

## **3. Classes and Responsibilities**

### **1. Item (Base Class)**

* Represents a general library item.
* Contains shared attributes: `id`, `title`.
* Contains a `display()` method to show item information.
* Parent class for Book and Magazine.

### **2. Book (Subclass)**

* Inherits from Item.
* Adds the `author` attribute.
* Overrides the `display()` method to show book-specific details.

### **3. Magazine (Subclass)**

* Inherits from Item.
* Adds `issueNo` as a unique attribute.
* Overrides `display()`.

### **4. Library**

* Responsible for storing all items using an array.
* Handles:

  * Adding items
  * Searching using **manual linear search**
  * Sorting items using **manual bubble sort**
  * Displaying all items

### **5. Student**

* Has a name.
* Can borrow and return items.
* Connected to Library via **association** (uses the Library but does not own it).

---

## **4. OOP Concepts Applied**

### ✔ **Inheritance**

* Book → Item
* Magazine → Item

### ✔ **Method Overriding**

* Both Book and Magazine override the `display()` method.

### ✔ **Method Overloading**

Two search methods:

* `searchItem(int id)`
* `searchItem(String title)`

### ✔ **Association**

* Student interacts with the Library.

### ✔ **Aggregation**

* Library contains an array of Item objects.

### ✔ **Searching**

* Implemented manually using Linear Search.

### ✔ **Sorting**

* Implemented manually using Bubble Sort (no built-in sort functions used).

---

## **5. Design Decisions**

* The entire project is inside **one file** to satisfy project requirements.
* Manual algorithms used instead of built-in functions to demonstrate understanding.
* Arrays used instead of ArrayLists to keep the code simple and controlled.
* Classes kept minimal to make the system easy to read, implement, and discuss.

---

## **6. Example Use Cases**

### **Adding a Book**

The user enters:

* ID
* Title
* Author
  The Library stores the new Book inside the array.

### **Searching**

* The user can search by ID or title.
* The correct overloaded search method is called.

### **Sorting**

* Items are sorted by ID using Bubble Sort.

### **Borrowing**

* A Student borrows an item if available.

---

## **7. Conclusion**

This project successfully applies all major OOP concepts required for the assignment.
The system is simple, easy to understand, and demonstrates OOP fundamentals clearly, including manual searching, sorting, inheritance, overriding, overloading, association, and aggregation.

It fulfills all academic requirements while staying clean and readable.
