
package main.java.object_class_method.assignment_problems;

import java.time.LocalDate;

class AssignmentStudent {
    private String name;

    public AssignmentStudent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

abstract class Assignment {
    private String title;
    private int maxMarks;
    private LocalDate dueDate;

    public Assignment(String title, int maxMarks, LocalDate dueDate) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDate = dueDate;
    }

    public String getTitle() { return title; }
    public int getMaxMarks() { return maxMarks; }
    public LocalDate getDueDate() { return dueDate; }

    public abstract double calculateFinalMarks(int awardedMarks, long lateDays);
}

class CodingAssignment extends Assignment {
    public CodingAssignment(String title, int maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }

    public double calculateFinalMarks(int awardedMarks, long lateDays) {
        double penalty = lateDays * 0.10;
        return Math.max(0, awardedMarks * (1 - penalty));
    }
}

class WrittenAssignment extends Assignment {
    public WrittenAssignment(String title, int maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }

    public double calculateFinalMarks(int awardedMarks, long lateDays) {
        double penalty = lateDays * 0.20;
        return Math.max(0, awardedMarks * (1 - penalty));
    }
}

class Submission {
    private AssignmentStudent student;
    private Assignment assignment;
    private LocalDate submissionDate;
    private String status = "Submitted";
    private double finalMarks;

    public Submission(AssignmentStudent student, Assignment assignment) {
        this.student = student;
        this.assignment = assignment;
    }

    public void submit(LocalDate date) {
        if (status.equals("Graded")) {
            System.out.println("Cannot resubmit: '" +
                    assignment.getTitle() + "' has already been graded.");
            return;
        }

        submissionDate = date;

        long lateDays = Math.max(0,
                java.time.temporal.ChronoUnit.DAYS.between(
                        assignment.getDueDate(), submissionDate));

        if (lateDays == 0) {
            System.out.println(student.getName() + "'s submission for '" +
                    assignment.getTitle() + "' received (on time). Status: Submitted.");
        } else {
            System.out.println(student.getName() + "'s submission for '" +
                    assignment.getTitle() + "' received (" +
                    lateDays + " days late). Status: Submitted.");
        }
    }

    public void grade(int awardedMarks) {
        if (submissionDate == null) {
            System.out.println("Cannot grade: assignment has not been submitted.");
            return;
        }

        if (status.equals("Graded")) {
            System.out.println("Submission has already been graded.");
            return;
        }

        if (awardedMarks < 0 || awardedMarks > assignment.getMaxMarks()) {
            System.out.println("Invalid awarded marks.");
            return;
        }

        long lateDays = Math.max(0,
                java.time.temporal.ChronoUnit.DAYS.between(
                        assignment.getDueDate(), submissionDate));

        finalMarks = assignment.calculateFinalMarks(awardedMarks, lateDays);
        status = "Graded";

        if (lateDays > 0) {
            System.out.printf("%s graded: %.0f/%d after %.0f%% late penalty. Status: Graded.%n",
                    student.getName(), finalMarks, assignment.getMaxMarks(),
                    Math.min(100, lateDays *
                            (assignment instanceof CodingAssignment ? 10 : 20)));
        } else {
            System.out.printf("%s graded: %.0f/%d. Status: Graded.%n",
                    student.getName(), finalMarks, assignment.getMaxMarks());
        }
    }
}

public class submission {
    public static void main(String[] args) {
        AssignmentStudent asha = new AssignmentStudent("Asha");
        AssignmentStudent ravi = new AssignmentStudent("Ravi");

        Assignment coding = new CodingAssignment(
                "Linked List Lab", 50, LocalDate.of(2026, 3, 10));

        Assignment written = new WrittenAssignment(
                "Design Essay", 50, LocalDate.of(2026, 3, 12));

        Submission s1 = new Submission(asha, coding);
        Submission s2 = new Submission(ravi, written);

        s1.submit(LocalDate.of(2026, 3, 10));
        s2.submit(LocalDate.of(2026, 3, 14));

        s1.grade(45);
        s2.grade(40);

        s1.submit(LocalDate.of(2026, 3, 15));
    }
}