package classes;

import java.util.ArrayList;

public class Reservation {
    private ReservationType type;
    private int size;
    private int number;
    private ArrayList<String> seatCodes;

    public Reservation(ReservationType type, int size) {
        this.type = type;
        this.size = size;
        this.number = 0;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ReservationType getType() {
        return this.type;
    }

    public void setResType(ReservationType type) {
        this.type = type;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getResType() {
        return this.type.getLabel();
    }

    public int getSize() {
        return this.size;
    }

    public int getNumber() {
        return this.number;
    }

    public boolean addSeatCode(int row, int chair) {
        char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
        String seatCode = letters[chair] + Integer.toString(row);

        if (this.seatCodes.size() < this.size) {
            if (!this.seatCodes.contains(seatCode)) {
                this.seatCodes.add(seatCode);
                return true;
            }
        }

        return false;
    }
}

enum ReservationType {
    T("Turística"),
    E("Executiva");

    private String label;

    ReservationType(String label) {
        this.label = label;
    }

    public static ReservationType strToType(String str) {
        switch (str) {
            case "E":
                return ReservationType.E;
            case "T":
                return ReservationType.T;
        }

        return null;
    }

    public String getLabel() {
        return label;
    }
}