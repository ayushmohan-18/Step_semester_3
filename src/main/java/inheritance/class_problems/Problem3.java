package main.java.inheritance.assignment_problems;

public class Problem3 {
}

class LibraryMember {
    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    private int[] fineHistory;
    private int fineCount;
    private int totalFine;

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

        fineHistory = new int[10];
        fineCount = 0;
        totalFine = 0;
    }

    void borrowBook() {
        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    int getBooksBorrowed() {
        return booksBorrowed;
    }

    protected void chargeFine(int amount) {
        if (fineCount < fineHistory.length) {
            fineHistory[fineCount] = amount;
            fineCount++;
        }

        totalFine += amount;
    }

    int[] getFineHistory() {
        int[] result = new int[fineCount];

        for (int i = 0; i < fineCount; i++) {
            result[i] = fineHistory[i];
        }

        return result;
    }

    int getTotalFine() {
        return totalFine;
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

    public String getCourse() {
        return course;
    }

    @Override
    protected void chargeFine(int amount) {
        super.chargeFine(amount / 2);
    }
}