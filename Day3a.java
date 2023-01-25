package aoc22;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day3a {
    private static String filename = "./src/aoc22/input3.txt";
    // private static String filename = "./src/aoc22/example3.txt";

    public Day3a(List<String> input) {
        System.out.println("prioritySum: " + parse(input));
    }

    private int parse(List<String> input) {
        int prioritySum = 0;

        for (String line : input) {
            int length = line.length() / 2;
            String firstCompartment = line.substring(0, length);
            String secondCompartment = line.substring(length);

            for (char item : firstCompartment.toCharArray()) {
                if (secondCompartment.contains(item + "")) {
                    prioritySum += toPriority(item);
                    break;
                }
            }
        }

        return prioritySum;
    }

    private int toPriority(char item) {
        if (Character.isUpperCase(item)) {
            return (int) item - 38;
        }

        return (int) item - 96;
    }

    public static void main(String[] args) throws IOException {
        new Day3a(Files.readAllLines(Path.of(filename)));
    }
}
