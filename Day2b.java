package aoc22;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day2b {
    private static String filename = "./src/aoc22/input2.txt";
    // private static String filename = "./src/aoc22/example2.txt";

    public Day2b(List<String> input) {
        System.out.println("Score: " + parse(input));
    }

    private int parse(List<String> input) {
        int score = 0;

        for (String line : input) {
            score += scoreForOutcome(toOutcome(line.charAt(2)));
            score += scoreForType(toType(line.charAt(0)), toOutcome(line.charAt(2)));
        }

        return score;
    }

    private Type toType(char typeAsChar) {
        return switch (typeAsChar) {
        case 'A' -> Type.ROCK;
        case 'B' -> Type.PAPER;
        case 'C' -> Type.SCISSORS;
        default -> throw new IllegalArgumentException("Unexpected value: " + typeAsChar);
        };
    }

    private Outcome toOutcome(char outcomeAsChar) {
        return switch (outcomeAsChar) {
        case 'X' -> Outcome.LOSE;
        case 'Y' -> Outcome.DRAW;
        case 'Z' -> Outcome.WIN;
        default -> throw new IllegalArgumentException("Unexpected value: " + outcomeAsChar);
        };
    }

    private int scoreForOutcome(Outcome outcome) {
        return switch (outcome) {
        case WIN -> 6;
        case DRAW -> 3;
        case LOSE -> 0;
        };
    }

    private int scoreForType(Type opponent, Outcome outcome) {
        if (outcome == Outcome.DRAW) {
            return switch (opponent) {
            case ROCK -> 1;
            case PAPER -> 2;
            case SCISSORS -> 3;
            };
        }

        if (outcome == Outcome.WIN) {
            return switch (opponent) {
            case ROCK -> 2;
            case PAPER -> 3;
            case SCISSORS -> 1;
            };
        }

        return switch (opponent) {
        case ROCK -> 3;
        case PAPER -> 1;
        case SCISSORS -> 2;
        };
    }

    public static void main(String[] args) throws IOException {
        new Day2b(Files.readAllLines(Path.of(filename)));
    }

    private enum Type {
        ROCK, PAPER, SCISSORS
    }

    private enum Outcome {
        WIN, DRAW, LOSE
    }
}
