import java.util.Arrays;
import java.util.Random;

public class TypeSudokuSolver implements SudokuSolver {
    @Override
    public void solve(SudokuBoard board) {
        fillBoard(board, 0);
    }

    private boolean fillBoard(SudokuBoard board, int index) {
        if (index == 81) {
            return true;
        }

        int row = index / 9;
        int col = index % 9;

        if (board.get(row, col) != 0) {
            return fillBoard(board, index + 1);
        }

        boolean[] used = new boolean[10];
        calculateUsed(board, used, row, col);

        Random random = new Random();
        int[] possibleValues = new int[9];
        int count = 0;
        for (int i = 1; i <= 9; i++) {
            if (!used[i]) {
                possibleValues[count++] = i;
            }
        }

        for (int i = 0; i < count; i++) {
            int randomIndex = random.nextInt(count);
            int temp = possibleValues[i];
            possibleValues[i] = possibleValues[randomIndex];
            possibleValues[randomIndex] = temp;
        }

        for (int i = 0; i < count; i++) {
            board.set(row, col, possibleValues[i]);
            if (fillBoard(board, index + 1)) {
                return true;
            }
        }

        board.set(row, col, 0);
        return false;
    }

    private void calculateUsed(SudokuBoard board, boolean[] used, int row, int col) {
        Arrays.fill(used, false);

        // Check row
        for (int i = 0; i < 9; i++) {
            if (board.get(row, i) != 0) {
                used[board.get(row, i)] = true;
            }
        }

        // Check column
        for (int i = 0; i < 9; i++) {
            if (board.get(i, col) != 0) {
                used[board.get(i, col)] = true;
            }
        }

        // Check box
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board.get(i, j) != 0) {
                    used[board.get(i, j)] = true;
                }
            }
        }
    }
}