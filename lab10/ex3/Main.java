package ex3;

public class Main {
    public static void main(String[] args) {
        ChatImplementation mediator = new ChatImplementation();
        MobileMember Andre = new MobileMember(mediator, "André");
        PCMember Joao = new PCMember(mediator, "João");
        WiiMember Joana = new WiiMember(mediator, "Joana");

        mediator.addMember(Andre);
        mediator.addMember(Joao);
        mediator.addMember(Joana);

        Andre.send("Hello World");
        Joao.send("Hello");
    }
}
