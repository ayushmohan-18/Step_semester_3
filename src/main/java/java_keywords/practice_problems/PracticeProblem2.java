package main.java.java_keywords.practice_problems;

public class PracticeProblem2 {

    static class Employee {
        String id;
        double salary;

        Employee(String id, double salary) {
            this.id = id;
            this.salary = salary;
        }

        void raiseSalary(double salary) {
            this.salary += salary;
        }

        void printSalary() {
            System.out.println(id + " | Final Salary: Rs " + salary);
        }
    }

    public static void main(String[] args) {

        Employee[] employees = {
            new Employee("E-101", 40000),
            new Employee("E-102", 55000),
            new Employee("E-103", 62000),
            new Employee("E-104", 48000)
        };

        for (int i = 0; i < employees.length; i++) {
            employees[i].raiseSalary(5000);
        }

        for (int i = 0; i < employees.length; i++) {
            employees[i].printSalary();
        }
    }
}