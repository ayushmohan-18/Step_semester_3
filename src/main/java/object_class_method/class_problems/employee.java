
package main.java.object_class_method.class_problems;

import java.time.LocalDate;

enum LeaveStatus {
    PENDING, APPROVED, REJECTED
}

abstract class Employee {
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract boolean isLeaveAllowed(int days);
}

class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String name) {
        super(name);
    }

    public boolean isLeaveAllowed(int days) {
        return days > 0 && days <= 30;
    }
}

class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String name) {
        super(name);
    }

    public boolean isLeaveAllowed(int days) {
        return days > 0 && days <= 10;
    }
}

class Contractor extends Employee {
    public Contractor(String name) {
        super(name);
    }

    public boolean isLeaveAllowed(int days) {
        return days > 0 && days <= 5;
    }
}

class LeaveRequest {
    private Employee employee;
    private LocalDate startDate;
    private LocalDate endDate;
    private LeaveStatus status;

    public LeaveRequest(Employee employee,
                        LocalDate startDate,
                        LocalDate endDate) {
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = LeaveStatus.PENDING;
    }

    public LeaveStatus getStatus() {
        return status;
    }

    public void review(boolean approve, String reviewer) {
        if (status != LeaveStatus.PENDING) {
            System.out.println("Request has already been reviewed.");
            return;
        }

        status = approve
                ? LeaveStatus.APPROVED
                : LeaveStatus.REJECTED;

        System.out.println(employee.getName()
                + "'s leave request (" + startDate + " to "
                + endDate + ") "
                + status.toString().toLowerCase()
                + " by " + reviewer + ".");

        System.out.println("Status: " + status);
    }

    public void changeStatus(LeaveStatus newStatus) {
        if (status != LeaveStatus.PENDING) {
            System.out.println("Cannot change leave request status from "
                    + status + " to " + newStatus);
            return;
        }

        if (newStatus != LeaveStatus.PENDING) {
            status = newStatus;
        }
    }
}

public class employee {
    public static void main(String[] args) {
        Employee john = new FullTimeEmployee("John");
        Employee jane = new PartTimeEmployee("Jane");

        submitLeave(john,
                LocalDate.of(2026, 1, 1),
                LocalDate.of(2026, 1, 5),
                "Alice", true);

        submitLeave(jane,
                LocalDate.of(2026, 2, 10),
                LocalDate.of(2026, 2, 11),
                "Bob", false);
    }

    static void submitLeave(Employee employee,
                           LocalDate start,
                           LocalDate end,
                           String reviewer,
                           boolean approve) {
        if (end.isBefore(start)) {
            System.out.println("Invalid leave dates.");
            return;
        }

        int days = (int) (end.toEpochDay()
                - start.toEpochDay()) + 1;

        if (!employee.isLeaveAllowed(days)) {
            System.out.println("Leave policy limit exceeded for "
                    + employee.getName());
            return;
        }

        LeaveRequest request =
                new LeaveRequest(employee, start, end);

        System.out.println("Leave request submitted for "
                + employee.getName() + " (" + start + " to "
                + end + "). Status: PENDING");

        request.review(approve, reviewer);

        if (employee.getName().equals("John")) {
            request.changeStatus(LeaveStatus.PENDING);
        }
    }
}