package aoc22;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class Day5a {
    private static String filename = "./src/aoc22/input5.txt";
    // private static String filename = "./src/aoc22/example5.txt";

    private Stack<String> stack1 = new Stack<>();
    private Stack<String> stack2 = new Stack<>();
    private Stack<String> stack3 = new Stack<>();
    private Stack<String> stack4 = new Stack<>();
    private Stack<String> stack5 = new Stack<>();
    private Stack<String> stack6 = new Stack<>();
    private Stack<String> stack7 = new Stack<>();
    private Stack<String> stack8 = new Stack<>();
    private Stack<String> stack9 = new Stack<>();

    public Day5a(List<String> input) {
        // example();
        real();

        parse(input);

        System.out.println("Top boxes: " + peek(stack1) + peek(stack2) + peek(stack3) + peek(stack4) + peek(stack5)
                + peek(stack6) + peek(stack7) + peek(stack8) + peek(stack9));
    }

    private void example() {
        stack1.push("Z");
        stack1.push("N");
        stack2.push("M");
        stack2.push("C");
        stack2.push("D");
        stack3.push("P");
    }

    private void real() {
        stack1.push("W");
        stack1.push("B");
        stack1.push("D");
        stack1.push("N");
        stack1.push("C");
        stack1.push("F");
        stack1.push("J");

        stack2.push("P");
        stack2.push("Z");
        stack2.push("V");
        stack2.push("Q");
        stack2.push("L");
        stack2.push("S");
        stack2.push("T");

        stack3.push("P");
        stack3.push("Z");
        stack3.push("B");
        stack3.push("G");
        stack3.push("J");
        stack3.push("T");

        stack4.push("D");
        stack4.push("T");
        stack4.push("L");
        stack4.push("J");
        stack4.push("Z");
        stack4.push("B");
        stack4.push("H");
        stack4.push("C");

        stack5.push("G");
        stack5.push("V");
        stack5.push("B");
        stack5.push("J");
        stack5.push("S");

        stack6.push("P");
        stack6.push("S");
        stack6.push("Q");

        stack7.push("B");
        stack7.push("V");
        stack7.push("D");
        stack7.push("F");
        stack7.push("L");
        stack7.push("M");
        stack7.push("P");
        stack7.push("N");

        stack8.push("P");
        stack8.push("S");
        stack8.push("M");
        stack8.push("F");
        stack8.push("B");
        stack8.push("D");
        stack8.push("L");
        stack8.push("R");

        stack9.push("V");
        stack9.push("D");
        stack9.push("T");
        stack9.push("R");
    }

    private String peek(Stack<String> stack) {
        try {
            return stack.peek();
        } catch (EmptyStackException e) {
            return "";
        }
    }

    private void parse(List<String> input) {
        for (String line : input) {
            int amount = readAmount(line);
            int fromStackNumber = readFromStack(amount, line);
            int toStackNumber = readToStack(amount, line);
            Stack<String> fromStack = toStack(fromStackNumber);
            Stack<String> toStack = toStack(toStackNumber);

            for (int i = 0; i < amount; i++) {
                toStack.push(fromStack.pop());
            }
        }
    }

    private int readAmount(String line) {
        if (line.charAt(6) == ' ') {
            return Integer.parseInt("" + line.charAt(5));
        }

        return Integer.parseInt("" + line.charAt(5) + line.charAt(6));
    }

    private int readFromStack(int amount, String line) {
        if (amount >= 10) {
            return Integer.parseInt("" + line.charAt(13));
        }

        return Integer.parseInt("" + line.charAt(12));
    }

    private int readToStack(int amount, String line) {
        if (amount >= 10) {
            return Integer.parseInt("" + line.charAt(18));
        }

        return Integer.parseInt("" + line.charAt(17));
    }

    private Stack<String> toStack(int stackNumber) {
        return switch (stackNumber) {
        case 1 -> stack1;
        case 2 -> stack2;
        case 3 -> stack3;
        case 4 -> stack4;
        case 5 -> stack5;
        case 6 -> stack6;
        case 7 -> stack7;
        case 8 -> stack8;
        case 9 -> stack9;
        default -> throw new IllegalArgumentException("Unexpected value: " + stackNumber);
        };
    }

    public static void main(String[] args) throws IOException {
        new Day5a(Files.readAllLines(Path.of(filename)));
    }
}
