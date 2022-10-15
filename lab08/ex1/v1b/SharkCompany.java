import java.util.List;
import java.util.ArrayList;

public class SharkCompany {
    public static void main(String[] args) {
        Person[] persons = { new Person("Maria Silva"), new Person("Manuel Pereira"), new Person("Aurora Machado"), new Person("Augusto Lima") };

        Company shark = new Company();
        Company.user = User.OWNER;

        shark.admitPerson(persons[0], 1000);
        shark.admitPerson(persons[1], 900);
        shark.admitPerson(persons[2], 1200);
        shark.admitPerson(persons[3], 1100);
        List<Employee> sharkEmps = shark.employees();

        for (Employee e : sharkEmps)
            System.out.println(e.getSalary());
        shark.paySalaries(1);

        for (Employee e : sharkEmps) {
            e.getBankAccount().withdraw(500);
            System.out.println(e.getBankAccount().balance());
        }
    }
}