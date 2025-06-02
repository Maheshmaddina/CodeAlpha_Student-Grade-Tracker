import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private final String name;
    private final ArrayList<Double> grades;

    public Student(String name) {
        this.name = name;
        this.grades = new ArrayList<>();
    }

    public void addGrade(double grade) {
        grades.add(grade);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Double> getGrades() {
        return grades;
    }

    public double getAverage() {
        if (grades.isEmpty()) return 0;
        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }

    public double getHighest() {
        if (grades.isEmpty()) return 0;
        double highest = grades.get(0);
        for (double grade : grades) {
            if (grade > highest) {
                highest = grade;
            }
        }
        return highest;
    }

    public double getLowest() {
        if (grades.isEmpty()) return 0;
        double lowest = grades.get(0);
        for (double grade : grades) {
            if (grade < lowest) {
                lowest = grade;
            }
        }
        return lowest;
    }
}

public class GradeTracker {
    private final ArrayList<Student> students;
    private final Scanner scanner;

    public GradeTracker() {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.println("\nStudent Grade Tracker");
            System.out.println("1. Add Student");
            System.out.println("2. Add Grade");
            System.out.println("3. View Student Report");
            System.out.println("4. View Class Summary");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> addGrade();
                case 3 -> viewStudentReport();
                case 4 -> viewClassSummary();
                case 5 -> {
                    System.out.println("Exiting program...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        students.add(new Student(name));
        System.out.println("Student added successfully!");
    }

    private void addGrade() {
        if (students.isEmpty()) {
            System.out.println("No students available. Please add a student first.");
            return;
        }

        System.out.println("Select a student:");
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i).getName());
        }

        int studentIndex = scanner.nextInt() - 1;
        if (studentIndex < 0 || studentIndex >= students.size()) {
            System.out.println("Invalid student selection.");
            return;
        }

        System.out.print("Enter grade: ");
        double grade = scanner.nextDouble();
        students.get(studentIndex).addGrade(grade);
        System.out.println("Grade added successfully!");
    }

    private void viewStudentReport() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        System.out.println("Select a student:");
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i).getName());
        }

        int studentIndex = scanner.nextInt() - 1;
        if (studentIndex < 0 || studentIndex >= students.size()) {
            System.out.println("Invalid student selection.");
            return;
        }

        Student student = students.get(studentIndex);
        System.out.println("\nStudent Report for " + student.getName());
        System.out.println("Grades: " + student.getGrades());
        System.out.println("Average: " + student.getAverage());
        System.out.println("Highest: " + student.getHighest());
        System.out.println("Lowest: " + student.getLowest());
    }

    private void viewClassSummary() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        System.out.println("\nClass Summary");
        System.out.println("--------------------------------------------------");
        System.out.printf("%-20s %-10s %-10s %-10s\n", "Student", "Average", "Highest", "Lowest");
        System.out.println("--------------------------------------------------");

        double classAverage = 0;
        double classHighest = Double.MIN_VALUE;
        double classLowest = Double.MAX_VALUE;

        for (Student student : students) {
            double avg = student.getAverage();
            double high = student.getHighest();
            double low = student.getLowest();

            System.out.printf("%-20s %-10.2f %-10.2f %-10.2f\n", 
                student.getName(), avg, high, low);

            classAverage += avg;
            if (high > classHighest) classHighest = high;
            if (low < classLowest) classLowest = low;
        }

        classAverage /= students.size();

        System.out.println("--------------------------------------------------");
        System.out.printf("%-20s %-10.2f %-10.2f %-10.2f\n", 
            "CLASS TOTALS", classAverage, classHighest, classLowest);
        System.out.println("--------------------------------------------------");
    }

    public static void main(String[] args) {
        GradeTracker tracker = new GradeTracker();
        tracker.run();
    }
}
