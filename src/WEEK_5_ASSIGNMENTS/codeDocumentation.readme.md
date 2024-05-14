```markdown
# Course and Student Management System

## Description

This Java program is designed to manage courses and student enrollments, allowing for course addition, student enrollment, grading, and overall grade calculation.

## Classes

### Student

- **Fields:**
    - `name` (String): Student's name.
    - `id` (String): Student's ID.
    - `enrolledCourses` (List<Course>): List of courses the student is enrolled in.
    - `grades` (Map<Course, Double>): Map of courses and corresponding grades for the student.

- **Methods:**
    - `enrollCourse(Course course)`: Enrolls the student in a given course.
    - `assignGrade(Course course, double grade)`: Assigns a grade to the student for a given course.
    - `getEnrolledCourses()`: Returns the list of enrolled courses.
    - `getGrades()`: Returns the map of grades.
    - `getName()`: Returns the student's name.
    - `getId()`: Returns the student's ID.

### Course

- **Fields:**
    - `code` (String): Course code.
    - `name` (String): Course name.
    - `maxCapacity` (int): Maximum capacity of the course.
    - `totalEnrolled` (static int): Total number of students enrolled in courses.

- **Methods:**
    - `getCode()`: Returns the course code.
    - `getName()`: Returns the course name.
    - `getMaxCapacity()`: Returns the maximum capacity of the course.
    - `getTotalEnrolled()`: Returns the total number of students enrolled.
    - `incrementTotalEnrolled()`: Increments the total number of enrolled students.

### CourseManagement

- **Fields:**
    - `courses` (List<Course>): List of courses.
    - `overallGrades` (Map<Student, Map<Course, Double>>): Map of students and their grades for each course.
    - `students` (Map<String, Student>): Map of student IDs to student objects.

- **Methods:**
    - `addCourse(String code, String name, int maxCapacity)`: Adds a new course.
    - `enrollStudent(Student student, Course course)`: Enrolls a student in a course.
    - `assignGrade(Student student, Course course, double grade)`: Assigns a grade to a student for a course.
    - `calculateOverallGrade(Student student)`: Calculates the overall grade for a student.
    - `main(String[] args)`: Main method to run the program. Implements a menu-driven interface for managing courses and students.

## Usage

- Clone the repository.
- Compile the Java program.
- Run the compiled program.
- Follow the on-screen prompts to perform the desired tasks.

## Notes

- The program handles course addition, student enrollment, grading, and overall grade calculation.
- Ensure to use appropriate variable names and follow coding best practices for readability and maintainability.
```
