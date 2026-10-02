
package main.java.object_class_method.class_problems;

import java.util.*;

abstract class Question {
    private String questionText;
    private int marks;

    public Question(String questionText, int marks) {
        this.questionText = questionText;
        this.marks = marks;
    }

    public String getQuestionText() {
        return questionText;
    }

    public int getMarks() {
        return marks;
    }

    public abstract boolean evaluate(String answer);
}

class MultipleChoiceQuestion extends Question {
    private String correctOption;

    public MultipleChoiceQuestion(String text, int marks,
                                  String correctOption) {
        super(text, marks);
        this.correctOption = correctOption;
    }

    public boolean evaluate(String answer) {
        return correctOption.equalsIgnoreCase(answer);
    }
}

class TrueFalseQuestion extends Question {
    private String correctAnswer;

    public TrueFalseQuestion(String text, int marks,
                             String correctAnswer) {
        super(text, marks);
        this.correctAnswer = correctAnswer;
    }

    public boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class ShortAnswerQuestion extends Question {
    private String correctAnswer;

    public ShortAnswerQuestion(String text, int marks,
                               String correctAnswer) {
        super(text, marks);
        this.correctAnswer = correctAnswer;
    }

    public boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer.trim());
    }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Examination {
    private String title;
    private List<Question> questions = new ArrayList<>();

    public Examination(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public List<Question> getQuestions() {
        return questions;
    }
}

class Attempt {
    private Student student;
    private Examination examination;
    private Map<Question, String> answers = new LinkedHashMap<>();
    private boolean submitted = false;

    public Attempt(Student student, Examination examination) {
        this.student = student;
        this.examination = examination;
        System.out.println(examination.getTitle()
                + " started by " + student.getName());
    }

    public void answer(int questionNumber, String answer) {
        if (submitted) {
            System.out.println(
                    "Cannot change answers for a submitted examination.");
            return;
        }

        List<Question> questions = examination.getQuestions();

        if (questionNumber < 1 || questionNumber > questions.size()) {
            System.out.println("Invalid question number.");
            return;
        }

        Question question = questions.get(questionNumber - 1);
        answers.put(question, answer);

        System.out.println("Answer recorded for Question "
                + questionNumber);
    }

    public void submit() {
        if (submitted) {
            System.out.println("Examination already submitted.");
            return;
        }

        submitted = true;
        int totalScore = 0;
        int maximumScore = 0;
        int number = 1;

        System.out.println(examination.getTitle()
                + " submitted by " + student.getName());

        for (Question question : examination.getQuestions()) {
            maximumScore += question.getMarks();
            String answer = answers.getOrDefault(question, "");

            if (question.evaluate(answer)) {
                totalScore += question.getMarks();
                System.out.println("Question " + number
                        + ": Correct (" + question.getMarks()
                        + " points)");
            } else {
                System.out.println("Question " + number
                        + ": Incorrect (0 points)");
            }

            number++;
        }

        System.out.println("Total score: "
                + totalScore + "/" + maximumScore);
    }
}

public class online_exam {
    public static void main(String[] args) {
        Student student = new Student("Student 1");

        Examination exam = new Examination("Exam A");

        exam.addQuestion(new MultipleChoiceQuestion(
                "Choose the correct option", 5, "C"));

        exam.addQuestion(new TrueFalseQuestion(
                "Java is platform independent", 5, "False"));

        Attempt attempt = new Attempt(student, exam);

        attempt.answer(1, "C");
        attempt.answer(2, "True");

        attempt.submit();

        attempt.answer(1, "A");
    }
}