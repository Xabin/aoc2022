package aoc22;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day1b {
    // private static String filename = "./src/aoc22/input1.txt";
    private static String filename = "./src/aoc22/input1.txt";
    private List<Elf> elves = new ArrayList<>();

    public Day1b(List<String> input) {
        parse(input);

        Collections.sort(elves, Comparator.comparing(Elf::sum));
        Collections.reverse(elves);
        int topThree = 0;

        for (int i = 0; i < 3; i++) {
            topThree += elves.get(i).sum();
        }

        System.out.println("Top 3 calories: " + topThree);
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
        new Day1b(Files.readAllLines(Path.of(filename)));
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
