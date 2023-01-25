package aoc22;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day4a {
	private static String filename = "./src/aoc22/input4.txt";
	// private static String filename = "./src/aoc22/example4.txt";

	private List<Integer> firstStarts = new ArrayList<>();
	private List<Integer> firstEnds = new ArrayList<>();
	private List<Integer> secondStarts = new ArrayList<>();
	private List<Integer> secondEnds = new ArrayList<>();

	public Day4a(List<String> input) {
		parse(input);
		System.out
				.println("Number of assignments contained within other assignment: " + calculateDuplicateAssignments());
	}

	private void parse(List<String> input) {
		for (String line : input) {
			int separatorIndex = line.indexOf(',');
			String firstAssignment = line.substring(0, separatorIndex);
			String secondAssignment = line.substring(separatorIndex + 1);

			firstStarts.add(Integer.parseInt(firstAssignment.split("-")[0]));
			firstEnds.add(Integer.parseInt(firstAssignment.split("-")[1]));
			secondStarts.add(Integer.parseInt(secondAssignment.split("-")[0]));
			secondEnds.add(Integer.parseInt(secondAssignment.split("-")[1]));
		}
	}

	private int calculateDuplicateAssignments() {
		int duplicateAssignments = 0;

		for (int i = 0; i < firstStarts.size(); i++) {
			int firstStart = firstStarts.get(i);
			int firstEnd = firstEnds.get(i);
			int secondStart = secondStarts.get(i);
			int secondEnd = secondEnds.get(i);

			if ((firstStart >= secondStart && firstEnd <= secondEnd)
					|| (secondStart >= firstStart && secondEnd <= firstEnd)) {
				duplicateAssignments++;
			}
		}

		return duplicateAssignments;
	}

	public static void main(String[] args) throws IOException {
		new Day4a(Files.readAllLines(Path.of(filename)));
	}
}
