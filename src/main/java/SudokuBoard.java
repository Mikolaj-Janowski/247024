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

    public boolean isBoardValid() {
        for (int i = 0; i < 9; i++) {
            if (!isRowValid(i) || !isColumnValid(i) || !isBoxValid(i)) {
                return false;
            }
        }
        return true;
    }

    private boolean isRowValid(int row) {
        boolean[] used = new boolean[10];
        for (int i = 0; i < 9; i++) {
            int num = board[row][i];
            if (num != 0 && used[num]) {
                return false;
            }
            used[num] = true;
        }
        return true;
    }

    private boolean isColumnValid(int col) {
        boolean[] used = new boolean[10];
        for (int i = 0; i < 9; i++) {
            int num = board[i][col];
            if (num != 0 && used[num]) {
                return false;
            }
            used[num] = true;
        }
        return true;
    }

    private boolean isBoxValid(int box) {
        int startRow = (box / 3) * 3;
        int startCol = (box % 3) * 3;
        boolean[] used = new boolean[10];
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                int num = board[i][j];
                if (num != 0 && used[num]) {
                    return false;
                }
                used[num] = true;
            }
        }
        return true;
    }
}