package aoc22;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day1a {
    // private static String filename = "./src/aoc22/input1.txt";
    private static String filename = "./src/aoc22/input1.txt";
    private List<Elf> elves = new ArrayList<>();

    public Day1a(List<String> input) {
        parse(input);

        System.out
                .println("Max calories: " + elves.stream().max(Comparator.comparing(Elf::sum)).map(Elf::sum).orElse(0));
    }

    private void parse(List<String> input) {
        int index = 1;
        Elf elf = new Elf(index++);
        elves.add(elf);

        for (String line : input) {
            if (line.isEmpty()) {
                elf = new Elf(index++);
                elves.add(elf);
            } else {
                elf.food.add(Integer.parseInt(line));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Day1a(Files.readAllLines(Path.of(filename)));
    }

    private record Elf(int index, List<Integer> food) {
        public Elf(int index) {
            this(index, new ArrayList<>());
        }

        public int sum() {
            return food.stream().collect(Collectors.summingInt(Integer::intValue));
        }
    }
}
