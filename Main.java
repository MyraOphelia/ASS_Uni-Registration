import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Lecturer> lecturers = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<>();

        while (true) {
            System.out.println("\nWelcome to the Course Management System!");
            System.out.println("1. Admin Login");
            System.out.println("2. Lecturer Login");
            System.out.println("3. Student Login");

            System.out.print("\nSelect an option: ");
            String userType = scanner.nextLine();

            if ("1".equals(userType)) {
                adminLogin(students, lecturers, courses, scanner);
            } else if ("2".equals(userType)) {
                lecturerLogin(lecturers, courses, scanner);
            } else if ("3".equals(userType)) {
                studentLogin(students, courses, scanner);
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void studentLogin(ArrayList<Student> students, ArrayList<Course> courses, Scanner scanner) {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        Student student = findStudent(students, studentId);

        if (student == null) {
            student = new Student(studentId);
            students.add(student);
        }

        while (true) {
            System.out.println("\nStudent Menu:");
            System.out.println("1. Enroll in a course");
            System.out.println("2. View enrolled courses");
            System.out.println("3. Logout");
            System.out.print("\nSelect an option: ");

            String studentChoice = scanner.nextLine();

            if ("1".equals(studentChoice)) {
                enrollStudentInCourse(student, courses, scanner);
            } else if ("2".equals(studentChoice)) {
                student.viewEnrolledCourses();
            } else if ("3".equals(studentChoice)) {
                System.out.println("Logging out as a student.");
                return;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void lecturerLogin(ArrayList<Lecturer> lecturers, ArrayList<Course> courses, Scanner scanner) {
        // Placeholder logic for lecturer login
        System.out.println("Lecturer login.");
    }

    private static void adminLogin(ArrayList<Student> students, ArrayList<Lecturer> lecturers, ArrayList<Course> courses, Scanner scanner) {
        System.out.println("\nAdmin Menu:");
        System.out.println("1. Create Student");
        System.out.println("2. Create Lecturer");
        System.out.println("3. Create Course");
        System.out.println("4. Assign Course to Lecturer");
        System.out.println("5. View Students and Lecturers for a Course");
        System.out.println("6. Logout");
        System.out.print("\nSelect an option: ");

        String adminChoice = scanner.nextLine();

        if ("1".equals(adminChoice)) {
            createStudent(students, scanner);
        } else if ("2".equals(adminChoice)) {
            createLecturer(lecturers, scanner);
        } else if ("3".equals(adminChoice)) {
            createCourse(courses, scanner);
        } else if ("4".equals(adminChoice)) {
            assignCourseToLecturer(lecturers, courses, scanner);
        } else if ("5".equals(adminChoice)) {
            viewStudentsAndLecturersForCourse(students, lecturers, courses, scanner);
        } else if ("6".equals(adminChoice)) {
            System.out.println("Logging out as an admin.");
            return;
        } else {
            System.out.println("Invalid option. Please try again.");
        }
    }

    private static void createStudent(ArrayList<Student> students, Scanner scanner) {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        Student student = findStudent(students, studentId);

        if (student == null) {
            student = new Student(studentId);
            students.add(student);
            System.out.println("Student created successfully.");
        } else {
            System.out.println("Student with ID " + studentId + " already exists.");
        }
    }

    private static void createLecturer(ArrayList<Lecturer> lecturers, Scanner scanner) {
        System.out.print("Enter lecturer ID: ");
        String lecturerId = scanner.nextLine();
        Lecturer lecturer = findLecturer(lecturers, lecturerId);

        if (lecturer == null) {
            lecturer = new Lecturer(lecturerId);
            lecturers.add(lecturer);
            System.out.println("Lecturer created successfully.");
        } else {
            System.out.println("Lecturer with ID " + lecturerId + " already exists.");
        }
    }

    private static void createCourse(ArrayList<Course> courses, Scanner scanner) {
        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine();
        System.out.print("Enter course credits: ");
        int credits = scanner.nextInt();
        scanner.nextLine();

        Course course = new Course(courseName, credits);
        courses.add(course);
        System.out.println("Course created successfully.");
    }

    private static void assignCourseToLecturer(ArrayList<Lecturer> lecturers, ArrayList<Course> courses, Scanner scanner) {
        System.out.print("Enter lecturer ID: ");
        String lecturerId = scanner.nextLine();
        Lecturer lecturer = findLecturer(lecturers, lecturerId);

        if (lecturer != null) {
            System.out.println("Available Courses:");
            for (int i = 0; i < courses.size(); i++) {
                System.out.println((i + 1) + ". " + courses.get(i).getName());
            }

            System.out.print("Enter the number of the course to assign: ");
            int courseNumber = scanner.nextInt();
            scanner.nextLine();

            if (courseNumber >= 1 && courseNumber <= courses.size()) {
                Course selectedCourse = courses.get(courseNumber - 1);
                lecturer.assignCourse(selectedCourse);
                System.out.println("Course assigned successfully.");
            } else {
                System.out.println("Invalid course number. Please try again.");
            }
        } else {
            System.out.println("Lecturer " + lecturerId + " not found.");
        }
    }

    private static void viewStudentsAndLecturersForCourse(ArrayList<Student> students, ArrayList<Lecturer> lecturers, ArrayList<Course> courses, Scanner scanner) {
        System.out.println("Available Courses:");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + ". " + courses.get(i).getName());
    
        }

        System.out.print("Enter the number of the course to view students and lecturers: ");
        int courseNumber = scanner.nextInt();
        scanner.nextLine();

        if (courseNumber >= 1 && courseNumber <= courses.size()) {
            Course selectedCourse = courses.get(courseNumber - 1);

            System.out.println("\nStudents enrolled in " + selectedCourse.getName() + ":");
            for (Student student : students) {
                if (student.isEnrolledInCourse(selectedCourse)) {
                    System.out.println("- Student ID: " + student.getStudentId());
                }
            }

            System.out.println("\nLecturer assigned to " + selectedCourse.getName() + ":");
            for (Lecturer lecturer : lecturers) {
                if (lecturer.isTeachingCourse(selectedCourse)) {
                    System.out.println("- Lecturer ID: " + lecturer.getLecturerId());
                }
            }
        } else {
            System.out.println("Invalid course number. Please try again.");
        }
    }

    private static void enrollStudentInCourse(Student student, ArrayList<Course> courses, Scanner scanner) {
        System.out.println("Available Courses:");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + ". " + courses.get(i).getName());
        }

        System.out.print("Enter the number of the course to enroll in: ");
        int courseNumber = scanner.nextInt();
        scanner.nextLine();

        if (courseNumber >= 1 && courseNumber <= courses.size()) {
            Course selectedCourse = courses.get(courseNumber - 1);
            if (student.enrollInCourse(selectedCourse)) {
                System.out.println("\nEnrolled in " + selectedCourse.getName() + ".");
            } else {
                System.out.println("Unable to enroll. Already enrolled in the course.");
            }
        } else {
            System.out.println("Invalid course number. Please try again.");
        }
    }

    private static Student findStudent(ArrayList<Student> students, String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    private static Lecturer findLecturer(ArrayList<Lecturer> lecturers, String lecturerId) {
        for (Lecturer lecturer : lecturers) {
            if (lecturer.getLecturerId().equals(lecturerId)) {
                return lecturer;
            }
        }
        return null;
    }

    private static class Student {
        private String studentId;
        private ArrayList<Course> enrolledCourses;

        public Student(String studentId) {
            this.studentId = studentId;
            this.enrolledCourses = new ArrayList<>();
        }

        public String getStudentId() {
            return studentId;
        }

        public boolean enrollInCourse(Course course) {
            if (!enrolledCourses.contains(course)) {
                enrolledCourses.add(course);
                return true;
            }
            return false;
        }

        public void viewEnrolledCourses() {
            if (enrolledCourses.isEmpty()) {
                System.out.println("No enrolled courses.");
            } else {
                System.out.println("\nEnrolled Courses:");
                for (Course course : enrolledCourses) {
                    System.out.println("- " + course.getName());
                }
            }
        }

        public boolean isEnrolledInCourse(Course course) {
            return enrolledCourses.contains(course);
        }
    }

    private static class Lecturer {
        private String lecturerId;
        private ArrayList<Course> assignedCourses;

        public Lecturer(String lecturerId) {
            this.lecturerId = lecturerId;
            this.assignedCourses = new ArrayList<>();
        }

        public String getLecturerId() {
            return lecturerId;
        }

        public void assignCourse(Course course) {
            assignedCourses.add(course);
        }

        public boolean isTeachingCourse(Course course) {
            return assignedCourses.contains(course);
        }
    }

    private static class Course {
        private String name;
        private int credits;

        public Course(String name, int credits) {
            this.name = name;
            this.credits = credits;
        }

        public String getName() {
            return name;
        }
    }
}
