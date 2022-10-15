import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class WSGenerator {
    private static ArrayList<String> leituraPalavras(ArrayList<String> array , String file ){
        try{
            File ficheiro = new File(file);
            Scanner scan = new Scanner(ficheiro);
            while(scan.hasNextLine()){
                String linha = scan.nextLine();
                String[] palavras = linha.split("[, ;]+");
                for (int i = 0; i < palavras.length; i++) {
                    if (!palavras[i].matches("[a-zA-Z]+")) {
                        continue;
                    }
                    else {
                        array.add(palavras[i].toUpperCase());
                    }                    
                }
            }


            scan.close();

        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return array;
    }
    private static int gerarPosicao(int min, int max){
        Random random = new Random();
        if(min > max){
            System.out.println("Tem de inserir um tamanho adequado!");
            System.exit(0);
        }
        return random.nextInt((max - min) + 1) + min;
    }

    private static char gerarLetra() {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random r = new Random();
        return letras.charAt(r.nextInt(letras.length()));
    }
    public static void main(String[] args) {

        String file_leitura = null;
        String file_saida = null;
        int tamanho = 0;
        int controlo1 = 0;
        int controlo2 = 0;
        int controlo3 = 0;
        ArrayList<String> palavras = new ArrayList<String>();
            

        if (args.length < 4) {
            System.err.println("Poucos argumentos de entrada!!");
            System.exit(1);
        }

        for(int i = 0; i < args.length; i++){
            switch (args[i]) {
                case "-i":
                    if (!args[i+1].contains(".txt")){
                        System.err.println("O ficheiro deve ser .txt");
                        System.exit(1);
                    }
                    file_leitura = args[i+1];
                    controlo1 = 1;
                    continue;
                case "-s":
                    if (!args[i+1].matches("[0-9]+")) {
                        System.err.println("Deve ser um número!");
                        System.exit(1);
                    }
                    tamanho = Integer.parseInt(args[i+1]);
                    controlo2= 1;  
                    continue;   
                case "-o":
                    if (!args[i+1].contains(".txt")){
                        System.err.println("O ficheiro deve ser .txt");
                        System.exit(1);
                    }
                    controlo3 = 1;
                    file_saida = args[i+1];  
                    continue;                         
            }



        }

        if(controlo1 != 1 || controlo2 != 1){
            System.out.println("É necessário inserir o ficheiro de leitura e o tamanho da sopa de letras!!");
            System.exit(1);

        }
        palavras = leituraPalavras(palavras, file_leitura);
        String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[][] sopa = new char[tamanho][tamanho];
        for(String s : palavras){
            loop: while (true) {
                int colunainicial = gerarPosicao(0, tamanho-1);
                int linhainicial = gerarPosicao(0, tamanho-1);

                if (alfabeto.contains(String.valueOf(sopa[linhainicial][colunainicial]))){
                    continue loop;
                }

                int linha;
                int coluna;
                // Estas duas variáveis serão usadas para escolher a direçao que a palavra vai ter

                do {
                    linha = gerarPosicao(-1, 1);
                    coluna = gerarPosicao(-1, 1);
                } while (coluna == 0 && linha == 0); // caso ambas sejam 0 significa que ficaram na mesma posição
                for (int t = 1; t <= s.length(); t++) {
                    try{
                        char posicao2 = sopa[linhainicial + t * linha][ colunainicial + t * coluna];
                        if (alfabeto.contains(String.valueOf(posicao2))){
                            continue loop;
                        }
                    } catch (Exception e) {
                    // se nao tiver espaço 

                        continue loop;
                    }
                    
                }

                for (int i = 0; i < s.length(); i++) {
                    sopa[linhainicial + i * linha][ colunainicial + i * coluna] = s.charAt(i);
                }


                break;
                
            }

            

        }
        for (int r = 0; r < sopa.length; r++) {
            for (int p = 0; p < sopa.length; p++) {
                if (String.valueOf(sopa[r][p]).contains(alfabeto)) {
                    continue;
                }
                // chars aleatorios sao geradas em maiuscula
                sopa[r][p] = gerarLetra();
            }
        }

        if(controlo3 == 1){
            try {
                FileWriter myWriter = new FileWriter(file_saida);
                for (int row = 0; row < tamanho; row++) {
                    for (int col = 0; col < tamanho; col++) {
                        myWriter.write(sopa[row][col] );
                    }
                    myWriter.write("\n");
                }
                myWriter.close();

            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            
        
        }
        else{
            for (int row = 0; row < tamanho; row++) {
                for (int col = 0; col < tamanho; col++) {
                    System.out.print(sopa[row][col] + " ");
                }
                System.out.println();
            }
        }



    }
}