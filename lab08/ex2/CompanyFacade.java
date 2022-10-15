import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CompanyFacade {
    private Company company;
    private Insurance insurance;

    private List<Card> cards;
    private SocialSecurity socialsecurity;
    private Parking parking;

    public CompanyFacade() {
        company = new Company();
        insurance = new Insurance();
        socialsecurity = new SocialSecurity();
        parking = new Parking();
        cards = new ArrayList<Card>();
    }


    public void admitEmployee(Person p, double salary) {
        System.out.println(p.getName());
        Card card = new Card(p.getName());
        Employee e = new Employee(p, salary, card);
        this.company.admitPerson(e, salary);
        this.insurance.regist(e);
        this.socialsecurity.regist(e);
        this.parking.allow(e, this.company.employees());
        System.out.println();
    }


    public void paySalaries(int month) {
        this.company.paySalaries(month);
    }


    public List<Employee> employees() {
        return this.company.employees();
    }
}
