public class JGaloMethods implements JGaloInterface {
    char player;
    boolean over;
    char result;
    char[][] table = new char[3][3];

    // pode criar com ou sem argumento
    public JGaloMethods() {
        this.player = 'X';
        this.over = false;
        this.result = ' ';
    }

    public JGaloMethods(char player) {
        this.player = player;
        this.over = false;
        this.result = ' ';
    }

    @Override
    public char getActualPlayer() {
        return this.player;
    }
    @Override
    public char checkResult() {
        return this.result;
    }

    @Override
    //makes a play
    public boolean setJogada(int lin, int col) {
        //catching errors
        if (lin > 3 || col > 3 || lin < 1 || col < 1 || !hasPlay(lin - 1, col - 1)) return false;

        //save the play in the table
        table[lin-1][col-1] = this.player;

        //switching player for the next turn
        if (this.player == 'X') this.player = 'O';
        else if (this.player == 'O') this.player = 'X';

        return true;
    }
    //checks is there's already a play in the given square
    private boolean hasPlay(int lin, int col) {
        if (table[lin][col] != 0) {
            return false;
        }
        return true;
    }

    @Override
    //checking if the game is over
    public boolean isFinished() {
        if (this.horizontalCheck() || this.verticalCheck() || this.diagonalCheck() || isFull()) {
            this.over = true;
        }

        return this.over;
    }
    //Checks
    private boolean horizontalCheck() {
        for (int i = 0; i < 3; i++) {
            if (table[i][0] == 'X' && table[i][1] == 'X' && table[i][2] == 'X') {
                this.result = 'X';
                return true;
            }
            if (table[i][0] == 'O' && table[i][1] == 'O' && table[i][2] == 'O') {
                this.result = 'O';
                return true;
            }
        }
        return false;
    }
    private boolean verticalCheck() {
        for (int i = 0; i < 3; i++) {
            if (table[0][i] == 'X' && table[1][i] == 'X' && table[2][i] == 'X') {
                this.result = 'X';
                return true;
            }
            if (table[0][i] == 'O' && table[1][i] == 'O' && table[2][i] == 'O') {
                this.result = 'O';
                return true;
            }
        }
        return false;
    }
    private boolean diagonalCheck() {
        //first diagonal
        if (table[0][0] == 'X' && table[1][1] == 'X' && table[2][2] == 'X') {
            this.result = 'X';
            return true;
        }
        if (table[0][0] == 'O' && table[1][1] == 'O' && table[2][2] == 'O') {
            this.result = 'O';
            return true;
        }

        //second diagonal
        if (table[0][2] == 'X' && table[1][1] == 'X' && table[2][0] == 'X') {
            this.result = 'X';
            return true;
        }
        if (table[0][2] == 'O' && table[1][1] == 'O' && table[2][0] == 'O') {
            this.result = 'O';
            return true;
        }

        return false;
    }
    private boolean isFull() {
        for (int lin = 0; lin < 3; lin++){
            for (int col = 0; col < 3; col++){
                if (table[lin][col] == 0){
                    return false;
                }
            }
        }
        return true;
    }




}