package v2;

public class Cake {
    private Shape shape;
    private String cakeLayer;
    private int numLayers;
    private Cream midCream;
    private Cream topCream;
    private Topping topping;
    private String message;

    public Cake() {
        this.shape = Shape.Circle;
        this.numLayers = 1;
    }

    public Shape getShape() {
        return shape;
    }

    public String getCakeLayer() {
        return cakeLayer;
    }

    public int getNumLayers() {
        return numLayers;
    }

    public Cream getMidCream() {
        return midCream;
    }

    public Cream getTopCream() {
        return topCream;
    }

    public Topping getTopping() {
        return topping;
    }

    public String getMessage() {
        return message;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void setCakeLayer(String cakeLayer) {
        this.cakeLayer = cakeLayer;
    }

    public void setNumLayers(int numLayers) {
        this.numLayers = numLayers;
    }

    public void setMidCream(Cream midCream) {
        this.midCream = midCream;
    }

    public void setTopCream(Cream topCream) {
        this.topCream = topCream;
    }

    public void setTopping(Topping topping) {
        this.topping = topping;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        String str = (this.getMidCream() != null) ? String.format(" and %s cream", this.getMidCream()) : "";
        return String.format("%s cake with %d layers%s, topped with %s cream and %s. Message says: \"%s\".",
                this.getCakeLayer(), this.getNumLayers(), this.getTopCream(), str, this.getTopping(), this.getMessage());
    }
}
