public class Employee {
    private Person p;
    private double salary;
    private BankAccount bankAccount;
    private Card card;

    public Employee(Person n, double s, Card card) {
        this.p = n;
        this.card =  card;
        salary = s;
        bankAccount = new BankAccountImpl("PeDeMeia", 0);
    }
    public double getSalary() {
        return salary;
    }

    public Person getPerson() {
        return p;
    }

    public BankAccount getBankAccount() {
        return new BankAccProxy(bankAccount);
    }
}