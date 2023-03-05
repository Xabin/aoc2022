package aoc22;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day11b {
    private static String filename = "./src/aoc22/input11.txt";
    // private static String filename = "./src/aoc22/example11.txt";

    private final List<Monkey> monkeys = new ArrayList<>();

    private long combinedDivide;

    public Day11b(List<String> input) {
        parse(input);

        for (int i = 0; i < 10_000; i++) {
            performRound();
        }

        monkeys.stream().forEach(System.out::println);

        System.out.println("Monkey business detected: " + determineMonkeyBusiness());
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

        combinedDivide = monkeys.stream().map(Monkey::divideBy).reduce(1, Math::multiplyExact);
    }

    private int parseDivideBy(String divideByLine) {
        return Integer.parseInt(divideByLine.substring(divideByLine.indexOf("by ") + 3));
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

    private long toValue(String valueOrOld, long oldWorryLevel) {
        return switch (valueOrOld) {
        case "old" -> oldWorryLevel;
        default -> Integer.parseInt(valueOrOld);
        };
    }

    private String[] parseItems(String itemsLine) {
        return itemsLine.substring(itemsLine.indexOf(':') + 2).split(",");
    }

    private void performRound() {
        for (Monkey monkey : monkeys) {
            for (Item item : monkey.items) {
                long worryLevelToReduce = monkey.operation.perform(item.worryLevel);
                item.worryLevel = worryLevelToReduce % combinedDivide;
                throwTo(monkey.determineNextMonkey(item.worryLevel), item.worryLevel);
                monkey.inspect();
            }

            monkey.items.clear();
        }
    }

    private void throwTo(int choice, long worryLevel) {
        monkeys.get(choice).items.add(new Item(worryLevel));
    }

    private long determineMonkeyBusiness() {
        monkeys.sort((one, two) -> Long.compare(two.inspections.amount, one.inspections.amount));
        return monkeys.get(0).inspections.amount * monkeys.get(1).inspections.amount;
    }

    public static void main(String[] args) throws IOException {
        new Day11b(Files.readAllLines(Path.of(filename)));
    }

    private record Monkey(List<Item> items, Inspection inspections, int divideBy, Operation operation, int firstChoice,
            int secondChoice) {
        public Monkey(int divideBy, Operation operation, int firstChoice, int secondChoice) {
            this(new ArrayList<>(), new Inspection(), divideBy, operation, firstChoice, secondChoice);
        }

        public int determineNextMonkey(long worryLevel) {
            if (worryLevel % divideBy == 0) {
                return firstChoice;
            }

            return secondChoice;
        }

        public void inspect() {
            inspections.inspect();
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

    private static class Inspection {
        private long amount;

        public void inspect() {
            amount++;
        }

        @Override
        public String toString() {
            return amount + "";
        }
    }

    @FunctionalInterface
    private interface Operation {
        long perform(long worryLevel);
    }
}
