package v2;

public class MankingsCakeBuilder extends BaseCakeBuilder {
    @Override
    public void addCakeLayer() {
        this.getCake().setCakeLayer("Soft chocolate");
    }

    @Override
    public void addCreamLayer() {
        this.getCake().setMidCream(Cream.Vanilla);
    }

    @Override
    public void addTopLayer() {
        this.getCake().setTopCream(Cream.Manquisse);
    }

    @Override
    public void addTopping() {
        this.getCake().setTopping(Topping.Fruit);
    }
}
