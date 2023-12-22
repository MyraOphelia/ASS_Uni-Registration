import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Course> courses = initializeCourses();

        while (true) {
            System.out.println("\nWelcome to the Course Management System!");
            System.out.println("1. Student Login");
            System.out.println("2. Lecturer Login");
            System.out.println("3. Admin Login");
        
            System.out.print("\nSelect an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            if (choice == 1) {
                studentLogin(students, courses, scanner);
            } else if (choice == 2) {
                lecturerLogin(courses, scanner);
            } else if (choice == 3) {
                adminLogin(students, courses, scanner);
            
            } else {
                System.out.println("Invalid. Please try again.");
            }
        }
    }

    private static ArrayList<Course> initializeCourses() {
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course("Computer Science", 4));
        courses.add(new Course("Mathematics", 3));
        // Add more courses as needed
        return courses;
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

            int studentChoice = scanner.nextInt();
            scanner.nextLine();

            if (studentChoice == 1) {
                enrollStudentInCourse(student, courses, scanner);
            } else if (studentChoice == 2) {
                student.viewEnrolledCourses();
            } else if (studentChoice == 3) {
                System.out.println("Logging out as a student.");
                return;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }
    private static void lecturerLogin(ArrayList<Course> courses, Scanner scanner) {
      
    }

    private static void adminLogin(ArrayList<Student> students, ArrayList<Course> courses, Scanner scanner) {
    
    }

    public static void enrollStudentInCourse(Student student, ArrayList<Course> courses, Scanner scanner) {
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
}
class Student {
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
}
class Course {
    private String name;
    private int credits;

    public Course(String name, int credits) {
        this.name = name;
        this.credits = credits;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }
}

