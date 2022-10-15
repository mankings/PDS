public class Controller implements JGaloInterface {

    // contar num jogadas
    // array bidimensional

    private boolean turn;
    private int numPlays;
    private char[][] grid;
    private char winner;

    public Controller() {
        this.turn = true;
        InitializeParams();
    }

    public Controller(int start) {
        this.turn = start == 1;
        InitializeParams();
    }

    private void InitializeParams() {
        this.numPlays = 0;
        this.grid = new char[3][3];
        this.winner = ' ';
    }

    @Override
    public char getActualPlayer() {
        return turn? 'X' : 'O';
    }

    @Override
    public boolean setJogada(int lin, int col) {

        if (grid[lin - 1][col - 1] == '\0') {
            grid[lin - 1][col - 1] = (turn) ? 'X' : 'O';
            numPlays++;
            turn = !turn;
            return true;
        }

        return false;
    }

    @Override
    public boolean isFinished() {
        if (numPlays == 9)
            return true;

        for (int i=0; i<3; i++) verify(i, 0, 0, 1); // Linhas
        for (int j=0; j<3; j++) verify(0, j, 1, 0); // Colunas
        verify(0, 0, 1, 1);                      // Diagonal 1
        verify(0, 2, 1, -1);                     // Diagonal 2

        return winner != ' ';
    }

    @Override
    public char checkResult() {
        return winner;
    }

    private void verify(int y, int x, int incy, int incx) {
        if (grid[y][x] == '\0') return;
        for (int i=0, yy=y, xx=x; i<3; i++, yy+=incy, xx+=incx)
            if (grid[y][x] != grid[yy][xx]) return;
        winner = turn? '2' : '1';
    }

}
