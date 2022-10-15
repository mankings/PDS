# Aula03 - Notes

Eu utilizo text blocks e enhanced 'switch' blocks.

Dessa forma, para correr o programa é necesário atualizar o nível de linguagem do module para 15+.

Para fazer essa operação, em intellij: 

File -> Project Structure -> Project -> Project language level -> Selecionar 15+.

File -> Project Structure -> Modules -> Language level -> Selecionar 15+.

ou alterar o switch case e text block para:

    // SWITCH CASE
		
    switch (opt) {
            case "H":
                printHelp();
                break;
            case "I":
                File wordFile = new File("src/" + cmd[1]);
                boolean result;
                result = wordFile.exists();
                if (result)
                    System.out.println(GREEN + wordFile.getAbsolutePath() + RESET);
                if (!result) {
                    System.out.println(RED + "File '" +wordFile + "' not found." + RESET);
                }
                else {
                    v = new Voo(wordFile);
                    if (!recordedFlights.containsKey(v.getFlightCode()))
                        recordedFlights.put(v.getFlightCode(), v);
                    else
                        System.out.println(YELLOW + "<!> Identificação de um voo já registado." + RESET);
                }
                break;
            case "M": 
                flightCode = cmd[1];
                if (recordedFlights.containsKey(flightCode))
                    recordedFlights.get(flightCode).PrintFlightMap();
                else
                    System.out.println(YELLOW + "<!> Identificação de um voo não registado." + RESET);
                break;
            case "F":
                if (recordedFlights.containsKey(cmd[1].split("[ \t]", 2)[0]))
                    System.out.println(YELLOW + "<!> Identificação de um voo já registado." + RESET);
                else {
                    v = new Voo(cmd[1]);
                    recordedFlights.put(v.getFlightCode(), v);
                }
                break;
            case "R":
                reservation_code = cmd[1].split("[ \t]+", 2);
                if (recordedFlights.containsKey(reservation_code[0]))
                    recordedFlights.get(reservation_code[0]).addReservation(reservation_code[1]);
                else
                    System.out.println(YELLOW + "<!> Identificação de um voo não registado." + RESET);
                break;
            case "C":
                reservation_code = cmd[1].split(":", 2);
                if (recordedFlights.containsKey(reservation_code[0]))
                    recordedFlights.get(reservation_code[0]).cancelReservation(Integer.parseInt(reservation_code[1]));
                else
                    System.out.println(YELLOW + "<!> Identificação de um voo não registado." + RESET);
                break;
            case "Q":
                System.out.println(GREEN + "Exiting..." + RESET);
                return false;
            default:
                System.out.println(RED + "\nOpção Inválida!\n" + RESET);
                printHelp();
        }
        return true;
    }
	
	// TEXT BLOCK
	
	private static void printHelp() {
	
	    System.out.println
            (
            "\nUsage:"
            + "\n> H"
            + "\n - apresenta as opções do menu.\n"
            + "\n> I <filename>"
            + "\n - lê um ficheiro de texto contento informação sobre um voo.\n"
            + "\n> M <flight_code>"
            + "\n - exibe o mapa das reservas de um voo.\n"
            + "\n> F <flight_code> <num_seats_executive> <num_seats_tourist>"
            + "\n - acrescenta um novo voo.\n"
            + "\n> R <flight_code> <class> <number_seat>"
            + "\n - acrescenta uma nova reserva a um voo.\n"
            + "\n> C <reservation_code>"
            + "\n - cancela uma reserva.\n"
            + "\n> Q"
            + "\n - termina o programa.\n"
            );
	}

 
	
	
	

