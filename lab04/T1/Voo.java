import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Voo {

    // Variaveis de uma instancia de Voo
    private Aviao AV;
    private String flightCode;
    private int execReserved = 0;
    private int touristReserved = 0;
    private int nReserve = 1;

    // Construtor com file
    public Voo(File flight) {
        ReadFlight(flight);
    }

    // Construtor com string
    public Voo(String flight) {
        AV = setupFlight(flight);
        PrintFlightInformation();
    }

    // Retorna o código do voo
    public String getFlightCode() {
        return flightCode;
    }

    // Lê o ficheiro: conclui setup do voo com as possíveis reservas
    public void ReadFlight (File flight) {

        Scanner input = null;
        try {
            input = new Scanner(flight);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert input != null;
        AV = setupFlight(input.nextLine());

        PrintFlightInformation();

        while (input.hasNextLine())
            addReservation(input.nextLine());

        input.close();
    }

    // Cancela uma reserva
    public void cancelReservation(int sequential_reservation_number) {
        if (sequential_reservation_number >= nReserve) {
            PrintErrorCancelation();
            return;
        }

        char classeFlight;
        int j;
        boolean isExec = AV.getExecFlight() != null;

        j = 0;
        classeFlight = 'E';
        if (isExec) {
            for (int[] row : AV.getExecFlight()) {
                j++;
                for (int i = 0; i < row.length; i++) {
                    if (row[i] == sequential_reservation_number) {
                        AV.unmarkSeats(classeFlight, j, i);
                        execReserved--;
                    }
                    if (row[i] > sequential_reservation_number)
                        AV.updateSeatMark(classeFlight, j, i);
                }
            }
        }

        j = 0;
        classeFlight = 'T';
        for (int[] row : AV.getTouristFlight()) {
            j++;
            for (int i = 0; i < row.length; i++) {
                if (row[i] == sequential_reservation_number) {
                    AV.unmarkSeats(classeFlight, j, i);
                    touristReserved--;
                }
                if (row[i] > sequential_reservation_number)
                    AV.updateSeatMark(classeFlight, j, i);
            }
        }

        nReserve--;
    }

    // Adiciona uma reserva
    public void addReservation(String reserva) {
        String[] line = reserva.split(" +");
        char classeFlight = line[0].charAt(0);
        int reserved = Integer.parseInt(line[1]);
        if (classeFlight == 'E') {
            if (AV.getExecSize() - execReserved - reserved < 0)
                PrintErrorReservation(classeFlight, reserved);
            else {
                execReserved += reserved;
                PrintReservedSeats(fillSeats(classeFlight, reserved));
                nReserve++;
            }
        }
        if (classeFlight == 'T') {
            if (AV.getTouristSize() - touristReserved - reserved < 0)
                PrintErrorReservation(classeFlight, reserved);
            else {
                touristReserved += reserved;
                PrintReservedSeats(fillSeats(classeFlight, reserved));
                nReserve++;
            }
        }
    }

    // Print dos lugares após reserva
    private void PrintReservedSeats(String[] fillSeats) {
        System.out.println(flightCode + ":" + nReserve + " = " + String.join(" | ", fillSeats));
    }

    // Setup do voo
    private Aviao setupFlight(String flight) {
        int[][] execFlight = null, touristFlight = null;
        int execSize = 0, touristSize = 0;

        Pattern p = Pattern.compile(">?(?<flightCode>[a-zA-Z0-9]+) (?<filaClass1>\\d+)x(?<lugaresClass1>\\d+)" +
                "( (?<filaClass2>\\d+)x(?<lugaresClass2>\\d+))?");

        Matcher m = p.matcher(flight);

        String filaClass1 = null, filaClass2 = null, lugaresClass1 = null, lugaresClass2 = null;
        while (m.find()) {
            flightCode = m.group("flightCode");
            if (flightCode == null)
                PrintErrorFlightCode();
            filaClass1 = m.group("filaClass1");
            filaClass2 = m.group("filaClass2");
            lugaresClass1 = m.group("lugaresClass1");
            lugaresClass2 = m.group("lugaresClass2");
        }

        int touristFila, touristLugares, execFila, execLugares;
        if (filaClass2 == null)
        {
            assert filaClass1 != null;
            assert lugaresClass1 != null;
            touristFila = Integer.parseInt(filaClass1);
            touristLugares = Integer.parseInt(lugaresClass1);
        } else {
            execFila = Integer.parseInt(filaClass1);
            execLugares = Integer.parseInt(lugaresClass1);
            execFlight = new int[execFila][execLugares];
            execSize = execFila*execLugares;
            touristFila = Integer.parseInt(filaClass2);
            touristLugares = Integer.parseInt(lugaresClass2);
        }

        touristSize = touristFila*touristLugares;
        touristFlight = new int[touristFila][touristLugares];

        Arrays.stream(touristFlight).forEach(row -> Arrays.fill(row, 0));

        if (execFlight != null)
            Arrays.stream(execFlight).forEach(row -> Arrays.fill(row, 0));

        return new Aviao(execSize, touristSize, execFlight, touristFlight);
    }

    // Marca lugares reservados
    private String[] fillSeats(char classeFlight, int reserved) {

        int j;
        int n = reserved;
        String[] reservedSeats = new String[n];
        boolean isExec = AV.getExecFlight() != null;

        j = 0;
        for (int[] row : (classeFlight == 'E')? AV.getExecFlight() : AV.getTouristFlight()) {
            j++;
            if (Arrays.stream(row).sum() == 0)
                for (int i = 0; i < row.length; i++) {
                    AV.markSeats(classeFlight, j, i, nReserve);
                    reservedSeats[n-reserved] = ((char) (65 + i)) + "" + (j + ((!isExec || classeFlight == 'E')? 0 : AV.getExecFlight().length));
                    reserved--;
                    if (reserved == 0)
                        return reservedSeats;
                }
        }

        j = 0;
        for (int[] row : (classeFlight == 'E')? AV.getExecFlight() : AV.getTouristFlight()) {
            j++;
            for (int i = 0; i < row.length; i++)
                if (row[i] == 0) {
                    AV.markSeats(classeFlight, j, i, nReserve);
                    reservedSeats[n-reserved] = ((char) (65 + i)) + "" + (j + ((!isExec || classeFlight == 'E')? 0 : AV.getExecFlight().length));
                    reserved--;
                    if (reserved == 0)
                        return reservedSeats;
                }
        }
        return null;
    }

    // Print do mapa do aviao
    public void PrintFlightMap() {
        boolean isExec = AV.getExecFlight() != null;
        int nFila = AV.getTouristNumFilas() + ((isExec)? AV.getExecNumFilas() : 0);
        int nSeat = Math.max(AV.getTouristNumLugares(), ((isExec)? AV.getExecNumLugares() : 0));

        System.out.print(" ");
        for (int i = 1; i <= nFila; i++)
            System.out.printf("%4d", i);

        for (int i = 0; i < nSeat; i++)
        {
            System.out.printf("\n%c", (char) (65 + i));
            if (isExec)
                for (int[] ints : AV.getExecFlight())
                    System.out.printf("%4s", ((i < AV.getExecNumLugares())) ? String.valueOf(ints[i]) : " ");
            for (int[] ints : AV.getTouristFlight())
                System.out.printf("%4s", ((i < AV.getTouristNumLugares())) ? String.valueOf(ints[i]) : " ");
        }
        System.out.println();
    }

    // Print da informação do voo
    private void PrintFlightInformation() {
        System.out.println("Código de voo " + flightCode + ".");
        System.out.print("Lugares disponíveis: ");
        if (AV.getExecSize() > 0)
            System.out.print(AV.getExecSize() + " lugares em classe Executiva; ");
        System.out.print(AV.getTouristSize() + " lugares em classe Turística.");
        if (AV.getExecSize() <= 0)
            System.out.print("\n<!> Classe executiva não disponível neste voo.");
        System.out.println();
    }

    // Erro de reserva
    private void PrintErrorReservation(char classeFlight, int reserved) {
        System.out.println("<!> Não foi possível obter lugares para a reserva: " + classeFlight + " " + reserved);
        if (classeFlight == 'E' && AV.getExecFlight() == null)
            System.out.print("<!> Classe executiva não disponível neste voo.\n");
    }

    // Erro de cancelamento de reserva
    private void PrintErrorCancelation() {
        System.out.println("<!> Tentativa de cancelamento de reserva que não existe");
    }

    // Erro de padrão do código de voo
    private void PrintErrorFlightCode() {
        System.out.println("Não foi possível criar Voo: Código não é um valor alfanumérico");
    }

}

