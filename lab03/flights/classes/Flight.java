package classes;

import java.util.HashMap;
import java.util.Collections;

public class Flight {
    private String code;
    private Plane plane;
    //private int availableESeats;
    //private int availableTSeats;
    private HashMap<Integer, Reservation> reservations;
    private int[][] seatMap;

    public Flight(String code, Plane plane) {
        this.code = code;
        this.plane = plane;
        this.reservations = new HashMap<>();
        this.initSeatMap();
    }

    public String getCode() {
        return this.code;
    }

    public Plane getPlane() {
        return plane;
    }



    /**
     * Initializes the seat map for the plane
     * 
     */
    private void initSeatMap() {
        int executiveRows = this.plane.getExecutive().getRows();
        int executiveSeatsPerRow = this.plane.getExecutive().getSeatsPerRow();
        int touristRows = this.plane.getTourist().getRows();
        int touristSeatsPerRow = this.plane.getTourist().getSeatsPerRow();

        //this.availableESeats = this.plane.getExecutive().totalSeats();
        //this.availableESeats = this.plane.getTourist().totalSeats();

        int i = 0;
        for(; i < executiveRows; i++) {
            this.seatMap[i] = new int[executiveSeatsPerRow];
        }

        for(; i < executiveRows + touristRows; i++) {
            this.seatMap[i] = new int[touristSeatsPerRow];
        }
    }

    /**
     * Makes a new reservation for this plane
     * 
     * @param reservation the reservation to add
     * @return inserted reservation
     */
    public Reservation addReservation(Reservation reservation) { // esta é uma reservation para adicionar, sem código; se for possível adicionar, tu atribuis um codigo e retorna-la
        int executiveRows = this.plane.getExecutive().getRows();
        int touristRows = this.plane.getTourist().getRows();

        int reservationNumber = 1;
        if(this.reservations.size() > 0) {
            reservationNumber = Collections.max(this.reservations.keySet()) + 1;
        }

        int start = 0, end = 0, rowSize = 0;
        switch(reservation.getType()) {
            case E:
                start = 0;
                end = executiveRows - 1;
                rowSize = this.plane.getExecutive().getSeatsPerRow();
            case T:
                start = executiveRows;
                end = touristRows + executiveRows - 1;
                rowSize = this.plane.getTourist().getSeatsPerRow();
        }

        // check for 1st empty row
        int row, freeChairs = 0;
        boolean freeRow;

        for(row = start; row <= end; row++) {
            freeRow = true;

            for(int chair = 0; chair < rowSize; chair++) {
                if(this.seatMap[row][chair] == 0) {
                    freeChairs = 0;
                } else {
                    freeRow = false;
                }
            }

            // if the row is free, try and book it
            if (freeRow && this.bookConsecutiveSeats(row, 0, end, reservation.getSize(), 0)) {
                reservation.setNumber(reservationNumber);
                this.reservations.put(reservationNumber, reservation);
                this.bookConsecutiveSeats(row, 0, end, reservation.getSize(), reservationNumber);
        
                return reservation;
              }
        }

        if(freeChairs < reservation.getSize()) {
            // impossible to make this reservation
            return null;
        }

        // if we got here, freeChairs > reservationSize, thus we can make this reservation by booking the remaining seats in each row
        reservation.setNumber(reservationNumber);
        this.reservations.put(reservationNumber, reservation);

        int missing = reservation.getSize();

        for(row = start; row <= end; row++) {
            for(int chair = 0; chair < rowSize; chair++) {
                this.seatMap[row][chair] = reservationNumber;
                reservation.addSeatCode(row, chair);
                missing--;
            }

            if(missing == 0) return reservation;
        }

        return null;
    }

    /**
     * 
     * @param row row of the seat to be reserved
     * @param chair chair number of the seat to be reserved
     * @param maxRow max number of rows of the class
     * @param missingSeats number of seats left to reserve
     * @param reservationNumber reservation number (0 if we dont want to save, just check if reservation is possible)
     * @return
     */
    private boolean bookConsecutiveSeats(int row, int chair, int maxRow, int missingSeats, int reservationNumber) {
        if(row > maxRow) return false;

        if(missingSeats == 0) return true;

        if(chair >= this.seatMap[row].length)
            return this.bookConsecutiveSeats(row + 1, 0, maxRow, missingSeats, reservationNumber);

        if(this.seatMap[row][chair] == 0) {
            // if reservationNumber is valid, save the seat/reserve it (else we are are just checking)
            if(reservationNumber > 0) {
                this.seatMap[row][chair] = reservationNumber;
                this.reservations.get(reservationNumber).addSeatCode(row, chair);
            }

            boolean sucess = this.bookConsecutiveSeats(row, chair + 1, maxRow, missingSeats, reservationNumber);

            if(!sucess && reservationNumber >= 0) { // if couldn't make reservation, reset seats
                this.seatMap[row][chair] = 0;
            }
    
            return sucess;
        } else {
            return false;
        }
    }

    public boolean cancelReservation(int number) throws SystemException {
        if(!this.reservations.containsKey(number)) {
            throw new SystemException(SystemException.Type.UnknownReservation);
        }

        for(int i = 0; i < seatMap.length; i++) {
            for(int j = 0; j < seatMap[i].length; j++) {
                if(seatMap[i][j] == number) {
                    seatMap[i][j] = 0;
                }
            }
        }

        this.reservations.remove(number);
        return true;
    }
}
