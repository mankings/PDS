import java.util.Scanner;

import classes.FlightSystem;
import classes.SystemException;

public class Ex2 {
    public static void main(String[] args) throws SystemException {
        Scanner sc = new Scanner(System.in);
        FlightSystem system = new FlightSystem();
        boolean working = true;

        while (working) {
            String[] input = sc.nextLine().split(" ");

            switch (input[0]) {
                case "I":
                    system.readFlight(input[1]);
                    break;
                case "M":
                    break;
                case "F":
                    break;
                case "R":
                    break;
                case "C":
                    break;
                case "Q":
                    working = false;
                    break;
                default:
                    throw new SystemException(SystemException.Type.UnknownCommand);
            }
        }

        sc.close();
    }
}
