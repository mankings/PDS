package v2;

public class YogurtCakeBuilder extends BaseCakeBuilder {
    @Override
    public void addCakeLayer() {
        this.getCake().setCakeLayer("Yogurt");
    }

    @Override
    public void addCreamLayer() {
        this.getCake().setMidCream(Cream.Vanilla);
    }

    @Override
    public void addTopLayer() {
        this.getCake().setTopCream(Cream.Red_Berries);
    }

    @Override
    public void addTopping() {
        this.getCake().setTopping(Topping.Chocolate);
    }
}
