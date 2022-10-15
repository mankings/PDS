import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Company {
    public static User user;
    private List<Employee> emps = new ArrayList<>();

    public void admitPerson(Person name, double salary) {
        Employee e = new Employee(name, salary);
        emps.add(e);
    }

    public void paySalaries(int month) {
        for (Employee e : emps) {
            BankAccount ba = e.getBankAccount();
            ba.deposit(e.getSalary());
        }
    }

    public List<Employee> employees() {
        return Collections.unmodifiableList(emps);
    }
}