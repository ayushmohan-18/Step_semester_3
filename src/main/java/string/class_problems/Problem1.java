package main.java.string.class_problems;

import java.util.Random;

public class Problem1 {

    static String playRound(String playerMove, String computerMove) {

        if (playerMove.equalsIgnoreCase(computerMove)) {
            return "Draw";
        }

        if ((playerMove.equalsIgnoreCase("Rock") &&
             computerMove.equalsIgnoreCase("Scissors")) ||
            (playerMove.equalsIgnoreCase("Paper") &&
             computerMove.equalsIgnoreCase("Rock")) ||
            (playerMove.equalsIgnoreCase("Scissors") &&
             computerMove.equalsIgnoreCase("Paper"))) {

            return "Player Wins";
        }

        return "Computer Wins";
    }

    public static void main(String[] args) {

        String[] playerMoves = {
            "Rock", "Paper", "Scissors", "Rock", "Paper"
        };

        String[] moves = {
            "Rock", "Paper", "Scissors"
        };

        Random random = new Random();

        int wins = 0;
        int losses = 0;
        int draws = 0;

        System.out.println("Round | Player Move | Computer Move | Result");

        for (int i = 0; i < playerMoves.length; i++) {

            String computerMove =
                moves[random.nextInt(3)];

            String result =
                playRound(playerMoves[i], computerMove);

            System.out.println(
                (i + 1) + " | " +
                playerMoves[i] + " | " +
                computerMove + " | " +
                result
            );

            if (result.equals("Player Wins")) {
                wins++;
            } else if (result.equals("Computer Wins")) {
                losses++;
            } else {
                draws++;
            }
        }

        double winPercentage =
            (double) wins / playerMoves.length * 100;

        System.out.println("\nFinal Summary");
        System.out.println("Wins: " + wins);
        System.out.println("Losses: " + losses);
        System.out.println("Draws: " + draws);
        System.out.printf("Win Percentage: %.1f%%%n",
                          winPercentage);
    }
}