package ex3;

public class MobileMember extends BaseMember{

    public MobileMember(Chat m, String name) {
        super(m, name);
    }

    @Override
    public void receive(String message) {
        System.out.println("[" + name + "](Mobile): Received \"" + message + "\"");
    }
}
