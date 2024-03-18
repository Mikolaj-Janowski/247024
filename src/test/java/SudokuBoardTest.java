import static org.junit.Assert.*;
import org.junit.Test;

public class SudokuBoardTest {

    @Test
    public void testSolveGame() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();

        assertTrue(checkRows(board));
        assertTrue(checkColumns(board));
        assertTrue(checkBoxes(board));
    }

    @Test
    public void testGetSet() {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.set(0, 0, 5);
        assertEquals(5, board.get(0, 0));
    }

    @Test
    public void testIsBoardValid() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();
        assertTrue(board.isBoardValid());
    }

    private boolean checkRows(SudokuBoard board) {
        for (int i = 0; i < 9; i++) {
            if (!checkRow(board, i)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkRow(SudokuBoard board, int row) {
        boolean[] used = new boolean[10];
        for (int i = 0; i < 9; i++) {
            int num = board.get(row, i);
            if (num != 0 && used[num]) {
                return false;
            }
            used[num] = true;
        }
        return true;
    }

    private boolean checkColumns(SudokuBoard board) {
        for (int i = 0; i < 9; i++) {
            if (!checkColumn(board, i)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColumn(SudokuBoard board, int col) {
        boolean[] used = new boolean[10];
        for (int i = 0; i < 9; i++) {
            int num = board.get(i, col);
            if (num != 0 && used[num]) {
                return false;
            }
            used[num] = true;
        }
        return true;
    }

    private boolean checkBoxes(SudokuBoard board) {
        for (int startRow = 0; startRow < 9; startRow += 3) {
            for (int startCol = 0; startCol < 9; startCol += 3) {
                if (!checkBox(board, startRow, startCol)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkBox(SudokuBoard board, int startRow, int startCol) {
        boolean[] used = new boolean[10];
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                int num = board.get(i, j);
                if (num != 0 && used[num]) {
                    return false;
                }
                used[num] = true;
            }
        }
        return true;
    }
}