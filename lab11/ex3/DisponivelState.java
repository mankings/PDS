package ex3;

public class DisponivelState implements State {

    @Override
    public void regista(Livro livro) {

    }

    @Override
    public void requisita(Livro livro) {
        livro.setState(new EmprestadoState());
    }

    @Override
    public void devolve(Livro livro) {
        System.out.println("Operação não disponível");
    }

    @Override
    public void cancelaReserva(Livro livro) {
        livro.setState(new ReservadoState());
    }

    @Override
    public void reserva(Livro livro) {
        System.out.println("Operação não disponível");
    }

    @Override
    public String toString() {
        return "[Disponível]";
    }
}
