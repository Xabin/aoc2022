package aoc22;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day8b {
    private static String filename = "./src/aoc22/input8.txt";
    // private static String filename = "./src/aoc22/example8.txt";

    private final Tree[][] treeGrid;
    private int highestScenicScore;

    public Day8b(List<String> input) {
        treeGrid = new Tree[input.get(0).length()][input.size()];

        parse(input);
        determineVisibility();

        System.out.println("Highest scenic score: " + highestScenicScore);
    }

    private void parse(List<String> input) {
        for (int i = 0; i < input.get(0).length(); i++) {
            for (int j = 0; j < input.size(); j++) {
                treeGrid[i][j] = new Tree(Integer.parseInt(input.get(i).charAt(j) + ""));
            }
        }
    }

    private void determineVisibility() {
        for (int row = 0; row < treeGrid[0].length; row++) {
            for (int column = 0; column < treeGrid.length; column++) {
                if (treeAtEdge(row, column)) {
                    continue;
                }

                int scenicScore = visibileDown(row, column);
                scenicScore *= visibileUp(row, column);
                scenicScore *= visibileRight(row, column);
                scenicScore *= visibileLeft(row, column);

                if (scenicScore > highestScenicScore) {
                    highestScenicScore = scenicScore;
                }
            }
        }

    }

    private boolean treeAtEdge(int row, int column) {
        return row == 0 || column == 0 || row == treeGrid[0].length || column == treeGrid.length;
    }

    private int visibileDown(int row, int column) {
        int scenicScore = 0;

        for (int i = row + 1; i < treeGrid.length; i++) {
            scenicScore++;

            if (treeGrid[row][column].height <= treeGrid[i][column].height) {
                break;
            }
        }

        return scenicScore;
    }

    private int visibileUp(int row, int column) {
        int scenicScore = 0;

        for (int i = row - 1; i >= 0; i--) {
            scenicScore++;

            if (treeGrid[row][column].height <= treeGrid[i][column].height) {
                break;
            }
        }

        return scenicScore;
    }

    private int visibileRight(int row, int column) {
        int scenicScore = 0;

        for (int i = column + 1; i < treeGrid[0].length; i++) {
            scenicScore++;

            if (treeGrid[row][column].height <= treeGrid[row][i].height) {
                break;
            }
        }

        return scenicScore;
    }

    private int visibileLeft(int row, int column) {
        int scenicScore = 0;

        for (int i = column - 1; i >= 0; i--) {
            scenicScore++;

            if (treeGrid[row][column].height <= treeGrid[row][i].height) {
                break;
            }
        }

        return scenicScore;
    }

    public static void main(String[] args) throws IOException {
        new Day8b(Files.readAllLines(Path.of(filename)));
    }

    private record Tree(int height) {

    }
}
