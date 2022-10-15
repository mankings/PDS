import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class FlightManager {

    // Variavel de FlightManager
    public static HashMap<String, Voo> recordedFlights = new HashMap<>();

    // Runner (main class)
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                if (!menu(scanner)) break;
            } catch (Exception e) {
                System.out.println("Formato errado.");
            }
        }
        scanner.close();
    }

    // Menu com opções disponíveis
    private static boolean menu(Scanner scanner) {

        System.out.println("\nEscolha uma opção: (H para ajuda)");

        System.out.print("> ");
        String input = scanner.nextLine();
        String[] cmd = input.split("[ \t]+",2);
        String opt = cmd[0];
        String flightCode;
        String[] reservation_code;
        Voo v;

        switch (opt) {
            case "H", "h" -> printHelp();
            case "I", "i" -> {
                File wordFile = new File(cmd[1]);
                boolean result;
                result = wordFile.exists();
                if (result)
                    System.out.println(wordFile.getAbsolutePath());
                if (!result) {
                    System.out.println("File '" +wordFile + "' not found.");
                }
                else {
                    v = new Voo(wordFile);
                    if (!recordedFlights.containsKey(v.getFlightCode()))
                        recordedFlights.put(v.getFlightCode(), v);
                    else
                        System.out.println("<!> Identificação de um voo já registado.");
                }
            }
            case "M", "m" -> {
                flightCode = cmd[1];
                if (recordedFlights.containsKey(flightCode))
                    recordedFlights.get(flightCode).PrintFlightMap();
                else
                    System.out.println("<!> Identificação de um voo não registado.");
            }
            case "F", "f" -> {
                if (recordedFlights.containsKey(cmd[1].split("[ \t]", 2)[0]))
                    System.out.println("<!> Identificação de um voo já registado.");
                else {
                    v = new Voo(cmd[1]);
                    recordedFlights.put(v.getFlightCode(), v);
                }
            }
            case "R", "r" -> {
                reservation_code = cmd[1].split("[ \t]+", 2);
                if (recordedFlights.containsKey(reservation_code[0]))
                    recordedFlights.get(reservation_code[0]).addReservation(reservation_code[1]);
                else
                    System.out.println("<!> Identificação de um voo não registado.");
            }
            case "C", "c" -> {
                reservation_code = cmd[1].split(":", 2);
                if (recordedFlights.containsKey(reservation_code[0]))
                    recordedFlights.get(reservation_code[0]).cancelReservation(Integer.parseInt(reservation_code[1]));
                else
                    System.out.println("<!> Identificação de um voo não registado.");
            }
            case "Q", "q" -> {
                System.out.println("Exiting...");
                return false;
            }
            default -> {
                System.out.println("Opção Inválida!\n");
                printHelp();
            }
        }
        return true;
    }

    // Print de ajuda
    private static void printHelp() {
        System.out.println
                (
                        """
                                Usage:
                                > H
                                 - apresenta as opções do menu;

                                > I <filename>
                                 - lê um ficheiro de texto contento informação sobre um voo;

                                > M <flight_code>
                                 - exibe o mapa das reservas de um voo;

                                > F <flight_code> <num_seats_executive> <num_seats_tourist>
                                 - acrescenta um novo voo;

                                > R <flight_code> <class> <number_seat>
                                 - acrescenta uma nova reserva a um voo;

                                > C <reservation_code>
                                 - cancela uma reserva;

                                > Q
                                 - termina o programa;
                                """
                );
    }

}
