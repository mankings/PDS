package ex3;

public class Livro {
    private String title;
    private int ISBN;
    private int year;
    private String author;
    private State state;

    public Livro(String title, int ISBN, int year, String author) {
        this.title = title;
        this.ISBN = ISBN;
        this.year = year;
        this.author = author;
        this.state =  new InventarioState();
    }

    /*------------------------------------*/
    public void setState(State state) {
        this.state = state;
    }

    /*------------------------------------*/
    public String getTitle() { return title; }

    public int getISBN() { return ISBN; }

    public int getYear() { return year; }

    public String getAuthor() { return author; }

    public State getState() { return state; }

    /*------------------------------------*/
    public void regista() { state.regista(this); }

    public void requisita() { state.requisita(this); }

    public void devolve() { state.devolve(this); }

    public void cancelaReserva() { state.cancelaReserva(this); }

    public void reserva() { state.reserva(this); }
}
