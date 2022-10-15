public class Aviao {

    // Variaveis de uma instancia de Aviao
    private int[][] execFlight;
    private int[][] touristFlight;
    private int execSize = 0;
    private int touristSize = 0;

    // Construtor Aviao
    public Aviao(int execSize, int touristSize, int[][] execFlight, int[][] touristFlight) {
        this.execSize = execSize;
        this.touristSize = touristSize;
        this.execFlight = execFlight;
        this.touristFlight = touristFlight;
    }

    // ------- Getters + Setters ------- //

    public int[][] getExecFlight() {
        return execFlight;
    }

    public int[][] getTouristFlight() {
        return touristFlight;
    }

    public int getExecSize() {
        return execSize;
    }

    public int getExecNumFilas() {
        return execFlight.length;
    }

    public int getTouristNumFilas() {
        return touristFlight.length;
    }

    public int getExecNumLugares() {
        return execFlight[0].length;
    }

    public int getTouristNumLugares() {
        return touristFlight[0].length;
    }

    public int getTouristSize() {
        return touristSize;
    }

    public void setExecFlight(int[][] execFlight) {
        this.execFlight = execFlight;
    }

    public void setTouristFlight(int[][] touristFlight) {
        this.touristFlight = touristFlight;
    }

    public void setExecSize(int execSize) {
        this.execSize = execSize;
    }

    public void setTouristSize(int touristSize) {
        this.touristSize = touristSize;
    }

    // --------------------------------- //

    // Marcar lugar no seu modelo
    public void markSeats(char classeFlight, int j, int i, int nReserve) {
        if (classeFlight == 'E')
            execFlight[j-1][i] = nReserve;
        if (classeFlight == 'T')
            touristFlight[j-1][i] = nReserve;
    }

    // Desmarcar lugar no seu modelo
    public void unmarkSeats(char classeFlight, int j, int i) {
        if (classeFlight == 'E')
            execFlight[j-1][i] = 0;
        if (classeFlight == 'T')
            touristFlight[j-1][i] = 0;
    }

    // Ajustar marcas dos lugares no modelo após cancelamento de uma reserva
    public void updateSeatMark(char classeFlight, int j, int i) {
        if (classeFlight == 'E')
            execFlight[j-1][i]--;
        if (classeFlight == 'T')
            touristFlight[j-1][i]--;
    }
}
