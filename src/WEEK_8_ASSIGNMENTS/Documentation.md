# Employee Management Application

## Overview

This Java application allows users to manage a list of employees by inputting their details, processing the data, and performing various operations such as filtering by age, concatenating name and department, and calculating the average salary. The application uses Java Streams and the Function interface to handle and process employee data efficiently.

## Table of Contents
1. [Classes and Methods](#classes-and-methods)
    - [Employee Class](#employee-class)
    - [EmployeeProcessing Class](#employeeprocessing-class)
2. [Usage](#usage)
3. [Functions and Processing](#functions-and-processing)
    - [Concatenate Name and Department](#concatenate-name-and-department)
    - [Calculate Average Salary](#calculate-average-salary)
    - [Filter by Age](#filter-by-age)
4. [Input Validation](#input-validation)
5. [Function Interface Summary](#function-interface-summary)

## Classes and Methods

### Employee Class

The `Employee` class represents an employee with the following attributes:
- `name`: The name of the employee.
- `age`: The age of the employee.
- `department`: The department in which the employee works.
- `salary`: The salary of the employee.

#### Methods
- `Employee(String name, int age, String department, double salary)`: Constructor to initialize an employee object.
- `String getName()`: Returns the name of the employee.
- `int getAge()`: Returns the age of the employee.
- `String getDepartment()`: Returns the department of the employee.
- `double getSalary()`: Returns the salary of the employee.
- `String toString()`: Returns a string representation of the employee object.

### EmployeeProcessing Class

The `EmployeeProcessing` class contains the `main` method and handles the main functionality of the application.

## Usage

1. **Enter the number of employees**: The user is prompted to enter the number of employees.
2. **Enter employee details**: For each employee, the user inputs the name, age, department, and salary.
3. **Enter age threshold**: The user inputs an age threshold to filter employees.

The application processes the input data and performs the following operations:
- Concatenates the name and department of each employee.
- Calculates the average salary of all employees.
- Filters and displays employees above the specified age threshold.

## Functions and Processing

### Concatenate Name and Department

A function is defined to concatenate the name and department of each employee:
```java
Function<Employee, String> getNameAndDepartment = employee -> employee.getName() + " - " + employee.getDepartment();
```
This function is applied to all employees to generate a list of concatenated strings.

### Calculate Average Salary

The average salary of all employees is calculated using Java Streams:
```java
double averageSalary = employees.stream()
                                .mapToDouble(Employee::getSalary)
                                .average()
                                .orElseThrow(() -> new RuntimeException("No employees found"));
```

### Filter by Age

Employees whose age is above the specified threshold are filtered using:
```java
List<Employee> employeesAboveThreshold = employees.stream()
                                                  .filter(employee -> employee.getAge() > ageThreshold)
                                                  .collect(Collectors.toList());
```

## Input Validation

The application ensures valid input using helper methods:
- `getValidInt(Scanner scanner, String errorMessage)`: Validates and returns a valid integer input.
- `getValidDouble(Scanner scanner, String errorMessage)`: Validates and returns a valid double input.
- `getValidString(Scanner scanner, String errorMessage)`: Validates and returns a non-empty string input.

## Function Interface Summary

The Function interface in Java represents a function that takes one argument and produces a result. It is defined in the `java.util.function` package and contains the method:
- `R apply(T t)`: Applies this function to the given argument.

In this program, the Function interface is used to create a function that takes an Employee object and returns a concatenated string of the employee's name and department. Using the Function interface, along with streams, helps in processing and manipulating collections in a declarative and concise manner.

```java
private static void printFunctionInterfaceSummary() {
    System.out.println("\nSummary of Function Interface in Java:");
    System.out.println("The Function interface in Java represents a function that takes one argument and produces a result.");
    System.out.println("It is a functional interface defined in the java.util.function package and contains the method:");
    System.out.println("- R apply(T t): Applies this function to the given argument.");
    System.out.println("In this program, we use the Function interface to create a function that takes an Employee object and returns a concatenated string of the employee's name and department.");
    System.out.println("Using the Function interface, along with streams, helps in processing and manipulating collections in a declarative and concise manner.");
}
```