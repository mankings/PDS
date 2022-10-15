package ex1;

import java.util.concurrent.TimeUnit;

public class Ex1 {
    public static void main(String[] args) throws InterruptedException {
        Product p1 = new Product(20000);
        p1.setDesc("Joelho do Mankings");
        Product p2 = new Product(6);
        p2.setDesc("Velhisse");
        Product p3 = new Product(499);
        p3.setDesc("Nota de 500€");
        Product p4 = new Product(30000);
        p4.setDesc("Wolkswagen Type 2");
        Product p5 = new Product(1);
        p5.setDesc("Atum do bom");

        Client c1 = new Client("Mankings Gordo");
        Client c2 = new Client("Arturito");
        Client c3 = new Client("Reis Rato do Esgoto");

        Manager m1 = new Manager("Pylance");
        m1.addProduct(p1);
        m1.addProduct(p2);
        m1.addProduct(p3);
        m1.addProduct(p4);
        m1.addProduct(p5);

        m1.startAuction(p1, 3);
		m1.startAuction(p2, 3);
        // não há leilão para a nota de 500
		m1.startAuction(p4, 3);
		m1.startAuction(p5, 3); // 3 segundos para cada leilão

        p1.addObserver(c1);
		c1.bid(p1, 50000); // mankings quer o seu joelho
		
		TimeUnit.SECONDS.sleep(1); // faltam 2 segundos
		p2.addObserver(c3);
		c3.bid(p2, 100000001); // reis quer a velhisse
		
		p3.addObserver(c3);
		c3.bid(p3, 499); // reis também quer a nota de 500 mas não há leilão para ela :(
		
		TimeUnit.SECONDS.sleep(1); // falta 1 segundo
		p4.addObserver(c1);
		c1.bid(p4, 30000); // mankings quer muito a sua pão de forma
	
		p5.addObserver(c2);
		c2.bid(p5, 2); // arturito quer o atum do bom
		
		TimeUnit.SECONDS.sleep(2); // acabou leilões
		p1.addObserver(c2);
		c2.bid(p1, 40000); // já vens tarde arturito
    }
}
