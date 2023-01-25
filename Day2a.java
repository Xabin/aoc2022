package aoc22;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day2a {
	private static String filename = "./src/aoc22/input2.txt";
	// private static String filename = "./src/aoc22/example2.txt";

	public Day2a(List<String> input) {
		System.out.println("Score: " + parse(input));
	}

	private int parse(List<String> input) {
		int score = 0;

		for (String line : input) {
			score += scoreForType(toType(line.charAt(2)));
			score += scoreForOutcome(toType(line.charAt(0)), toType(line.charAt(2)));
		}

		return score;
	}

	private Type toType(char typeAsChar) {
		return switch (typeAsChar) {
		case 'A', 'X' -> Type.ROCK;
		case 'B', 'Y' -> Type.PAPER;
		case 'C', 'Z' -> Type.SCISSORS;
		default -> throw new IllegalArgumentException("Unexpected value: " + typeAsChar);
		};
	}

	private int scoreForType(Type type) {
		return switch (type) {
		case ROCK -> 1;
		case PAPER -> 2;
		case SCISSORS -> 3;
		};
	}

	private int scoreForOutcome(Type opponent, Type me) {
		if (opponent == me) {
			return 3;
		}

		if (me == Type.ROCK && opponent == Type.SCISSORS || me == Type.PAPER && opponent == Type.ROCK
				|| me == Type.SCISSORS && opponent == Type.PAPER) {
			return 6;
		}

		return 0;
	}

	public static void main(String[] args) throws IOException {
		new Day2a(Files.readAllLines(Path.of(filename)));
	}

	private enum Type {
		ROCK, PAPER, SCISSORS
	}
}
