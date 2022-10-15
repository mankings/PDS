package ex3;

public class Demo {
    public static void main(String[] args) {
        Biblioteca biblioteca=new Biblioteca();

        Livro l1=new Livro("Java Anti-Stress", 123456, 2000, "Omodionah");
        Livro l2=new Livro("A Guerra dos Padrões", 987654, 2000, "Jorge Omel");
        Livro l3=new Livro("A Procura da Luz", 192837, 2000, "Khumatkli");

        biblioteca.addLivro(l1);
        biblioteca.addLivro(l2);
        biblioteca.addLivro(l3);

        biblioteca.consolePrompt();
    }


}
