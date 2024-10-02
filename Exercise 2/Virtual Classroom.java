import java.util.*;
import java.util.Scanner;

// To Manage Classrooms, Students, and Assignments - Singleton Pattern
public class ClassroomManager {
    private static ClassroomManager instance;
    private List<Classroom> classrooms;

    private ClassroomManager() {
        classrooms = new ArrayList<>();
    }

    public static ClassroomManager getInstance() {
        if (instance == null) {
            instance = new ClassroomManager();
        }
        return instance;
    }

    public void addClassroom(String name) {
        Classroom classroom = new Classroom(name);
        classrooms.add(classroom);
        System.out.println("Classroom " + name + " has been created.");
    }

    public void listClassrooms() {
        if (classrooms.isEmpty()) {
            System.out.println("No classrooms available.");
        } else {
            for (Classroom classroom : classrooms) {
                System.out.println("Classroom: " + classroom.getName());
            }
        }
    }

    public Classroom getClassroomByName(String name) {
        for (Classroom classroom : classrooms) {
            if (classroom.getName().equals(name)) {
                return classroom;
            }
        }
        return null;
    }

    public void removeClassroom(String name) {
        Classroom classroom = getClassroomByName(name);
        if (classroom != null) {
            classrooms.remove(classroom);
            System.out.println("Classroom " + name + " has been removed.");
        } else {
            System.out.println("Classroom not found.");
        }
    }
}

// Classroom Representation
public class Classroom {
    private String name;
    private List<Student> students;
    private List<Assignment> assignments;

    public Classroom(String name) {
        this.name = name;
        this.students = new ArrayList<>();
        this.assignments = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student " + student.getId() + " has been enrolled in " + name);
    }

    public void listStudents() {
        if (students.isEmpty()) {
            System.out.println("No students enrolled in " + name);
        } else {
            for (Student student : students) {
                System.out.println("Student ID: " + student.getId());
            }
        }
    }

    public void scheduleAssignment(Assignment assignment) {
        assignments.add(assignment);
        System.out.println("Assignment for " + name + " has been scheduled.");
    }

    public void submitAssignment(String studentId, String assignmentName) {
        for (Assignment assignment : assignments) {
            if (assignment.getName().equals(assignmentName)) {
                assignment.submit(studentId);
                return;
            }
        }
        System.out.println("Assignment not found.");
    }

    public void listAssignments() {
        if (assignments.isEmpty()) {
            System.out.println("No assignments scheduled for " + name);
        } else {
            for (Assignment assignment : assignments) {
                System.out.println("Assignment: " + assignment.getName());
            }
        }
    }
}

// Student Representation
public class Student {
    private String id;

    public Student(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

// Assignment
import java.util.HashSet;
import java.util.Set;

public class Assignment {
    private String name;
    private Set<String> submissions;

    public Assignment(String name) {
        this.name = name;
        this.submissions = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void submit(String studentId) {
        if (submissions.contains(studentId)) {
            System.out.println("Student " + studentId + " has already submitted this assignment.");
        } else {
            submissions.add(studentId);
            System.out.println("Assignment submitted by Student " + studentId);
        }
    }
}

// Main Application
public class VirtualClassroomManagerApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClassroomManager manager = ClassroomManager.getInstance();
        boolean running = true;

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Classroom");
            System.out.println("2. Add Student to Classroom");
            System.out.println("3. Schedule Assignment");
            System.out.println("4. Submit Assignment");
            System.out.println("5. List Classrooms");
            System.out.println("6. List Students in Classroom");
            System.out.println("7. List Assignments in Classroom");
            System.out.println("8. Remove Classroom");
            System.out.println("9. Exit");

            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter classroom name: ");
                    String className = scanner.nextLine();
                    manager.addClassroom(className);
                    break;
                case 2:
                    System.out.print("Enter student ID: ");
                    String studentId = scanner.nextLine();
                    System.out.print("Enter classroom name: ");
                    className = scanner.nextLine();
                    Classroom classroom = manager.getClassroomByName(className);
                    if (classroom != null) {
                        Student student = new Student(studentId);
                        classroom.addStudent(student);
                    } else {
                        System.out.println("Classroom not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter classroom name: ");
                    className = scanner.nextLine();
                    classroom = manager.getClassroomByName(className);
                    if (classroom != null) {
                        System.out.print("Enter assignment name: ");
                        String assignmentName = scanner.nextLine();
                        Assignment assignment = new Assignment(assignmentName);
                        classroom.scheduleAssignment(assignment);
                    } else {
                        System.out.println("Classroom not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter student ID: ");
                    studentId = scanner.nextLine();
                    System.out.print("Enter classroom name: ");
                    className = scanner.nextLine();
                    classroom = manager.getClassroomByName(className);
                    if (classroom != null) {
                        System.out.print("Enter assignment name: ");
                        String assignmentName = scanner.nextLine();
                        classroom.submitAssignment(studentId, assignmentName);
                    } else {
                        System.out.println("Classroom not found.");
                    }
                    break;
                case 5:
                    manager.listClassrooms();
                    break;
                case 6:
                    System.out.print("Enter classroom name: ");
                    className = scanner.nextLine();
                    classroom = manager.getClassroomByName(className);
                    if (classroom != null) {
                        classroom.listStudents();
                    } else {
                        System.out.println("Classroom not found.");
                    }
                    break;
                case 7:
                    System.out.print("Enter classroom name: ");
                    className = scanner.nextLine();
                    classroom = manager.getClassroomByName(className);
                    if (classroom != null) {
                        classroom.listAssignments();
                    } else {
                        System.out.println("Classroom not found.");
                    }
                    break;
                case 8:
                    System.out.print("Enter classroom name to remove: ");
                    className = scanner.nextLine();
                    manager.removeClassroom(className);
                    break;
                case 9:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }

        scanner.close();
    }
}
