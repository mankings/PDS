public class BankAccProxy implements BankAccount {
    BankAccount acc;

    BankAccProxy(BankAccount account) {
        this.acc = account;
    }

    @Override public void deposit(double amount) {
        this.acc.deposit(amount);
    }

    @Override public boolean withdraw(double amount) {
        if (Company.user == User.OWNER) {
            return this.acc.withdraw(amount);
        }
        else {
            return false;
        }
    }

    @Override public double balance() {
        if (Company.user == User.OWNER) {
            return this.acc.balance();
        }
        else {
            return 0;
        }
    }
}