public class GaloController implements JGaloInterface {
    char[][] matriz_jogo = new char[3][3];
    char winner = ' ';
    boolean isFirstPlayer = true; //True é X e false é O

    int count = 0;

    public GaloController(boolean xFirst){
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                matriz_jogo[x][y] = ' ';
            }
        }

        isFirstPlayer = xFirst;
    }

    @Override
    public char getActualPlayer() {
        if(isFirstPlayer)
            return 'X';
        else
            return 'O';
    }

    @Override
    public boolean isFinished() {
        winner = findWinner();
        if (count == 9 || winner != ' '){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public char checkResult() {
        return winner;
    }

    @Override
    public boolean setJogada(int lin, int col) {
        lin--;
        col--;

        if(matriz_jogo[lin][col] != ' '){
            return false;
        }

        count++;
        matriz_jogo[lin][col] = getActualPlayer();
        isFirstPlayer = !isFirstPlayer;


        return true;
    }

    public char findWinner() {

        if (       ((matriz_jogo[0][0] == 'O') && (matriz_jogo[0][1] == 'O') && (matriz_jogo[0][2] == 'O'))
                || ((matriz_jogo[1][0] == 'O') && (matriz_jogo[1][1] == 'O') && (matriz_jogo[1][2] == 'O'))
                || ((matriz_jogo[2][0] == 'O') && (matriz_jogo[2][1] == 'O') && (matriz_jogo[2][2] == 'O'))
                || ((matriz_jogo[0][0] == 'O') && (matriz_jogo[1][0] == 'O') && (matriz_jogo[2][0] == 'O'))
                || ((matriz_jogo[0][1] == 'O') && (matriz_jogo[1][1] == 'O') && (matriz_jogo[2][1] == 'O'))
                || ((matriz_jogo[0][2] == 'O') && (matriz_jogo[1][2] == 'O') && (matriz_jogo[2][2] == 'O'))
                || ((matriz_jogo[0][0] == 'O') && (matriz_jogo[1][1] == 'O') && (matriz_jogo[2][2] == 'O'))
                || ((matriz_jogo[0][2] == 'O') && (matriz_jogo[1][1] == 'O') && (matriz_jogo[2][0] == 'O'))
                ) {
            return 'O';
        }
        if (       ((matriz_jogo[0][0] == 'X') && (matriz_jogo[0][1] == 'X') && (matriz_jogo[0][2] == 'X'))
                || ((matriz_jogo[1][0] == 'X') && (matriz_jogo[1][1] == 'X') && (matriz_jogo[1][2] == 'X'))
                || ((matriz_jogo[2][0] == 'X') && (matriz_jogo[2][1] == 'X') && (matriz_jogo[2][2] == 'X'))
                || ((matriz_jogo[0][0] == 'X') && (matriz_jogo[1][0] == 'X') && (matriz_jogo[2][0] == 'X'))
                || ((matriz_jogo[0][1] == 'X') && (matriz_jogo[1][1] == 'X') && (matriz_jogo[2][1] == 'X'))
                || ((matriz_jogo[0][2] == 'X') && (matriz_jogo[1][2] == 'X') && (matriz_jogo[2][2] == 'X'))
                || ((matriz_jogo[0][0] == 'X') && (matriz_jogo[1][1] == 'X') && (matriz_jogo[2][2] == 'X'))
                || ((matriz_jogo[0][2] == 'X') && (matriz_jogo[1][1] == 'X') && (matriz_jogo[2][0] == 'X'))
                ) {

            return 'X';
        }
        return ' ';
    }
}
