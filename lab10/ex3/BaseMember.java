package ex3;

public abstract class BaseMember {
    private final Chat chat;
    protected String name;
    public BaseMember(Chat m, String name) {
        chat = m;
        this.name = name;
    }
    public void send(String message) {
        System.out.println("--------------------------------\n" +
                           "[" + name + "]: Sending \"" + message + "\"");
        chat.send(message, this);
    }
    public Chat getChat() {
        return chat;
    }
    public abstract void receive(String message);
}
