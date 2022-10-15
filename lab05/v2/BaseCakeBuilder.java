package v2;

public abstract class BaseCakeBuilder implements CakeBuilder {
    private Cake cake = new Cake();

    @Override
    public void setCakeShape(Shape shape) {
        this.cake.setShape(shape);
    }

    @Override
    public void addMessage(String m) {
        this.cake.setMessage(m);
    }

    @Override
    public void createCake() {
        this.addCakeLayer();
        this.addCreamLayer();
        this.addTopLayer();
        this.addTopping();
    }

    @Override
    public Cake getCake() {
        return this.cake;
    }
}
