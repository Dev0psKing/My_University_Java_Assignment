package WEEK_8_ASSIGNMENTS;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

class Employee {
    private final String name;
    private final int age;
    private final String department;
    private final double salary;

    public Employee(String name, int age, String department, double salary) {
        this.name = name;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
    }
}

public class EmployeeProcessing {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the number of employees: ");
            int numEmployees = getValidInt(scanner, "Please enter a valid number of employees");

            for (int i = 0; i < numEmployees; i++) {
                System.out.println("\nEnter details for Employee " + (i + 1) + ":");

                System.out.print("Name: ");
                String name = getValidString(scanner, "Please enter a valid name");

                System.out.print("Age: ");
                int age = getValidInt(scanner, "Please enter a valid age");

                System.out.print("Department: ");
                String department = getValidString(scanner, "Please enter a valid department");

                System.out.print("Salary: ");
                double salary = getValidDouble(scanner, "Please enter a valid salary");

                employees.add(new Employee(name, age, department, salary));
            }

            // Allow users to input the age threshold dynamically
            System.out.print("Enter age threshold: ");
            int ageThreshold = getValidInt(scanner, "Please enter a valid age threshold");

            // 2. Define a function that concatenates employee name and department
            Function<Employee, String> getNameAndDepartment = employee -> employee.getName() + " - " + employee.getDepartment();

            // 3. Generate a new collection with concatenated strings
            List<String> nameAndDepartmentList = employees.stream()
                    .map(getNameAndDepartment)
                    .collect(Collectors.toList());

            System.out.println("\nName and Department List:");
            nameAndDepartmentList.forEach(System.out::println);

            // 4. Find the average salary of all employees
            double averageSalary = employees.stream()
                    .mapToDouble(Employee::getSalary)
                    .average()
                    .orElseThrow(() -> new RuntimeException("No employees found"));

            System.out.println("\nAverage Salary: $" + averageSalary);

            // 5. Filter employees whose age is above the dynamic threshold
            List<Employee> employeesAboveThreshold = employees.stream()
                    .filter(employee -> employee.getAge() > ageThreshold)
                    .collect(Collectors.toList());

            System.out.println("\nEmployees above age " + ageThreshold + ":");
            employeesAboveThreshold.forEach(System.out::println);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static int getValidInt(Scanner scanner, String errorMessage) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.err.println(errorMessage);
            }
        }
    }

    private static double getValidDouble(Scanner scanner, String errorMessage) {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.err.println(errorMessage);
            }
        }
    }

    private static String getValidString(Scanner scanner, String errorMessage) {
        while (true) {
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.err.println(errorMessage);
        }
    }

    // Summary of Function interface in Java
    private static void printFunctionInterfaceSummary() {
        System.out.println("\nSummary of Function Interface in Java:");
        System.out.println("The Function interface in Java represents a function that takes one argument and produces a result.");
        System.out.println("It is a functional interface defined in the java.util.function package and contains the method:");
        System.out.println("- R apply(T t): Applies this function to the given argument.");
        System.out.println("In this program, we use the Function interface to create a function that takes an Employee object and returns a concatenated string of the employee's name and department.");
        System.out.println("Using the Function interface, along with streams, helps in processing and manipulating collections in a declarative and concise manner.");
    }
}
