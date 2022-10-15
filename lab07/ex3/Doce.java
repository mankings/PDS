public class Doce extends Item {
    private String name;
    private float weight;

    public Doce(String name, float weight) {
        this.name = name;
        this.weight = weight;
    }

    public String print() {
        return " Doce " + name + "- Weight " + weight;
    }

    public float getWeight() {
        return weight;
    }
}