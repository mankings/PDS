package ex3;

import java.util.ArrayList;
import java.util.List;

public class ChatImplementation implements Chat {
    private final ArrayList<BaseMember> members;
    public ChatImplementation() {
        members = new ArrayList<>();
    }
    public void addMember(BaseMember member) {
        members.add(member);
    }

    public void send(String message, BaseMember originator) {
        //let all other screens know that this screen has changed
        for(BaseMember member: members) {
            //don't tell ourselves
            if(member != originator) {
                member.receive(message);
            }
        }
    }
}
