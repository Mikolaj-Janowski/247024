public class SudokuBoard {
    private int[][] board;
    private SudokuSolver sudokuSolver;

    public SudokuBoard(SudokuSolver sudokuSolver) {
        board = new int[9][9];
        this.sudokuSolver = sudokuSolver;
    }

    public int get(int x, int y) {
        return board[x][y];
    }

    public void set(int x, int y, int value) {
        board[x][y] = value;
    }

    public void solveGame() {
        sudokuSolver.solve(this);
    }

    // Additional method to check if digits arrangement on the board is correct
    public boolean isBoardValid() {
        // Implement validation logic here
        return true; // Placeholder
    }
}