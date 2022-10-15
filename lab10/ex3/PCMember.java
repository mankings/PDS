package ex3;

public class PCMember extends BaseMember {
    public PCMember(Chat m, String name) {
        super(m, name);
    }

    @Override
    public void receive(String message) {
        System.out.println("[" + name + "](PC): Received \"" + message + "\"");
    }
}