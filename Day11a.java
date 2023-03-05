package aoc22;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day11a {
    private static String filename = "./src/aoc22/input11.txt";
    // private static String filename = "./src/aoc22/example11.txt";

    private final List<Monkey> monkeys = new ArrayList<>();

    public Day11a(List<String> input) {
        parse(input);

        for (int i = 0; i < 20; i++) {
            performRound();
        }

        monkeys.stream().forEach(System.out::println);

        System.out.println("Monkey business detected: " + determineMonkeyBusiness());
    }

    private int determineMonkeyBusiness() {
        monkeys.sort((one, two) -> two.inspections - one.inspections);
        return monkeys.get(0).inspections * monkeys.get(1).inspections;
    }

    private void performRound() {
        for (Monkey monkey : monkeys) {
            for (Item item : monkey.items) {
                item.worryLevel = monkey.operation.perform(item.worryLevel);
                item.worryLevel = monkeyBored(item.worryLevel);

                if (monkey.divisible(item.worryLevel)) {
                    throwTo(monkey.firstChoice, item.worryLevel);
                } else {
                    throwTo(monkey.secondChoice, item.worryLevel);
                }

                monkey.inspections++;
            }

            monkey.items.clear();
        }
    }

    private void throwTo(int choice, long worryLevel) {
        monkeys.get(choice).items.add(new Item(worryLevel));
    }

    private long monkeyBored(long worryLevel) {
        return worryLevel / 3;
    }

    private void parse(List<String> input) {
        for (int i = 0; i < input.size(); i += 7) {
            Monkey monkey = new Monkey(parseDivideBy(input.get(i + 3)), parseOperation(input.get(i + 2)),
                    parseChoice(input.get(i + 4)), parseChoice(input.get(i + 5)));

            for (String item : parseItems(input.get(i + 1))) {
                monkey.items.add(new Item(Integer.parseInt(item.trim())));
            }

            monkeys.add(monkey);
        }
    }

    private int parseChoice(String choiceLine) {
        return Integer.parseInt(choiceLine.charAt(choiceLine.length() - 1) + "");
    }

    private Operation parseOperation(String operationLine) {
        String[] operations = operationLine.substring(operationLine.indexOf('=') + 2).split(" ");

        return switch (operations[1]) {
        case "*" -> worryLevel -> worryLevel * toValue(operations[2], worryLevel);
        case "+" -> worryLevel -> worryLevel + toValue(operations[2], worryLevel);
        default -> throw new IllegalArgumentException("Unexpected value: " + operations[1]);
        };
    }

    private long toValue(String valueOrOld, long worryLevel) {
        return switch (valueOrOld) {
        case "old" -> worryLevel;
        default -> Integer.parseInt(valueOrOld);
        };
    }

    private int parseDivideBy(String divideByLine) {
        return Integer.parseInt(divideByLine.substring(divideByLine.indexOf("by ") + 3));
    }

    private String[] parseItems(String itemsLine) {
        return itemsLine.substring(itemsLine.indexOf(':') + 2).split(",");
    }

    public static void main(String[] args) throws IOException {
        new Day11a(Files.readAllLines(Path.of(filename)));
    }

    private class Monkey {
        private final List<Item> items = new ArrayList<>();
        private final int divideBy;
        private final Operation operation;
        private final int firstChoice;
        private final int secondChoice;

        private int inspections;

        public Monkey(int divideBy, Operation operation, int firstChoice, int secondChoice) {
            this.divideBy = divideBy;
            this.operation = operation;
            this.firstChoice = firstChoice;
            this.secondChoice = secondChoice;
        }

        public boolean divisible(long worryLevel) {
            return worryLevel % divideBy == 0;
        }

        @Override
        public String toString() {
            return "Monkey [items=" + items + ", divideBy=" + divideBy + ", firstChoice=" + firstChoice
                    + ", secondChoice=" + secondChoice + ", inspections=" + inspections + "]";
        }
    }

    private class Item {
        private long worryLevel;

        public Item(long worryLevel) {
            this.worryLevel = worryLevel;
        }

        @Override
        public String toString() {
            return worryLevel + "";
        }
    }

    @FunctionalInterface
    private interface Operation {
        long perform(long worryLevel);
    }
}
