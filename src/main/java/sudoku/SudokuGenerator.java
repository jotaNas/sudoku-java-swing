package sudoku;

import java.util.*;

public class SudokuGenerator {
    private static final int SIZE = 9;
    private static final int BOX = 3;
    private int[][] board = new int[SIZE][SIZE];
    private Random rand = new Random();

    public int[][] generate(String difficulty) {
        fillBoard();
        int[][] solution = copy(board);
        removeCells(difficulty);
        return board;
    }

    public int[][] getSolution() {
        return copy(board);
    }

    private void fillBoard() {
        solve(0, 0);
    }

    private boolean solve(int row, int col) {
        if (row == SIZE) return true;
        int nextRow = col == SIZE - 1 ? row + 1 : row;
        int nextCol = col == SIZE - 1 ? 0 : col + 1;

        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= SIZE; i++) nums.add(i);
        Collections.shuffle(nums);

        for (int num : nums) {
            if (isValid(num, row, col)) {
                board[row][col] = num;
                if (solve(nextRow, nextCol)) return true;
                board[row][col] = 0;
            }
        }
        return false;
    }

    private boolean isValid(int num, int row, int col) {
        for (int i = 0; i < SIZE; i++)
            if (board[row][i] == num || board[i][col] == num)
                return false;

        int boxRow = (row / BOX) * BOX;
        int boxCol = (col / BOX) * BOX;
        for (int i = 0; i < BOX; i++)
            for (int j = 0; j < BOX; j++)
                if (board[boxRow + i][boxCol + j] == num)
                    return false;

        return true;
    }

    private void removeCells(String difficulty) {
        int toKeep = switch (difficulty.toLowerCase()) {
            case "facil" -> 40;
            case "medio" -> 32;
            case "dificil" -> 28;
            default -> 36;
        };

        int cellsToRemove = SIZE * SIZE - toKeep;

        while (cellsToRemove > 0) {
            int i = rand.nextInt(SIZE);
            int j = rand.nextInt(SIZE);
            if (board[i][j] != 0) {
                board[i][j] = 0;
                cellsToRemove--;
            }
        }
    }

    private int[][] copy(int[][] original) {
        int[][] copy = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++)
            System.arraycopy(original[i], 0, copy[i], 0, SIZE);
        return copy;
    }
}

