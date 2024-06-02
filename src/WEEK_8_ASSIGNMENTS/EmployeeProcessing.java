import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

class Employee {
    private String name;
    private int age;
    private String department;
    private double salary;

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
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter the number of employees: ");
            int numEmployees = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            for (int i = 0; i < numEmployees; i++) {
                System.out.println("\nEnter details for Employee " + (i + 1) + ":");

                System.out.print("Name: ");
                String name = scanner.nextLine();

                System.out.print("Age: ");
                int age = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                System.out.print("Department: ");
                String department = scanner.nextLine();

                System.out.print("Salary: ");
                double salary = scanner.nextDouble();
                scanner.nextLine(); // Consume newline character

                employees.add(new Employee(name, age, department, salary));
            }

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

            // 5. Filter employees whose age is above a certain threshold
            int ageThreshold = 30;
            List<Employee> employeesAboveThreshold = employees.stream()
                    .filter(employee -> employee.getAge() > ageThreshold)
                    .collect(Collectors.toList());

            System.out.println("\nEmployees above age " + ageThreshold + ":");
            employeesAboveThreshold.forEach(System.out::println);
        } catch (InputMismatchException e) {
            System.err.println("Error: Invalid input. Please enter a valid value.");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}