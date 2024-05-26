package WEEK_7_ASSIGNMENTS;



import javax.swing.*;

import javax.swing.table.DefaultTableModel;

import java.awt.*;

import java.awt.event.*;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;

import java.util.Map;



class Student {

    private static int nextStudentId = 1;

    private int studentId;

    private String name;

    private String rollNumber;

    private String email;

    private String contactNumber;

    private String address;

    private String gender;

    private String nationality;

    private int yearOfPassing10th;

    private int yearOfPassing12th;

    private double points10th;

    private double percentage12th;



    public Student(String name, String rollNumber, String email, String contactNumber, String address, String gender,

                   String nationality, int yearOfPassing10th, int yearOfPassing12th, double points10th,

                   double percentage12th) {

        this.studentId = nextStudentId++;

        this.name = name;

        this.rollNumber = rollNumber;

        this.email = email;

        this.contactNumber = contactNumber;

        this.address = address;

        this.gender = gender;

        this.nationality = nationality;

        this.yearOfPassing10th = yearOfPassing10th;

        this.yearOfPassing12th = yearOfPassing12th;

        this.points10th = points10th;

        this.percentage12th = percentage12th;

    }



    public int getStudentId() {

        return studentId;

    }



    public String getName() {

        return name;

    }



    public String getRollNumber() {

        return rollNumber;

    }



    public String getEmail() {

        return email;

    }



    public String getContactNumber() {

        return contactNumber;

    }



    public String getAddress() {

        return address;

    }



    public String getGender() {

        return gender;

    }



    public String getNationality() {

        return nationality;

    }



    public int getYearOfPassing10th() {

        return yearOfPassing10th;

    }



    public int getYearOfPassing12th() {

        return yearOfPassing12th;

    }



    public double getPoints10th() {

        return points10th;

    }



    public double getPercentage12th() {

        return percentage12th;

    }

}



class StudentController {

    private List<Student> students = new ArrayList<>();

    private Map<String, ArrayList<String>> courseEnrollments = new HashMap<>();

    private Map<String, Map<String, String>> studentGrades = new HashMap<>();



    public boolean addStudent(Student student) {

        for (Student s : students) {

            if (s.getRollNumber().equals(student.getRollNumber())) {

                return false;

            }

        }

        students.add(student);

        return true;

    }



    public boolean updateStudent(String rollNumber, Student updatedStudent) {

        for (Student student : students) {

            if (student.getRollNumber().equals(rollNumber)) {

                students.set(students.indexOf(student), updatedStudent);

                return true;

            }

        }

        return false;

    }



    public List<Student> getStudents() {

        return students;

    }



    public void enrollStudentInCourse(int studentId, String course) {

        courseEnrollments.putIfAbsent(course, new ArrayList<>());

        String studentName = getStudentName(studentId);

        if (!courseEnrollments.get(course).contains(studentName)) {

            courseEnrollments.get(course).add(studentName);

        }

    }



    public void assignGrade(int studentId, String course, String grade) {

        String studentName = getStudentName(studentId);

        studentGrades.putIfAbsent(studentName, new HashMap<>());

        studentGrades.get(studentName).put(course, grade);

    }



    public Map<String, ArrayList<String>> getCourseEnrollments() {

        return courseEnrollments;

    }



    public Map<String, Map<String, String>> getStudentGrades() {

        return studentGrades;

    }



    private String getStudentName(int studentId) {

        for (Student student : students) {

            if (student.getStudentId() == studentId) {

                return student.getName();

            }

        }

        return null;

    }

}



public class StudentManagementSystem extends JFrame {

    private JTextField tfName, tfRollNumber, tfEmail, tfContactNumber, tfAddress, tfNationality, tfPoints10th, tfPercentage12th;

    private JComboBox<String> cbGender, cbYearOfPassing10th, cbYearOfPassing12th, cbStudents, cbCourses, cbGrades;

    private JButton btnAddStudent, btnUpdateStudent, btnViewStudents, btnEnrollStudent, btnAssignGrade;

    private DefaultTableModel studentTableModel;

