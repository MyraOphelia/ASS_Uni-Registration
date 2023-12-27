import java.util.Scanner;

public class Myra1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        User[] users = new User[3];
        users[0] = new Student("Student 1", "Computer Science", "Computer Science Lab", 3.5);
        users[1] = new Lecturer("Lecturer 1", "Computer Science", "Computer Science Department");
        users[2] = new Admin("Admin 1", "IT Department", "Computing Center");

        System.out.println("Choose user type:");
        System.out.println("1. Student");
        System.out.println("2. Lecturer");
        System.out.println("3. Admin");
        int userChoice = scanner.nextInt();

        User currentUser = users[userChoice - 1];
        currentUser.printUserInfo();

        if (currentUser instanceof Student) {
            System.out.println("Enroll in course:");
            String course = scanner.next();
            ((Student) currentUser).enrollInCourse(course);
        } else if (currentUser instanceof Lecturer) {
            System.out.println("Grade a student:");
            String student = scanner.next();
            int grade = scanner.nextInt();
            ((Lecturer) currentUser).gradeStudent(student, grade);
        } else if (currentUser instanceof Admin) {
            System.out.println("Manage admin tasks...");
        }
    }
}

public abstract class User {
    protected String name;
    protected String department;
    protected UserType userType;

    public User(String name, String department, UserType userType) {
        this.name = name;
        this.department = department;
        this.userType = userType;
    }

    public abstract void printUserInfo();
}

public class Student extends User {
    private String major;
    private String course;
    private double gpa;

    public Student(String name, String major, String course, double gpa) {
        super(name, "", UserType.STUDENT);
        this.major = major;
        this.course = course;
        this.gpa = gpa;
    }

    public void enrollInCourse(String course) {
        this.course = course;
    }

    @Override
    public void printUserInfo() {
        System.out.println("Name: " + name);
        System.out.println("Major: " + major);
        System.out.println("Course: " + course);
        System.out.println("GPA: " + gpa);
    }
}

public class Lecturer extends User {
    private String subject;
    private String department;

    public Lecturer(String name, String department, String subject) {
        super(name, department, UserType.LECTURER);
        this.subject = subject;
        this.department = department;
    }

    public void gradeStudent(String student, int grade) {
        // Assuming there is a student object, replace this with the actual code to grade the student
        System.out.println("Grade for " + student + ": " + grade);
    }

    @Override
    public void printUserInfo() {
        System.out.println("Name: " + name);
        System.out.println("Subject: " + subject);
        System.out.println("Department: " + department);
    }
}

public class Admin extends User {
    private String department;

    public Admin(String name, String department, String title) {
        super(name, department, UserType.ADMIN);
        this.department = department;
    }

    @Override
    public void printUserInfo() {
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
    }
}

public enum UserType {
    STUDENT,
    LECTURER,
    ADMIN
}