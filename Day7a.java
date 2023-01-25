package aoc22;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day7a {
	private static String filename = "./src/aoc22/input7.txt";
	// private static String filename = "./src/aoc22/example7.txt";
	private static long sum = 0;

	public Day7a(List<String> input) {
		Directory root = parse(input);
		root.size();

		System.out.println("Sum of all directories below size 100 000: " + sum);
	}

	private Directory parse(List<String> input) {
		Directory currentDir = new Directory("root", null);
		Directory root = null;

		for (String line : input) {
			if (line.equals("$ cd /")) {
				root = currentDir;
			} else if (line.startsWith("dir")) {
				currentDir.directories.add(new Directory(line.substring(4), currentDir));
			} else if (isNumber(line)) {
				currentDir.files.add(toFile(line));
			} else if (line.endsWith("..")) {
				currentDir = currentDir.parent;
			} else if (line.startsWith("$ cd")) {
				currentDir = currentDir.cd(line.split(" ")[2]);
			}
		}

		return root;
	}

	private boolean isNumber(String line) {
		try {
			Long.parseLong(line.split(" ")[0]);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

	private File toFile(String line) {
		return new File(line.split(" ")[1], Long.parseLong(line.split(" ")[0]));
	}

	public static void main(String[] args) throws IOException {
		new Day7a(Files.readAllLines(Path.of(filename)));
	}

	private record File(String name, long size) {

	}

	private record Directory(String name, Directory parent, List<File> files, List<Directory> directories) {
		public Directory(String name, Directory parent) {
			this(name, parent, new ArrayList<>(), new ArrayList<>());
		}

		public Directory cd(String name) {
			return directories.stream().filter(dir -> dir.name.equals(name)).findFirst().get();
		}

		public long size() {
			long size = files.stream().collect(Collectors.summingLong(File::size))
					+ directories.stream().collect(Collectors.summingLong(Directory::size));

			if (size < 100_000) {
				sum += size;
			}

			return size;
		}

		@Override
		public String toString() {
			return "Directory [name=" + name + ", files=" + files + ", directories=" + directories + "]";
		}
	}
}