    private JTable studentTable;

    private StudentController controller;

    private String selectedRollNumber;



    public StudentManagementSystem() {

        controller = new StudentController();

        initUI();

    }



    private void initUI() {

        setTitle("Student Management System");

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(1200, 700);

        setLocationRelativeTo(null);

        setLayout(new BorderLayout(15, 15));

        getContentPane().setBackground(new Color(245, 245, 245));



        // Student Form Panel

        JPanel studentFormPanel = new JPanel(new GridBagLayout());

        studentFormPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Student Form"));

        studentFormPanel.setBackground(new Color(245, 245, 245));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.anchor = GridBagConstraints.WEST;



        // Adding components to student form panel

        addLabelAndComponent(studentFormPanel, gbc, "Name:", tfName = new JTextField(20), 0, 0);

        addLabelAndComponent(studentFormPanel, gbc, "Roll Number:", tfRollNumber = new JTextField(20), 1, 0);

        addLabelAndComponent(studentFormPanel, gbc, "Email:", tfEmail = new JTextField(20), 2, 0);

        addLabelAndComponent(studentFormPanel, gbc, "Contact Number:", tfContactNumber = new JTextField(20), 3, 0);

        addLabelAndComponent(studentFormPanel, gbc, "Address:", tfAddress = new JTextField(20), 4, 0);

        addLabelAndComponent(studentFormPanel, gbc, "Gender:", cbGender = new JComboBox<>(new String[]{"Male", "Female", "Other"}), 5, 0);

        addLabelAndComponent(studentFormPanel, gbc, "Nationality:", tfNationality = new JTextField(20), 6, 0);

        addLabelAndComponent(studentFormPanel, gbc, "Year of Passing 10th:", cbYearOfPassing10th = new JComboBox<>(new String[]{"2018", "2019", "2020", "2021", "2022"}), 7, 0);

        addLabelAndComponent(studentFormPanel, gbc, "Year of Passing 12th:", cbYearOfPassing12th = new JComboBox<>(new String[]{"2020", "2021", "2022", "2023", "2024"}), 8, 0);

        addLabelAndComponent(studentFormPanel, gbc, "Points in 10th:", tfPoints10th = new JTextField(20), 9, 0);

        addLabelAndComponent(studentFormPanel, gbc, "Percentage in 12th:", tfPercentage12th = new JTextField(20), 10, 0);



        gbc.gridwidth = 2;

        gbc.anchor = GridBagConstraints.CENTER;

        btnAddStudent = new JButton("Add Student");

        btnUpdateStudent = new JButton("Update Student");

        btnViewStudents = new JButton("View Students");

        addComponentsToPanel(studentFormPanel, gbc, btnAddStudent, 11, 0);

        addComponentsToPanel(studentFormPanel, gbc, btnUpdateStudent, 11, 1);

        addComponentsToPanel(studentFormPanel, gbc, btnViewStudents, 11, 2);



        add(studentFormPanel, BorderLayout.WEST);



        // Student List Panel

        JPanel studentListPanel = new JPanel(new BorderLayout());

        studentListPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Student List"));

        studentListPanel.setBackground(new Color(245, 245, 245));



        studentTableModel = new DefaultTableModel(new Object[]{"Name", "Roll Number", "Email", "Contact Number", "Address", "Gender", "Nationality", "Year of Passing 10th", "Year of Passing 12th", "Points in 10th", "Percentage in 12th"}, 0);

        studentTable = new JTable(studentTableModel);

        JScrollPane scrollPane = new JScrollPane(studentTable);



        studentListPanel.add(scrollPane, BorderLayout.CENTER);



        add(studentListPanel, BorderLayout.CENTER);



        // Panel for course enrollment and grade assignment

        JPanel coursePanel = new JPanel(new GridBagLayout());

        coursePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Course Enrollment and Grade Assignment"));

        coursePanel.setBackground(new Color(245, 245, 245));



        gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.anchor = GridBagConstraints.WEST;



        addLabelAndComponent(coursePanel, gbc, "Students:", cbStudents = new JComboBox<>(), 0, 0);

        addLabelAndComponent(coursePanel, gbc, "Courses:", cbCourses = new JComboBox<>(new String[]{"Math", "Science", "History"}), 1, 0);

        addLabelAndComponent(coursePanel, gbc, "Grades:", cbGrades = new JComboBox<>(new String[]{"A", "B", "C", "D", "F"}), 2, 0);



        gbc.gridwidth = 2;

        gbc.anchor = GridBagConstraints.CENTER;

        btnEnrollStudent = new JButton("Enroll Student");

        btnAssignGrade = new JButton("Assign Grade");

        addComponentsToPanel(coursePanel, gbc, btnEnrollStudent, 3, 0);

        addComponentsToPanel(coursePanel, gbc, btnAssignGrade, 3, 1);



        add(coursePanel, BorderLayout.EAST);



        // Button action listeners

        btnAddStudent.addActionListener(new AddStudentActionListener());

        btnUpdateStudent.addActionListener(new UpdateStudentActionListener());

        btnViewStudents.addActionListener(new ViewStudentsActionListener());

        btnEnrollStudent.addActionListener(new EnrollStudentActionListener());

        btnAssignGrade.addActionListener(new AssignGradeActionListener());

    }



