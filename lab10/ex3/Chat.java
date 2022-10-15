package ex3;

public interface Chat {
    void addMember(BaseMember member);
    void send(String msg, BaseMember member);
}
