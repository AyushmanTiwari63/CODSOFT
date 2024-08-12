import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Course Class
class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private List<Student> enrolledStudents;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }
https://www.onlinegdb.com/#tab-stdin
    public String getSchedule() {
        return schedule;
    }

    public int getAvailableSlots() {
        return capacity - enrolledStudents.size();
    }

    public boolean addStudent(Student student) {
        if (enrolledStudents.size() < capacity) {
            enrolledStudents.add(student);
            return true;
        }
        return false;
    }

    public void removeStudent(Student student) {
        enrolledStudents.remove(student);
    }

    public void displayCourseDetails() {
        System.out.println("Course Code: " + courseCode);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Schedule: " + schedule);
        System.out.println("Capacity: " + capacity);
        System.out.println("Available Slots: " + getAvailableSlots());
        System.out.println();
    }
}

// Student Class
class Student {
    private String studentId;
    private String name;
    private List<Course> registeredCourses;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(Course course) {
        if (course.addStudent(this)) {
            registeredCourses.add(course);
            System.out.println("Successfully registered for " + course.getTitle());
        } else {
            System.out.println("Course " + course.getTitle() + " is full. Registration failed.");
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            course.removeStudent(this);
            System.out.println("Successfully dropped " + course.getTitle());
        } else {
            System.out.println("You are not registered for " + course.getTitle());
        }
    }

    public void displayRegisteredCourses() {
        if (registeredCourses.isEmpty()) {
            System.out.println("No registered courses.");
        } else {
            System.out.println("Registered courses:");
            for (Course course : registeredCourses) {
                System.out.println(course.getCourseCode() + ": " + course.getTitle());
            }
        }
    }
}

// RegistrationSystem Class
class RegistrationSystem {
    private List<Course> courses;
    private List<Student> students;

    public RegistrationSystem() {
        courses = new ArrayList<>();
        students = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    public Student findStudentById(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    public void displayCourses() {
        System.out.println("Available courses:");
        for (Course course : courses) {
            course.displayCourseDetails();
        }
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {
        RegistrationSystem system = new RegistrationSystem();

        // Adding some courses
        system.addCourse(new Course("CSE101", "Introduction to Computer Science", "Basic concepts of computer science.", 30, "MWF 9:00-10:00 AM"));
        system.addCourse(new Course("MATH101", "Calculus I", "Introduction to calculus.", 25, "TTh 10:00-11:30 AM"));
        system.addCourse(new Course("PHY101", "Physics I", "Fundamentals of physics.", 20, "MWF 11:00-12:00 PM"));

        // Adding a student
        system.addStudent(new Student("S001", "John Doe"));

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Register for a course");
            System.out.println("2. Drop a course");
            System.out.println("3. Display registered courses");
            System.out.println("4. Display available courses");
            System.out.println("5. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter student ID: ");
                    String studentId = scanner.nextLine();
                    System.out.print("Enter course code: ");
                    String courseCode = scanner.nextLine();
                    Student student = system.findStudentById(studentId);
                    Course course = system.findCourseByCode(courseCode);
                    if (student != null && course != null) {
                        student.registerCourse(course);
                    } else {
                        System.out.println("Invalid student ID or course code.");
                    }
                    break;
                case 2:
                    System.out.print("Enter student ID: ");
                    studentId = scanner.nextLine();
                    System.out.print("Enter course code: ");
                    courseCode = scanner.nextLine();
                    student = system.findStudentById(studentId);
                    course = system.findCourseByCode(courseCode);
                    if (student != null && course != null) {
                        student.dropCourse(course);
                    } else {
                        System.out.println("Invalid student ID or course code.");
                    }
                    break;
                case 3:
                    System.out.print("Enter student ID: ");
                    studentId = scanner.nextLine();
                    student = system.findStudentById(studentId);
                    if (student != null) {
                        student.displayRegisteredCourses();
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 4:
                    system.displayCourses();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
