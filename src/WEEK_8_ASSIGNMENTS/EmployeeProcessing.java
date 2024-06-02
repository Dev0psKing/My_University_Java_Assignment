import java.util.ArrayList;
import java.util.List;
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
        try {
            // 1. Create a dataset of employees
            List<Employee> employees = new ArrayList<>();
            employees.add(new Employee("John Doe", 35, "IT", 60000.0));
            employees.add(new Employee("Jane Smith", 28, "HR", 50000.0));
            employees.add(new Employee("Bob Johnson", 42, "IT", 70000.0));
            employees.add(new Employee("Alice Williams", 27, "Marketing", 55000.0));
            employees.add(new Employee("Tom Brown", 33, "Finance", 65000.0));

            // 2. Define a function that concatenates employee name and department
            Function<Employee, String> getNameAndDepartment = employee -> employee.getName() + " - " + employee.getDepartment();

            // 3. Generate a new collection with concatenated strings
            List<String> nameAndDepartmentList = employees.stream()
                    .map(getNameAndDepartment)
                    .collect(Collectors.toList());

            System.out.println("Name and Department List:");
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
        } catch (RuntimeException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}