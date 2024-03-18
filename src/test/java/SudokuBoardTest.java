import static org.junit.Assert.*;
import org.junit.Test;

public class SudokuBoardTest {


    @Test
    public void testGetSet() {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.set(0, 0, 5);
        assertEquals(5, board.get(0, 0));
    }

    @Test
    public void testSolveGame() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();

        assertTrue(board.isBoardValid());
    }

    @Test
    public void testIsBoardValid() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);

        // Empty board should be valid
        assertTrue(board.isBoardValid());

        // Invalid board with duplicate numbers in row
        board.set(0, 0, 1);
        board.set(0, 1, 1);
        assertFalse(board.isBoardValid());

        // Invalid board with duplicate numbers in column
        board = new SudokuBoard(solver);
        board.set(0, 0, 1);
        board.set(1, 0, 1);
        assertFalse(board.isBoardValid());

        // Invalid board with duplicate numbers in box
        board = new SudokuBoard(solver);
        board.set(0, 0, 1);
        board.set(1, 1, 1);
        assertFalse(board.isBoardValid());

        // Valid board
        board = new SudokuBoard(solver);
        board.set(0, 0, 1);
        board.set(1, 1, 2);
        assertTrue(board.isBoardValid());
    }
}