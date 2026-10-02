package main.java.object_class_method.assignment_problems;

import java.util.*;

interface NotificationChannel {
    void send(String studentName, String message);
}

class EmailChannel implements NotificationChannel {

    @Override
    public void send(String studentName, String message) {
        System.out.println("[Email → " + studentName + "] " + message);
    }
}

class SmsChannel implements NotificationChannel {

    @Override
    public void send(String studentName, String message) {
        System.out.println("[SMS → " + studentName + "] " + message);
    }
}

class AppChannel implements NotificationChannel {

    @Override
    public void send(String studentName, String message) {
        System.out.println("[App → " + studentName + "] " + message);
    }
}

class Student {

    private String name;
    private String department;
    private List<NotificationChannel> channels;

    public Student(String name, String department) {
        this.name = name;
        this.department = department;
        this.channels = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public void addChannel(NotificationChannel channel) {
        channels.add(channel);
    }

    public List<NotificationChannel> getChannels() {
        return channels;
    }
}

class Notice {

    private String title;
    private Set<String> departments;

    public Notice(String title, Set<String> departments) {
        this.title = title;
        this.departments = new HashSet<>(departments);
    }

    public boolean isValid() {
        return title != null
                && !title.trim().isEmpty()
                && !departments.isEmpty();
    }

    public String getTitle() {
        return title;
    }

    public Set<String> getDepartments() {
        return departments;
    }
}

class NoticeBoard {

    private List<Student> students;

    public NoticeBoard() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void postNotice(Notice notice) {

        if (!notice.isValid()) {
            System.out.println(
                    "Cannot post notice: At least one target department is required."
            );
            return;
        }

        System.out.println("Notice '" + notice.getTitle()
                + "' posted to "
                + String.join(", ", notice.getDepartments()) + ".");

        for (Student student : students) {

            if (notice.getDepartments().contains(
                    student.getDepartment())) {

                for (NotificationChannel channel :
                        student.getChannels()) {

                    channel.send(
                            student.getName(),
                            notice.getTitle()
                    );
                }
            }
        }
    }
}

public class broadcaster {

    public static void main(String[] args) {

        Student asha = new Student("Asha", "CSE");
        Student ravi = new Student("Ravi", "ECE");

        asha.addChannel(new EmailChannel());
        asha.addChannel(new AppChannel());

        ravi.addChannel(new SmsChannel());

        NoticeBoard board = new NoticeBoard();

        board.addStudent(asha);
        board.addStudent(ravi);

        Notice n1 = new Notice(
                "Lab Closed Tomorrow",
                new HashSet<>(Arrays.asList("CSE"))
        );

        board.postNotice(n1);

        Notice n2 = new Notice(
                "Fee Deadline Extended",
                new HashSet<>(Arrays.asList("CSE", "ECE"))
        );

        board.postNotice(n2);

        Notice n3 = new Notice(
                "Sports Day",
                new HashSet<>()
        );

        board.postNotice(n3);
    }
}