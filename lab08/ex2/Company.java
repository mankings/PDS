import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Company {
    public static User user;
    private List<Employee> emps = new ArrayList<Employee>();


    public void admitPerson(Employee e, double salary) {
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