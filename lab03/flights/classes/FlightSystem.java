package classes;

import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FlightSystem {
    private HashMap<String, Flight> flights = new HashMap<>();

    public

    FlightSystem() {

    }

    public void showHelp() {
        // I filename - loads flight from filename
        // M flightcode - shows flight seat and reservation map
        // F flight_code (num_seats_executive) num_seats_tourist - makes new flight
        // R flight_code class number_seats - makes new reservation on a flight
        // C reservation_code - cancels a previously made reservation
        // Q - quits program
    }

    /**
     *
     * I command, reads a flight from a file
     * 
     * @param filename file name
     * @throws SystemException
     * 
     */
    public void readFlight(String filename) throws SystemException {
        Scanner sc = null;
        try {
            sc = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            throw new SystemException(SystemException.Type.FileNotFound);
        }

        String[] info = sc.nextLine().substring(1).split(" ");
        this.createFlight(info);

        Flight flight = this.flights.get(info[0]);

        System.out.printf("Código de voo %s.\n", flight.getCode());
        System.out.printf("Lugares disponíveis: %d em classe Executiva, %d em classe Turística.\n",
                flight.getPlane().getExecutive().totalSeats(), flight.getPlane().getTourist().totalSeats());

        while (sc.hasNextLine()) {
            String[] data = sc.nextLine().split(" ");
            makeReservation(flight.getCode(), data[0], data[1]);
        }

        sc.close();
    }

    /**
     * 
     * F command, creates a new flight
     * 
     * @param info String[] [flight code, (executive), tourist]
     * 
     */
    public void createFlight(String[] info) throws SystemException {
        if (info.length < 2) {
            throw new SystemException(SystemException.Type.ArgumentError);
        }

        Flight flight;
        if (info.length == 2) {
            flight = new Flight(info[0], new Plane(info[1]));
        } else {
            flight = new Flight(info[0], new Plane(info[1], info[2]));
        }

        this.flights.put(flight.getCode(), flight);
    }

    /**
     * Makes a new reservation
     * 
     * @param code   flight code
     * @param type   reservation type (E or T)
     * @param amount amount of seats
     * @return reservation object made (null if couldn't be made)
     */
    public Reservation makeReservation(String code, String type, String amount) {
        Flight flight = this.flights.get(code);

        Reservation reservation = new Reservation(ReservationType.strToType(type), Integer.parseInt(amount));
        Reservation r = flight.addReservation(reservation);

        if (r == null) {
            System.out.printf("Não foi possível obter lugares para a reserva: %s %s", type, amount);
        }

        return reservation;
    }

    /**
     * Cancels a previously made reservation
     * 
     * @return true if canceled, false if couldn't
     */
    public boolean cancelReservation(String code) throws SystemException {
        String[] codeData = code.split(":");
        if (codeData.length != 2) {
            throw new SystemException(SystemException.Type.ArgumentError);
        }

        Flight flight = flights.get(codeData[0]);
        if (flight == null) {
            throw new SystemException(SystemException.Type.UnknownFlight);
        }

        return flight.cancelReservation(Integer.parseInt(codeData[1]));
    }
}