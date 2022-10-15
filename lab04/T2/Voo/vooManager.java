import java.util.Scanner;
import java.io.File;

public class vooManager {
    public static void main(String[] args) {
        System.out.println("Escolha uma opção: ");
        Scanner scan = new Scanner(System.in);
        String opcao = scan.next(); 
        switch (opcao) {
            case "H":
            System.out.println("***Menu de Opções:***");
            System.out.println("***H-Mostrar menu***");
            System.out.println("***I-Carregar ficheiro com informações de voo***");
            System.out.println("***Q-Terminar o programa***");
            System.out.println("***F-Acrescentar um novo voo***");
            System.out.println("***R-Acrescentar uma nova reserva a um voo***");
            System.out.println("***C-Cancelar uma reserva***");
            System.out.println("***M-Exibir mapa de reservas de um voo***");
                break;
            case "I":
            System.out.println("Digite o nome da file: ");
            String filename = scan.next();
            File file = new File("filename");  
            

                break;
            case "M":
                break;
            case "F":

                break;   
            case "R":
                break;       
            case "Q":
            System.exit(0);
                break;       
            case "C":
                break;            
    
            default:
                break;
        }
    }
}
