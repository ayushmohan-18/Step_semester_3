package main.java.inheritance.class_problems;

public class Problem5 {

    static boolean isValidRenewalCode(String code) {

        if (code == null || code.length() != 4) {
            return false;
        }

        return code.charAt(0) == 'R'
                && Character.isDigit(code.charAt(1))
                && Character.isDigit(code.charAt(2))
                && Character.isUpperCase(code.charAt(3));
    }

    static String processNightlyAudit(LibraryMember[] members) {

        int processed = 0;
        int nullSkipped = 0;
        int faculty = 0;
        int regular = 0;

        for (LibraryMember member : members) {

            if (member == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (member instanceof FacultyMember) {
                faculty++;
            } else {
                regular++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + faculty + " faculty | "
                + regular + " regular";
    }
}

class LibraryMember {

    private static int membersEnrolled = 0;

    private final String memberNumber;

    protected int borrowLimit;
    protected int booksBorrowed;

    private String[] genres;
    private int genreCount;

    public LibraryMember(int borrowLimit) {

        if (borrowLimit <= 0) {
            throw new IllegalArgumentException("Invalid borrow limit");
        }

        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;

        membersEnrolled++;

        this.memberNumber = "LIB-" + (100 + membersEnrolled);

        genres = new String[10];
        genreCount = 0;
    }

    void borrowBook() {

        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    void borrowBook(String genre) {

        if (genreCount < genres.length) {
            genres[genreCount] = genre;
            genreCount++;
        }

        borrowBook();
    }

    int getBooksBorrowed() {
        return booksBorrowed;
    }

    static int getMembersEnrolled() {
        return membersEnrolled;
    }

    String getMemberNumber() {
        return memberNumber;
    }
}

class FacultyMember extends LibraryMember {

    private String department;

    public FacultyMember(
            int borrowLimit,
            String department) {

        super(borrowLimit);
        this.department = department;
    }

    String getDepartment() {
        return department;
    }
}