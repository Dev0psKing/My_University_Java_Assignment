package WEEK_3_ASSIGNMENT;



import java.util.ArrayList;

import java.util.InputMismatchException;

import java.util.Scanner;



/**

 * StudentManagementSystem class to manage student records.

 *

 * This class provides functionality to add, update, and view student records.

 * It also includes validation for user input and handles exceptions.

 *

 * @author [Your Name]

 */

public class StudentManagementSystem {

    private ArrayList<Student> students;

    private Scanner scanner;



    /**

     * Constructor for the StudentManagementSystem class.

     * Initializes the students list and scanner.

     */

    public StudentManagementSystem() {

        students = new ArrayList<>();

        scanner = new Scanner(System.in);

    }



    /**

     * Main entry point of the program.

     *

     * @param args Command-line arguments (not used)

     */

    public static void main(String[] args) {

        StudentManagementSystem sms = new StudentManagementSystem();

        sms.run();

    }



    /**

     * Runs the student management system program.

     */

    private void run() {

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

    private void displayMenu() {

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

    private int getChoice() {

        int choice = -1;

        while (choice < 1 || choice > 4) {

            try {

                choice = scanner.nextInt();

            } catch (InputMismatchException e) {

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

    private void addStudent() {

        System.out.print("Enter student ID: ");

        String studentID = scanner.nextLine();



        // Validate student ID

        if (studentID.trim().isEmpty()) {

            System.out.println("Student ID cannot be empty.");

            return;

        }



        System.out.print("Enter student first name: ");

        String firstName = scanner.nextLine();



        System.out.print("Enter student last name: ");

        String lastName = scanner.nextLine();



        // Validate name

        if (firstName.trim().isEmpty() || lastName.trim().isEmpty()) {

            System.out.println("First name and last name cannot be empty.");

            return;

        }



        String fullName = firstName.trim() + " " + lastName.trim();



        int age = getAge();

        String grade = getGrade();



        Student student = new Student(studentID, fullName, age, grade);

        students.add(student);



        System.out.println("Student added successfully.");

    }



    /**

     * Updates the information of an existing student.

     */

    private void updateStudent() {

        System.out.print("Enter student ID: ");

        String studentID = scanner.nextLine();



        Student student = findStudent(studentID);

        if (student != null) {

            System.out.print("Enter new first name (or enter to skip): ");

            String firstName = scanner.nextLine();

            System.out.print("Enter new last name (or enter to skip): ");

            String lastName = scanner.nextLine();

            String fullName = firstName.trim() + " " + lastName.trim();

            if (!fullName.trim().isEmpty()) {

                student.setName(fullName);

            }



            int age = getAge("Enter new age (or enter -1 to skip): ");

            if (age != -1) {

                student.setAge(age);

            }



            String grade = getGrade("Enter new grade (A, B, C, D, or F) (or enter to skip): ");

            if (!grade.isEmpty()) {

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

    private void viewStudents() {

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

     * @param studentID The ID of the student to find

     * @return The Student object if found, or null if not found

     */

    private Student findStudent(String studentID) {

        for (Student student : students) {

            if (student.getStudentID().equals(studentID)) {

                return student;

            }

        }

        return null;

    }



    /**

     * Gets a valid age from the user.

     *

     * @return The age as an integer, or -1 if the user enters -1

     */

    private int getAge() {

        return getAge("Enter student age: ");

    }



    /**

     * Gets a valid age from the user with a prompt.

     *

     * @param prompt The prompt to display to the user

     * @return The age as an integer, or -1 if the user enters -1

     */

    private int getAge(String prompt) {

        int age = -1;

        while (age < 0) {

            System.out.print(prompt);

            try {

                age = scanner.nextInt();

            } catch (InputMismatchException e) {

                System.out.println("Invalid input for age. Please enter a number.");

                scanner.nextLine(); // Consume the invalid input

            }

        }

        scanner.nextLine(); // Consume the newline character

        return age;

    }



    /**

     * Gets a valid grade from the user.

     *

     * @return The grade as a string (A, B, C, D, or F)

     */

    private String getGrade() {

        return getGrade("Enter student grade (A, B, C, D, or F): ");

    }



    /**

     * Gets a valid grade from the user with a prompt.

     *

     * @param prompt The prompt to display to the user

     * @return The grade as a string (A, B, C, D, or F), or an empty string if the user enters nothing

     */

    private String getGrade(String prompt) {

        String grade;

        while (true) {

            System.out.print(prompt);

            grade = scanner.nextLine().toUpperCase();

            if (isValidGrade(grade) || grade.isEmpty()) {

                break;

            } else {

                System.out.println("Invalid grade input. Please enter A, B, C, D, or F.");

            }

        }

        return grade;

    }



    /**

     * Checks if the given grade is valid (A, B, C, D, or F).

     *

     * @param grade The grade to be checked

     * @return true if the grade is valid, false otherwise

     */

    private boolean isValidGrade(String grade) {

        return grade.equals("A") || grade.equals("B") || grade.equals("C") || grade.equals("D") || grade.equals("F");

    }



    /**

     * Represents a student in the system.

     */

    private static class Student {

        private String studentID;

        private String name;

        private int age;

        private String grade;



        /**

         * Constructs a new Student object with the given student ID, name, age, and grade.

         *

         * @param studentID The ID of the student

         * @param name      The name of the student

         * @param age       The age of the student

         * @param grade     The grade of the student

         */

        public Student(String studentID, String name, int age, String grade) {

            this.studentID = studentID;

            this.name = name;

            this.age = age;

            this.grade = grade;

        }



        public String getStudentID() {

            return studentID;

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



        public String getGrade() {

            return grade;

        }



        public void setGrade(String grade) {

            this.grade = grade;

        }



        @Override

        public String toString() {

            return "Student ID: " + studentID + ", Name: " + name + ", Age: " + age + ", Grade: " + grade;

        }

    }

}

