package aoc22;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day3b {
	private static String filename = "./src/aoc22/input3.txt";
	// private static String filename = "./src/aoc22/example3.txt";

	public Day3b(List<String> input) {
		System.out.println("prioritySum: " + parse(input));
	}

	private int parse(List<String> input) {
		int prioritySum = 0;

		for (int i = 0; i < input.size(); i += 3) {
			for (char item : input.get(i).toCharArray()) {
				if (input.get(i + 1).contains(item + "") && input.get(i + 2).contains(item + "")) {
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
		new Day3b(Files.readAllLines(Path.of(filename)));
	}
}
