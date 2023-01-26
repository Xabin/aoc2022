package aoc22;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Day8a {
    private static String filename = "./src/aoc22/input8.txt";
    // private static String filename = "./src/aoc22/example8.txt";

    private final Tree[][] treeGrid;

    public Day8a(List<String> input) {
        treeGrid = new Tree[input.get(0).length()][input.size()];

        parse(input);
        determineVisibility();

        System.out.println("Number of visible trees: " + countVisibleTrees());
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
                if (treeAtEdge(row, column) || visibileFromTop(row, column) || visibileFromBottom(row, column)
                        || visibileFromLeft(row, column) || visibileFromRight(row, column)) {
                    treeGrid[row][column].visible = true;
                }
            }
        }
    }

    private boolean treeAtEdge(int row, int column) {
        return row == 0 || column == 0 || row == treeGrid[0].length || column == treeGrid.length;
    }

    private boolean visibileFromTop(int row, int column) {
        for (int i = 0; i < row; i++) {
            if (treeGrid[row][column].height <= treeGrid[i][column].height) {
                return false;
            }
        }

        return true;
    }

    private boolean visibileFromBottom(int row, int column) {
        for (int i = treeGrid.length - 1; i > row; i--) {
            if (treeGrid[row][column].height <= treeGrid[i][column].height) {
                return false;
            }
        }

        return true;
    }

    private boolean visibileFromLeft(int row, int column) {
        for (int i = 0; i < column; i++) {
            if (treeGrid[row][column].height <= treeGrid[row][i].height) {
                return false;
            }
        }

        return true;
    }

    private boolean visibileFromRight(int row, int column) {
        for (int i = treeGrid[0].length - 1; i > column; i--) {
            if (treeGrid[row][column].height <= treeGrid[row][i].height) {
                return false;
            }
        }

        return true;
    }

    private long countVisibleTrees() {
        return Arrays.stream(treeGrid).flatMap(Arrays::stream).filter(Tree::visible).count();
    }

    public static void main(String[] args) throws IOException {
        new Day8a(Files.readAllLines(Path.of(filename)));
    }

    private class Tree {
        private final int height;
        private boolean visible;

        public Tree(int height) {
            this.height = height;
        }

        public boolean visible() {
            return visible;
        }

        @Override
        public String toString() {
            return "Tree [height=" + height + ", visible=" + visible + "]";
        }
    }
}
