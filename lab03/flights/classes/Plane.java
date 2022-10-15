package classes;
public class Plane {
    private SeatingConfig executive;
    private SeatingConfig tourist;

    public Plane(String E_config, String T_config) {
        this.executive = new SeatingConfig(E_config);
        this.tourist = new SeatingConfig(T_config);
    }

    public Plane(String T_config) {
        this.executive = null;
        this.tourist = new SeatingConfig(T_config);
    }

    public SeatingConfig getExecutive() {
        return this.executive;
    }

    public SeatingConfig getTourist() {
        return this.tourist;
    }
}

class SeatingConfig {
    private int rows;
    private int seatsPerRow;

    public SeatingConfig(String config) {
        String[] data = config.split("x");
        this.rows = Integer.parseInt(data[0]);
        this.seatsPerRow = Integer.parseInt(data[1]);
    }

    public int getRows() {
        return this.rows;
    }

    public int getSeatsPerRow() {
        return this.seatsPerRow;
    }

    public int totalSeats() {
        return this.rows * this.seatsPerRow;
    }

    public String toString() {
        return this.rows + "x" + this.seatsPerRow;
    }
}