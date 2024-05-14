package WEEK_5_ASSIGNMENTS;



import java.util.*;



// Student class

class Student {

    private final String name;

    private final String id;

    private final List<Course> enrolledCourses;

    private final Map<Course, Double> grades;



    public Student(String name, String id) {

        this.name = name;

        this.id = id;

        this.enrolledCourses = new ArrayList<>();

        this.grades = new HashMap<>();

    }



    public void enrollCourse(Course course) {

        enrolledCourses.add(course);

        Course.incrementTotalEnrolled();

    }



    public void assignGrade(Course course, double grade) {

        grades.put(course, grade);

    }



    public List<Course> getEnrolledCourses() {

        return enrolledCourses;

    }



    public Map<Course, Double> getGrades() {

        return grades;

    }



    public String getName() {

        return name;

    }



    public String getId() {

        return id;

    }

}



// Course class

class Course {

    private String code;

    private String name;

    private int maxCapacity;

    private static int totalEnrolled = 0;



    public Course(String code, String name, int maxCapacity) {

        this.code = code;

        this.name = name;

        this.maxCapacity = maxCapacity;

    }



    public String getCode() {

        return code;

    }



    public String getName() {

        return name;

    }



    public int getMaxCapacity() {

        return maxCapacity;

    }



    public static int getTotalEnrolled() {

        return totalEnrolled;

    }



    public static void incrementTotalEnrolled() {

        totalEnrolled++;

    }

}



// CourseManagement class

class CourseManagement {

    private static List<Course> courses = new ArrayList<>();

    private static Map<Student, Map<Course, Double>> overallGrades = new HashMap<>();

    private static Map<String, Student> students = new HashMap<>();



    public static void addCourse(String code, String name, int maxCapacity) {

        courses.add(new Course(code, name, maxCapacity));

    }



    public static void enrollStudent(Student student, Course course) {

        if (course.getTotalEnrolled() < course.getMaxCapacity()) {

            student.enrollCourse(course);

        } else {

            System.out.println("Course is full. Cannot enroll student.");

        }

    }



    public static void assignGrade(Student student, Course course, double grade) {

        student.assignGrade(course, grade);

        overallGrades.putIfAbsent(student, new HashMap<>());

        overallGrades.get(student).put(course, grade);

    }



    public static double calculateOverallGrade(Student student) {

        Map<Course, Double> grades = overallGrades.get(student);

        double totalGrade = 0;

        int courseCount = 0;

        for (double grade : grades.values()) {

            totalGrade += grade;

            courseCount++;

        }

        return totalGrade / courseCount;

    }



    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);



        while (true) {

            System.out.println("\nCourse Enrollment and Grade Management System");

            System.out.println("1. Add Course");

            System.out.println("2. Enroll Student");

            System.out.println("3. Assign Grade");

            System.out.println("4. Calculate Overall Grade");

            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            scanner.nextLine(); // Consume the newline character



            switch (choice) {

                case 1:

                    System.out.print("Enter course code: ");

                    String code = scanner.nextLine();

                    System.out.print("Enter course name: ");

                    String name = scanner.nextLine();

                    System.out.print("Enter maximum capacity: ");

                    int maxCapacity = scanner.nextInt();

                    addCourse(code, name, maxCapacity);

                    System.out.println("Course added successfully.");

                    break;

                case 2:

                    System.out.print("Enter student name: ");

                    String studentName = scanner.nextLine();

                    System.out.print("Enter student ID: ");

                    String studentId = scanner.nextLine();

                    Student student = new Student(studentName, studentId);

                    students.put(studentId, student); // Add the student to the students map

                    System.out.print("Enter course code: ");

                    String courseCode = scanner.nextLine();

                    Course course = courses.stream()

                            .filter(c -> c.getCode().equals(courseCode))

                            .findFirst()

                            .orElse(null);

                    if (course != null) {

                        enrollStudent(student, course);

                        System.out.println("Student enrolled successfully.");

                    } else {

                        System.out.println("Course not found.");

                    }

                    break;

                case 3:

                    System.out.print("Enter student ID: ");

                    String studId = scanner.nextLine();

                    Student stud = students.get(studId);



                    if (stud != null) {

                        System.out.print("Enter course code: ");

                        String crsCode = scanner.nextLine();

                        Course crs = courses.stream()

                                .filter(c -> c.getCode().equals(crsCode))

                                .findFirst()

                                .orElse(null);



                        if (crs != null) {

                            double grade;

                            while (true) {

                                System.out.print("Enter grade: ");

                                try {

                                    grade = scanner.nextDouble();

                                    scanner.nextLine(); // Consume the newline character

                                    break;

                                } catch (InputMismatchException e) {

                                    System.out.println("Invalid input. Please enter a valid numeric grade.");

                                    scanner.nextLine(); // Consume the invalid input

                                }

                            }

                            assignGrade(stud, crs, grade);

                            System.out.println("Grade assigned successfully.");

                        } else {

                            System.out.println("Course not found.");

                        }

                    } else {

                        System.out.println("Student not found.");

                    }

                    break;

                case 4:

                    System.out.print("Enter student ID: ");

                    String studId2 = scanner.nextLine();

                    Student student2 = overallGrades.keySet().stream()

                            .filter(s -> s.getId().equals(studId2))

                            .findFirst()

                            .orElse(null);

                    if (student2 != null) {

                        double overallGrade = calculateOverallGrade(student2);

                        System.out.println("Overall grade for " + student2.getName() + ": " + overallGrade);

                    } else {

                        System.out.println("Student not found.");

                    }

                    break;

                case 5:

                    System.out.println("Exiting...");

                    scanner.close();

                    System.exit(0);

                default:

                    System.out.println("Invalid choice. Try again.");

            }

        }

    }

}