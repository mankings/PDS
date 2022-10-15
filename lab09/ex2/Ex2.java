package ex2;

public class Ex2 {
    public static void main(String[] args) {
        Chef MankingsDinner = new Sushi().setSucessor(
                new Pasta().setSucessor(new Burguer().setSucessor(new Pizza().setSucessor(new Dessert()))));

        System.out.println("\nCan i please get a veggie burger?");
        Request r1 = new Request("veggie burger", "burger");
        MankingsDinner.chef(r1);

        System.out.println("\nCan i please get a Pasta Carbonara?");
        Request r2 = new Request("Pasta Carbonara", "pasta");
        MankingsDinner.chef(r2);

        System.out.println("\nCan i please get a PLAIN pizza, no toppings?");
        Request r3 = new Request("PLAIN pizza, no toppings", "pizza");
        MankingsDinner.chef(r3);

        System.out.println("\nCan i please get a nigiri and sashimi?");
        Request r4 = new Request("nigiri and sashimi", "sushi");
        MankingsDinner.chef(r4);

        System.out.println("\nCan i please get a salad with tuna?");
        Request r5 = new Request("salad with tuna", "salad");
        MankingsDinner.chef(r5);

        System.out.println("\nCan i please get a strawberry ice cream and waffles dessert?");
        Request r6 = new Request("strawberry ice cream and waffles dessert", "dessert");
        MankingsDinner.chef(r6);
    }
}
