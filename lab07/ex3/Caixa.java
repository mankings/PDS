import java.util.ArrayList;

public class Caixa extends Item {
    private ArrayList<Item> children = new ArrayList<Item>();
    private String name;
    private float weight;
    private float totalWeight;

    public Caixa(String name, float weight) {
        this.name = name;
        this.weight = weight;
    }

    public void add(Item newitem) {
        children.add(newitem);
    }

    public String print() {
        return "* Caixa " + name + " [ Weight: " + weight + " ; Total : " + getWeight() + "]";
    }

    public float getWeight() {
        int totalWeight = 0;
        for (Item it : children)
            totalWeight += it.getWeight();
        return weight + totalWeight;
    }

    public void draw() {
        System.out.println(indent.toString() + print());
        indent.append("  ");
        for (Item it : children) {
            totalWeight += 1.0;

            if (it instanceof Caixa) {
                ((Caixa) it).draw();

            } else {
                System.out.println(indent.toString() + it.print());
            }
        }
        indent.setLength(indent.length() - 2);

    }
}
