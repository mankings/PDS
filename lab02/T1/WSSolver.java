import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Formatter;
import java.lang.*;

public class WSSolver{
    public static boolean isUpperCase(String s){
        for (int i=0; i<s.length(); i++)
        {
            if (!Character.isUpperCase(s.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        try {    
            //File ficheiro = new File("words.txt");
            Scanner scan = new Scanner(new File(args[0]));

            Formatter output;
            FileWriter myWriter = new FileWriter("teste.txt");
            output = new Formatter(myWriter);

            int linha = 0;
            int colunacount = 0;
            ArrayList<String> palavras = new ArrayList<String>();
            char[][] dicionario = new char[40][40];
            int[] posicao = new int[3];

            while (scan.hasNextLine()){
                String data = scan.nextLine();
                char[] ch = data.toCharArray();

                if(isUpperCase(data) == true ){
                  for(int s = 0; s<ch.length ; s++){
                      dicionario[linha][s] = Character.toLowerCase(ch[s]);
                      colunacount=s+1;
                  }
                  linha++;
                }
                
                for(int i = 0; i<ch.length ; i++){
                    if(ch[i] == ';' | ch[i] == ' '| ch[i] == ','){
                        String[] p = data.split("[, ;]+");
                        for(int j = 0; j<p.length ; j++){
                            if( palavras.contains(p[j]) == false && p[j].matches("[a-zA-Z]+")) {
                                palavras.add(p[j].toLowerCase());
                            }
                        }
                    }
                }
            }

            if (linha != colunacount){
              System.err.println("O puzzle deve conter o mesmo numero de linhas e colunas!");
              System.exit(1);
            }

            scan.close();

            char[][] newdic = new char[linha][linha];
            boolean[][] valido = new boolean[dicionario.length][dicionario[0].length];

            for (int i = 0; i < dicionario.length; i++) {
              for (int j = 0; j < dicionario[i].length; j++) {
                  valido[i][j] = false;
              }
            }

            for (int x = 0; x < palavras.size(); x++){
              String palavra = palavras.get(x);

              for (int linhas = 0; linhas < dicionario.length; linhas++){
                for (int colunas = 0; colunas < dicionario[linhas].length; colunas++){

                  //encontrar na direita
                  if (colunas + (palavra.length() - 1) < dicionario[linhas].length){
                    boolean encontrada = true;

                    for (int letras = 0; letras < palavra.length(); letras++) {
                      if (palavra.charAt(letras) != dicionario[linhas][colunas + letras]) {
                          encontrada = false;
                          break;
                      }
                    }
                  
                    if (encontrada) {
                        posicao[0] = linhas;
                        posicao[1] = colunas;
                        posicao[2] = 2;
                    }
                  }

                  // encontrar na esquerda
                  if (colunas - (palavra.length() - 1) >= 0) {
                    boolean encontrada = true;

                    for (int letras = 0; letras < palavra.length(); letras++) {
                      if (palavra.charAt(letras) != dicionario[linhas][colunas - letras]) {
                        encontrada = false;
                        break;
                      }
                    }
                    if (encontrada) {
                      posicao[0] = linhas;
                      posicao[1] = colunas;
                      posicao[2] = 6;
                    }
                  }

                  // encontrar em baixo
                  if (linhas + (palavra.length() - 1) < dicionario.length) {
                    boolean encontrada = true;

                    for (int letras = 0; letras < palavra.length(); letras++) {
                      if (palavra.charAt(letras) != dicionario[linhas + letras][colunas]) {
                        encontrada = false;
                        break;
                      }
                    }
                    if (encontrada) {
                      posicao[0] = linhas;
                      posicao[1] = colunas;
                      posicao[2] = 4;
                    }
                  }

                  // encontrar em cima
                  if (linhas - (palavra.length() - 1) >= 0) {
                    boolean encontrada = true;

                    for (int letras = 0; letras < palavra.length(); letras++) {
                      if (palavra.charAt(letras) != dicionario[linhas - letras][colunas]) {
                        encontrada = false;
                        break;
                      }
                    }
                    if (encontrada) {
                    posicao[0] = linhas;
                    posicao[1] = colunas;
                    posicao[2] = 0;
                    }
                  }

                  // encontrar acima direita
                  if ((linhas - (palavra.length() - 1) >= 0) && (colunas + (palavra.length() - 1) < dicionario[linhas].length)) {
                    boolean encontrada = true;

                    for (int letras = 0; letras < palavra.length(); letras++) {
                      if (palavra.charAt(letras) != dicionario[linhas - letras][colunas + letras]) {
                        encontrada = false;
                        break;
                      }
                    }
                    if (encontrada) {
                      posicao[0] = linhas;
                      posicao[1] = colunas;
                      posicao[2] = 1;
                    }
                  }

                  // encontrar acima esquerda
                  if ((linhas - (palavra.length() - 1) >= 0) && (colunas - (palavra.length() - 1) >= 0)) {
                    boolean encontrada = true;

                    for (int letras = 0; letras < palavra.length(); letras++) {
                      if (palavra.charAt(letras) != dicionario[linhas - letras][colunas - letras]) {
                        encontrada = false;
                        break;
                      }
                    }
                    if (encontrada) {
                      posicao[0] = linhas;
                      posicao[1] = colunas;
                      posicao[2] = 7;
                    }
                  }

                  // diagonal abaixo derecha
                  if ((linhas + (palavra.length() - 1) < dicionario.length) && (colunas + (palavra.length() - 1) <= dicionario[linhas].length)) {
                    boolean encontrada = true;

                    for (int letras = 0; letras < palavra.length(); letras++) {
                      if (palavra.charAt(letras) != dicionario[linhas + letras][colunas + letras]) {
                        encontrada = false;
                        break;
                      }
                    }
                    if (encontrada) {
                      posicao[0] = linhas;
                      posicao[1] = colunas;
                      posicao[2] = 3;
                    }
                  }

                  // encontrar abaixo esquerda
                  if ((linhas + (palavra.length() - 1) < dicionario.length) && (colunas - (palavra.length() - 1) >= 0)) {
                    boolean encontrada = true;

                    for (int letras = 0; letras < palavra.length(); letras++) {
                      if (palavra.charAt(letras) != dicionario[linhas + letras][colunas - letras]) {
                        encontrada = false;
                        break;
                      }
                    }
                    if (encontrada) {
                      posicao[0] = linhas;
                      posicao[1] = colunas;
                      posicao[2] = 5;
                    }
                  }

                }
              }

              
              int fila = posicao[0];
              int coluna = posicao[1];
              int direcao = posicao[2];

              int filaprint = posicao[0] + 1;
              int colunaprint = posicao[1] + 1;
              String direction = " ";        

              switch (direcao) {
                case 0:
                    for (int j = 0; j < palavra.length(); j++) {
                        valido[fila][coluna] = true;
                        fila--;
                    }
                    direction = "Up";
                    break;
                case 1:
                    for (int j = 0; j < palavra.length(); j++) {
                        valido[fila][coluna] = true;
                        fila--;
                        coluna++;
                    }
                    direction = "upRight";
                    break;
                case 2:
                    for (int j = 0; j < palavra.length(); j++) {
                        valido[fila][coluna] = true;
                        coluna++;
                    }
                    direction = "Right";
                    break;
                case 3:
                    for (int j = 0; j < palavra.length(); j++) {
                        valido[fila][coluna] = true;
                        fila++;
                        coluna++;
                    }
                    direction = "DownRight";
                    break;
                case 4:
                    for (int j = 0; j < palavra.length(); j++) {
                        valido[fila][coluna] = true;
                        fila++;
                    }
                    direction = "Down";
                    break;
                case 5:
                    for (int j = 0; j < palavra.length(); j++) {
                        valido[fila][coluna] = true;
                        coluna--;
                        fila++;
                    }
                    direction = "DownLeft";
                    break;
                case 6:
                    for (int j = 0; j < palavra.length(); j++) {
                        valido[fila][coluna] = true;
                        coluna--;
                    }
                    direction = "Left";
                    break;
                case 7:
                    for (int j = 0; j < palavra.length(); j++) {
                        valido[fila][coluna] = true;
                        fila--;
                        coluna--;
                    }
                    direction = "UpLeft";
                    break;
                
              }
              output.format("%-20s   %2d   %2d,%2d    %-8s\n", palavra, palavra.length(), filaprint, colunaprint, direction);
              
            }
          
            for (int i = 0; i < newdic.length; i++) {
              for (int j = 0; j < newdic[i].length; j++) {
                  if (valido[i][j] == true) {
                     newdic[i][j] = Character.toUpperCase(dicionario[i][j]);
                  } else {
                      newdic[i][j] = '.';
                  }
              }
            }

            myWriter.write("\n");
            for (int m = 0 ; m<newdic.length;m++) {
                myWriter.write("|");
                for (int j = 0; j<newdic[0].length;j++){
                  if (j != newdic[0].length){
                    myWriter.write(" " + newdic[m][j]);
                  }
                }
                myWriter.write("|");
                myWriter.write("\n");              
            }
            myWriter.close();
            output.close();

        }        
                
        catch (FileNotFoundException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
   }
}
        
  
