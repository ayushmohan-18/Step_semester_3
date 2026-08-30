package main.java.strings.assignment_problems;

import java.util.HashMap;
import java.util.Map;

public class Problem5 {

    static void printFilteredWordFrequency(String feedback) {

        String cleaned = feedback.toLowerCase()
                                 .replace(".", "")
                                 .replace(",", "");

        String[] words = cleaned.split("\\s+");

        String[] stopWords = {
            "the", "was", "and", "a", "is", "of", "in"
        };

        HashMap<String, Integer> frequency = new HashMap<>();

        for (int i = 0; i < words.length; i++) {

            boolean isStopWord = false;

            for (int j = 0; j < stopWords.length; j++) {
                if (words[i].equals(stopWords[j])) {
                    isStopWord = true;
                    break;
                }
            }

            if (!isStopWord) {
                frequency.put(
                    words[i],
                    frequency.getOrDefault(words[i], 0) + 1
                );
            }
        }

        frequency.entrySet()
                 .stream()
                 .sorted(
                     (a, b) -> b.getValue()
                                .compareTo(a.getValue())
                 )
                 .forEach(
                     entry -> System.out.println(
                         entry.getKey() + ": " + entry.getValue()
                     )
                 );
    }

    public static void main(String[] args) {

        String feedback =
            "The mentor was great, the session was great and clear.";

        printFilteredWordFrequency(feedback);
    }
}