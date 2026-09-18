package main.java.inheritance.assignment_problems;

public class Problem4 {

    static String batchPrint(LibraryMember[] members) {

        StringBuilder result = new StringBuilder();

        for (LibraryMember member : members) {

            String info = member.displayInfo();

            if (member instanceof StudentMember) {
                StudentMember student = (StudentMember) member;

                result.append(info)
                      .append(" [Course via downcast: ")
                      .append(student.getCourse())
                      .append("] | ");
            } else {
                result.append(info)
                      .append(" | ");
            }
        }

        return result.toString();
    }
}

class LibraryMember {
    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    public LibraryMember(String memberId, int borrowLimit) {
        if (memberId == null || memberId.trim().isEmpty()
                || memberId.length() < 4) {
            throw new IllegalArgumentException("Invalid member ID");
        }

        if (borrowLimit <= 0) {
            throw new IllegalArgumentException("Invalid borrow limit");
        }

        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
    }

    void borrowBook() {
        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    int getBooksBorrowed() {
        return booksBorrowed;
    }

    String displayInfo() {
        return "General | Books: " + booksBorrowed;
    }
}

class StudentMember extends LibraryMember {
    private String course;

    public StudentMember(
            String memberId,
            int borrowLimit,
            String course) {

        super(memberId, borrowLimit);
        this.course = course;
    }

    String getCourse() {
        return course;
    }

    @Override
    String displayInfo() {
        return "Student | Course: " + course
                + " | Books: " + booksBorrowed;
    }
}