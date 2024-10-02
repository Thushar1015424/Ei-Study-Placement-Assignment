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

        System.out.println("Welcome to the Virtual Classroom Manager.");
        System.out.println("Type 'help' for a list of commands.");

        while (running) {
            System.out.print("\n> ");
            String input = scanner.nextLine();
            String[] commandParts = input.split(" ");

            if (commandParts.length == 0) {
                System.out.println("Invalid command. Type 'help' for a list of commands.");
                continue;
            }

            String command = commandParts[0];

            switch (command.toLowerCase()) {
                case "add_classroom":
                    if (commandParts.length == 2) {
                        String className = commandParts[1];
                        manager.addClassroom(className);
                    } else {
                        System.out.println("Usage: add_classroom <classroom_name>");
                    }
                    break;

                case "add_student":
                    if (commandParts.length == 3) {
                        String studentId = commandParts[1];
                        String className = commandParts[2];
                        Classroom classroom = manager.getClassroomByName(className);
                        if (classroom != null) {
                            Student student = new Student(studentId);
                            classroom.addStudent(student);
                        } else {
                            System.out.println("Classroom not found.");
                        }
                    } else {
                        System.out.println("Usage: add_student <student_id> <classroom_name>");
                    }
                    break;

                case "schedule_assignment":
                    if (commandParts.length == 3) {
                        String className = commandParts[1];
                        String assignmentName = commandParts[2];
                        Classroom classroom = manager.getClassroomByName(className);
                        if (classroom != null) {
                            Assignment assignment = new Assignment(assignmentName);
                            classroom.scheduleAssignment(assignment);
                        } else {
                            System.out.println("Classroom not found.");
                        }
                    } else {
                        System.out.println("Usage: schedule_assignment <classroom_name> <assignment_name>");
                    }
                    break;

                case "submit_assignment":
                    if (commandParts.length == 4) {
                        String studentId = commandParts[1];
                        String className = commandParts[2];
                        String assignmentName = commandParts[3];
                        Classroom classroom = manager.getClassroomByName(className);
                        if (classroom != null) {
                            classroom.submitAssignment(studentId, assignmentName);
                        } else {
                            System.out.println("Classroom not found.");
                        }
                    } else {
                        System.out.println("Usage: submit_assignment <student_id> <classroom_name> <assignment_name>");
                    }
                    break;

                case "list_classrooms":
                    manager.listClassrooms();
                    break;

                case "list_students":
                    if (commandParts.length == 2) {
                        String className = commandParts[1];
                        Classroom classroom = manager.getClassroomByName(className);
                        if (classroom != null) {
                            classroom.listStudents();
                        } else {
                            System.out.println("Classroom not found.");
                        }
                    } else {
                        System.out.println("Usage: list_students <classroom_name>");
                    }
                    break;

                case "list_assignments":
                    if (commandParts.length == 2) {
                        String className = commandParts[1];
                        Classroom classroom = manager.getClassroomByName(className);
                        if (classroom != null) {
                            classroom.listAssignments();
                        } else {
                            System.out.println("Classroom not found.");
                        }
                    } else {
                        System.out.println("Usage: list_assignments <classroom_name>");
                    }
                    break;

                case "remove_classroom":
                    if (commandParts.length == 2) {
                        String className = commandParts[1];
                        manager.removeClassroom(className);
                    } else {
                        System.out.println("Usage: remove_classroom <classroom_name>");
                    }
                    break;

                case "exit":
                    running = false;
                    System.out.println("Exiting the Virtual Classroom Manager.");
                    break;

                case "help":
                    System.out.println("Available commands:");
                    System.out.println("  add_classroom <classroom_name>");
                    System.out.println("  add_student <student_id> <classroom_name>");
                    System.out.println("  schedule_assignment <classroom_name> <assignment_name>");
                    System.out.println("  submit_assignment <student_id> <classroom_name> <assignment_name>");
                    System.out.println("  list_classrooms");
                    System.out.println("  list_students <classroom_name>");
                    System.out.println("  list_assignments <classroom_name>");
                    System.out.println("  remove_classroom <classroom_name>");
                    System.out.println("  exit");
                    break;

                default:
                    System.out.println("Unknown command. Type 'help' for a list of commands.");
                    break;
            }
        }

        scanner.close();
    }
}
