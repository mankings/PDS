public class Employee {
    private Person p;
    private double salary;
    private BankAccount bankAccount;

    public Employee(Person n, double s) {
        this.p = n;
        salary = s;
        bankAccount = new BankAccountImpl("PeDeMeia", 0);
    }
    public double getSalary() {
        return salary;
    }

    public BankAccount getBankAccount() {
        return new BankAccProxy(bankAccount);
    }
}