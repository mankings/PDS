package ex3;

public class ReservadoState implements State {
    @Override
    public void regista(Livro livro) {
        System.out.println("Operação não disponível");
    }

    @Override
    public void requisita(Livro livro) {
        System.out.println("Operação não disponível");
    }

    @Override
    public void devolve(Livro livro) {
        System.out.println("Operação não disponível");
    }

    @Override
    public void cancelaReserva(Livro livro) {
        livro.setState(new DisponivelState());
    }

    @Override
    public void reserva(Livro livro) {
        System.out.println("Operação não disponível");
    }

    @Override
    public String toString() {
        return "[Reservado]";
    }
}
