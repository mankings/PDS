package v2;

public class SpongeCakeBuilder extends BaseCakeBuilder {
    @Override
    public void addCakeLayer() {
        this.getCake().setCakeLayer("Sponge");
    }

    @Override
    public void addCreamLayer() {
        this.getCake().setMidCream(Cream.Red_Berries);
    }

    @Override
    public void addTopLayer() {
        this.getCake().setTopCream(Cream.Whipped_Cream);
    }

    @Override
    public void addTopping() {
        this.getCake().setTopping(Topping.Fruit);
    }
}
