package ex3;

public class WiiMember extends  BaseMember{
    public WiiMember(Chat m, String name) {
        super(m, name);
    }

    @Override
    public void receive(String message) {
        System.out.println("[" + name + "](Wii): Received \"" + message + "\"");
    }
}
