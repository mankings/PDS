package ex2;

public abstract class Chef {
    protected int max = 20;
    protected int min = 5;

    private Chef sucessor = null;

    public void chef(Request r) {
        if(sucessor != null) {
            sucessor.chef(r);
        }
    }

    protected int rand() {
        return (int) (Math.random()*(max - min + 1) + min);
    }

    protected boolean canMake(Request r, String type) {
        return (r != null) && (r.getType() == type);
    }

    public Chef setSucessor(Chef sucessor) {
        this.sucessor = sucessor;
        return this;
    }
}

class Sushi extends Chef {
    @Override
    public void chef(Request r) {
        System.out.print("SushiChef: ");
        if(canMake(r, "sushi")) {
            System.out.println("Starting to cook sushi " + r.getName() + ".Out in " + rand() + " minutes!");
        } else {
            System.out.println("I can't cook that.");
            super.chef(r);
        }
    }
}

class Pasta extends Chef {
    @Override
    public void chef(Request r) {
        System.out.print("PastaChef: ");
        if(canMake(r, "pasta")) {
            System.out.println("Starting to cook pasta " + r.getName() + ".Out in " + rand() + " minutes!");
        } else {
            System.out.println("I can't cook that.");
            super.chef(r);
        }
    }
}

class Burguer extends Chef {
    @Override
    public void chef(Request r) {
        System.out.print("BurguerChef: ");
        if(canMake(r, "burguer")) {
            System.out.println("Starting to cook burguer " + r.getName() + ".Out in " + rand() + " minutes!");
        } else {
            System.out.println("I can't cook that.");
            super.chef(r);
        }
    }
}

class Pizza extends Chef {
    @Override
    public void chef(Request r) {
        System.out.print("PizzaChef: ");
        if(canMake(r, "pizza")) {
            System.out.println("Starting to cook pizza " + r.getName() + ".Out in " + rand() + " minutes!");
        } else {
            System.out.println("I can't cook that.");
            super.chef(r);
        }
    }
}

class Dessert extends Chef {
    @Override
    public void chef(Request r) {
        System.out.print("DessertChef: ");
        if(canMake(r, "dessert")) {
            System.out.println("Starting to cook dessert " + r.getName() + ".Out in " + rand() + " minutes!");
        } else {
            System.out.println("I can't cook that.");
            super.chef(r);
        }
    }
}