    private void addLabelAndComponent(JPanel panel, GridBagConstraints gbc, String labelText, Component component, int row, int col) {

        gbc.gridx = col;

        gbc.gridy = row;

        panel.add(new JLabel(labelText), gbc);

        gbc.gridx = col + 1;

        panel.add(component, gbc);

    }



    private void addComponentsToPanel(JPanel panel, GridBagConstraints gbc, Component component, int row, int col) {

        gbc.gridx = col;

        gbc.gridy = row;

        panel.add(component, gbc);

    }



    private class AddStudentActionListener implements ActionListener {

        @Override

        public void actionPerformed(ActionEvent e) {

            String name = tfName.getText();

            String rollNumber = tfRollNumber.getText();

            String email = tfEmail.getText();

            String contactNumber = tfContactNumber.getText();

            String address = tfAddress.getText();

            String gender = cbGender.getSelectedItem().toString();

            String nationality = tfNationality.getText();

            int yearOfPassing10th = Integer.parseInt(cbYearOfPassing10th.getSelectedItem().toString());

            int yearOfPassing12th = Integer.parseInt(cbYearOfPassing12th.getSelectedItem().toString());

            double points10th = Double.parseDouble(tfPoints10th.getText());

            double percentage12th = Double.parseDouble(tfPercentage12th.getText());



            Student student = new Student(name, rollNumber, email, contactNumber, address, gender, nationality,

                    yearOfPassing10th, yearOfPassing12th, points10th, percentage12th);



            if (controller.addStudent(student)) {

                JOptionPane.showMessageDialog(StudentManagementSystem.this, "Student added successfully.");

                clearFormFields();

            } else {

                JOptionPane.showMessageDialog(StudentManagementSystem.this, "Student with this Roll Number already exists.");

            }

        }

    }



    private class UpdateStudentActionListener implements ActionListener {

        @Override

        public void actionPerformed(ActionEvent e) {

            String name = tfName.getText();

            String rollNumber = tfRollNumber.getText();

            String email = tfEmail.getText();

            String contactNumber = tfContactNumber.getText();

            String address = tfAddress.getText();

            String gender = cbGender.getSelectedItem().toString();

            String nationality = tfNationality.getText();

            int yearOfPassing10th = Integer.parseInt(cbYearOfPassing10th.getSelectedItem().toString());

            int yearOfPassing12th = Integer.parseInt(cbYearOfPassing12th.getSelectedItem().toString());

            double points10th = Double.parseDouble(tfPoints10th.getText());

            double percentage12th = Double.parseDouble(tfPercentage12th.getText());



            Student updatedStudent = new Student(name, rollNumber, email, contactNumber, address, gender, nationality,

                    yearOfPassing10th, yearOfPassing12th, points10th, percentage12th);



            if (controller.updateStudent(selectedRollNumber, updatedStudent)) {

                JOptionPane.showMessageDialog(StudentManagementSystem.this, "Student updated successfully.");

                clearFormFields();

                selectedRollNumber = null;

            } else {

                JOptionPane.showMessageDialog(StudentManagementSystem.this, "Student not found.");

            }

        }

    }



