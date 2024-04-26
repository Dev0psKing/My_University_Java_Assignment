import java.util.ArrayList;
import java.util.Scanner;

/**
 * Student Record Management System
 *
 * This program allows administrators to manage student records, including
 * adding new students, updating student information, and viewing student details.
 *
 * @author [Your Name]
 */
class StudentManagementSystem {
    private static ArrayList<Student> students = new ArrayList<>();
    private static int totalStudents = 0;
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Main entry point of the program.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            displayMenu();
            int choice = getChoice();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    updateStudent();
                    break;
                case 3:
                    viewStudents();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Displays the menu of options for the administrator.
     */
    private static void displayMenu() {
        System.out.println("\nStudent Record Management System");
        System.out.println("1. Add Student");
        System.out.println("2. Update Student");
        System.out.println("3. View Students");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    /**
     * Gets a valid choice from the user.
     *
     * @return The choice as an integer
     */
    private static int getChoice() {
        int choice = -1;
        while (choice < 1 || choice > 4) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
        scanner.nextLine(); // Consume the newline character
        return choice;
    }

    /**
     * Adds a new student to the system.
     */
    private static void addStudent() {
        System.out.print("Enter student first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter student last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter student age: ");
        int age = -1;
        while (age < 0) {
            if (scanner.hasNextInt()) {
                age = scanner.nextInt();
            } else {
                System.out.println("Invalid input for age. Please enter a number.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter student grade: ");
        double grade = scanner.nextDouble();

        String fullName = firstName + " " + lastName;
        Student student = new Student(fullName, age, grade);
        students.add(student);
        totalStudents++;

        System.out.println("Student added successfully.");
    }

    /**
     * Updates the information of an existing student.
     */
    private static void updateStudent() {
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Student student = findStudent(id);
        if (student != null) {
            System.out.print("Enter new first name (or enter to skip): ");
            String firstName = scanner.nextLine();
            System.out.print("Enter new last name (or enter to skip): ");
            String lastName = scanner.nextLine();
            String fullName = firstName.trim() + " " + lastName.trim();
            if (!fullName.trim().isEmpty()) {
                student.setName(fullName);
            }

            System.out.print("Enter new age (or enter -1 to skip): ");
            int age = scanner.nextInt();
            if (age != -1) {
                student.setAge(age);
            }
            scanner.nextLine(); // Consume the newline character

            System.out.print("Enter new grade (or enter -1 to skip): ");
            double grade = scanner.nextDouble();
            if (grade != -1) {
                student.setGrade(grade);
            }

            System.out.println("Student information updated successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    /**
     * Views the details of all students in the system.
     */
    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("Student List:");
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    /**
     * Finds a student by their ID.
     *
     * @param id The ID of the student to find
     * @return The Student object if found, or null if not found
     */
    private static Student findStudent(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    /**
     * Represents a student in the system.
     */
    private static class Student {
        private static int idCounter = 1;
        private final int id;
        private String name;
        private int age;
        private double grade;

        /**
         * Constructs a new Student object with the given name, age, and grade.
         *
         * @param name  The name of the student
         * @param age   The age of the student
         * @param grade The grade of the student
         */
        public Student(String name, int age, double grade) {
            this.id = idCounter++;
            this.name = name;
            this.age = age;
            this.grade = grade;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public double getGrade() {
            return grade;
        }

        public void setGrade(double grade) {
            this.grade = grade;
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Grade: " + grade;
        }
    }
}