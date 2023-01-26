package aoc22;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day10b {
    private static String filename = "./src/aoc22/input10.txt";
    // private static String filename = "./src/aoc22/example10.txt";

    private final List<Integer> registerX = new ArrayList<>();

    public Day10b(List<String> input) {
        parse(input);
        draw();
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

    private void draw() {
        StringBuilder screen = new StringBuilder();

        for (int i = 0; i < registerX.size(); i++) {
            int spritePosition = registerX.get(i) - 1;

            if (i % 40 >= spritePosition && i % 40 <= spritePosition + 2) {
                screen.append("#");
            } else {
                screen.append(".");
            }

            if ((i + 1) % 40 == 0) {
                screen.append('\n');
            }
        }

        System.out.println(screen);
    }

    public static void main(String[] args) throws IOException {
        new Day10b(Files.readAllLines(Path.of(filename)));
    }
}
