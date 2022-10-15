package v2;

public class ChocolateCakeBuilder extends BaseCakeBuilder {
    @Override
    public void addCakeLayer() {
        this.getCake().setCakeLayer("Soft chocolate");
    }

    @Override
    public void addCreamLayer() {
        this.getCake().setMidCream(null);
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
