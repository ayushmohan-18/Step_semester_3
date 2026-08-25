package oop.class_problems;

public class Student {

    String name;
    int attendance;

    static String collegeName = "SRM Institute of Science and Technology";

    static int studentCount = 0;

    public Student(String name, int attendance) {
        this.name = name;
        this.attendance = attendance;

        studentCount++;
    }

    public static void printCollegeInfo() {
        System.out.println(collegeName);
        System.out.println("Students created: " + studentCount);
    }
    public static void main(String[] args) {

        Student student1 = new Student("Ravi", 85);

        Student student2 = new Student("Anitha", 90);

        System.out.println("2 Student objects created");

        Student.printCollegeInfo();
    }
}