    private class ViewStudentsActionListener implements ActionListener {

        @Override

        public void actionPerformed(ActionEvent e) {

            studentTableModel.setRowCount(0); // Clear existing rows

            cbStudents.removeAllItems(); // Clear existing items in the JComboBox



            for (Student student : controller.getStudents()) {

                studentTableModel.addRow(new Object[]{

                        student.getName(),

                        student.getRollNumber(),

                        student.getEmail(),

                        student.getContactNumber(),

                        student.getAddress(),

                        student.getGender(),

                        student.getNationality(),

                        student.getYearOfPassing10th(),

                        student.getYearOfPassing12th(),

                        student.getPoints10th(),

                        student.getPercentage12th()

                });

                cbStudents.addItem(student.getName()); // Add student name to the JComboBox

            }



            displayCourseEnrollmentsAndGrades();

        }

    }



    private class EnrollStudentActionListener implements ActionListener {

        @Override

        public void actionPerformed(ActionEvent e) {

            if (cbStudents.getItemCount() > 0) {

                String selectedStudent = cbStudents.getSelectedItem().toString();

                int studentId = getStudentIdByName(selectedStudent);

                String course = cbCourses.getSelectedItem().toString();

                controller.enrollStudentInCourse(studentId, course);

                JOptionPane.showMessageDialog(StudentManagementSystem.this, "Student enrolled in course successfully.");

            } else {

                JOptionPane.showMessageDialog(StudentManagementSystem.this, "No students available to enroll.");

            }

        }

    }



    private class AssignGradeActionListener implements ActionListener {

        @Override

        public void actionPerformed(ActionEvent e) {

            if (cbStudents.getItemCount() > 0) {

                String selectedStudent = cbStudents.getSelectedItem().toString();

                int studentId = getStudentIdByName(selectedStudent);

                String course = cbCourses.getSelectedItem().toString();

                String grade = cbGrades.getSelectedItem().toString();

                controller.assignGrade(studentId, course, grade);

                JOptionPane.showMessageDialog(StudentManagementSystem.this, "Grade assigned to student successfully.");

            } else {

                JOptionPane.showMessageDialog(StudentManagementSystem.this, "No students available to assign grade.");

            }

        }

    }





    private int getStudentIdByName(String name) {

        for (Student student : controller.getStudents()) {

            if (student.getName().equals(name)) {

                return student.getStudentId();

            }

        }

        return -1; // Student not found

    }



    private void clearFormFields() {

        tfName.setText("");

        tfRollNumber.setText("");

        tfEmail.setText("");

        tfContactNumber.setText("");

        tfAddress.setText("");

        cbGender.setSelectedIndex(0);

        tfNationality.setText("");

        cbYearOfPassing10th.setSelectedIndex(0);

        cbYearOfPassing12th.setSelectedIndex(0);

        tfPoints10th.setText("");

        tfPercentage12th.setText("");

    }



    private void displayCourseEnrollmentsAndGrades() {

        Map<String, ArrayList<String>> courseEnrollments = controller.getCourseEnrollments();

        Map<String, Map<String, String>> studentGrades = controller.getStudentGrades();

        // Display course enrollments

        System.out.println("Course Enrollments:");

        for (Map.Entry<String, ArrayList<String>> entry : courseEnrollments.entrySet()) {

            System.out.println("Course: " + entry.getKey());

            System.out.println("Enrolled Students: " + entry.getValue());

        }

        // Display student grades

        System.out.println("\nStudent Grades:");

        for (Map.Entry<String, Map<String, String>> entry : studentGrades.entrySet()) {

            System.out.println("Student: " + entry.getKey());

            System.out.println("Grades: " + entry.getValue());

        }

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            StudentManagementSystem sms = new StudentManagementSystem();

            sms.setVisible(true);

        });

    }

}
