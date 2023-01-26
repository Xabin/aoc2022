package aoc22;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day10a {
    private static String filename = "./src/aoc22/input10.txt";
    // private static String filename = "./src/aoc22/example10.txt";

    private final List<Integer> registerX = new ArrayList<>();

    public Day10a(List<String> input) {
        parse(input);

        System.out.println(
                "Signal strength: " + (20 * registerX.get(19) + 60 * registerX.get(59) + 100 * registerX.get(99)
                        + 140 * registerX.get(139) + 180 * registerX.get(179) + 220 * registerX.get(219)));
    }

    private void parse(List<String> input) {
        int x = 1;
        registerX.add(x);

        for (String line : input) {
            registerX.add(x);

            if (line.startsWith("addx")) {
                x += Integer.parseInt(line.split(" ")[1]);
                registerX.add(x);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Day10a(Files.readAllLines(Path.of(filename)));
    }
}
