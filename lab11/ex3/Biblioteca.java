package ex3;

import java.util.ArrayList;
import java.util.Scanner;

public class Biblioteca {
    ArrayList<Livro> livros = new ArrayList<>();
    public Biblioteca() {
    }

    public void addLivro(Livro livro) {
        livros.add(livro);
    }

    public ArrayList<Livro> getLivros(){
        return livros;
    }

    public void showLivros() {
        int sizeTitle=0, sizeAuthor=0, sizeState=0, currentLivro = 0;
        int numberOfBooks = (int) (Math.log10(livros.size()) + 1);

        for(Livro livro : livros) {
            if(livro.getTitle().length() > sizeTitle)
                sizeTitle=livro.getTitle().length();

            if(livro.getAuthor().length() > sizeAuthor)
                sizeAuthor=livro.getAuthor().length();

            if(livro.getState().toString().length() > sizeState)
                sizeState=livro.getState().toString().length();
        }


        System.out.println("*** Biblioteca ***");
        for(Livro livro : livros) {
            currentLivro++;
            System.out.printf("%-"+ numberOfBooks +"d\t%-" + sizeTitle + "s\t%-" + sizeAuthor + "s\t%-" + sizeState + "s\n",
                    currentLivro, livro.getTitle(), livro.getAuthor(), livro.getState().toString());
        }
        System.out.println("<livro>, <operação: (1)regista; (2)requisita; (3)devolve; (4)reserva; (5)cancela>\n");
    }

    public void consolePrompt() {
        while(true) {
            showLivros();
            System.out.print(">> ");
            Scanner sc = new Scanner(System.in);

            String resposta = sc.nextLine();
            String[] temp=resposta.split(",");
            if(temp.length!=2) {
                System.err.println("intoduza um comando do tipo <livro>,<operação>");
                continue;
            }

            Livro l = livros.get(Integer.valueOf(temp[0])-1);
            if(temp[1].equals("1")) {
                l.regista();
            }else if(temp[1].equals("2")){
                l.requisita();
            }else if(temp[1].equals("3")){
                l.devolve();
            }else if(temp[1].equals("4")){
                l.reserva();
            }else if(temp[1].equals("5")){
                l.cancelaReserva();
            }else{
                System.err.println("Esta operação não existe!");
                continue;
            }
        }
    }
}
