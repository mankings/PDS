public class Card {
    private int ID;
    private String name;
    private static int ID_num = 1;

    public Card(String name) {
        this.ID = ID_num;
        this.name = name;
        ID_num++;
    }

    public String getCard() {
        return "Name: " + this.name + "\nID #: " + this.ID;
    }
}