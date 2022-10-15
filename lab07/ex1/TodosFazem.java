import java.util.Calendar;
import java.util.Date;

public class TodosFazem {

    public static void main(String[] args) {

        EmployeeInterface e1 = new Employee("André");
        e1.start(new Date());
        e1.work();
        e1.terminate(new Date());

        System.out.println();

        TeamMember tm1 = new TeamMember(new Employee("Ines"));
        tm1.start(new Date());
        tm1.colaborate();

        TeamLeader tl1 = new TeamLeader(new Employee("Diogo"));
        tl1.start(new Date());
        tl1.plan();

        System.out.println();

        Manager m1 = new Manager(new Employee("João"));
        m1.start(new Date());
        m1.manage();
        m1.work();
        m1.terminate(new Date());



        EmployeeInterface e2 = new Employee("Rui");
        TeamMember tm2 = new TeamMember(new Employee("Luis"));
        TeamLeader tl2 = new TeamLeader(new Employee("Ana"));
        TeamLeader tl3 = new TeamLeader(e2);
        TeamLeader tl4 = new TeamLeader(tm2);
        Manager m2 = new Manager(e2);
        Manager m3 = new Manager(
                new TeamLeader(
                        new TeamMember(
                                new Employee("Bruna"))));

        EmployeeInterface lista[] = { e2, tm2, tl2, tl3, tl4, m2, m2 };
        for (EmployeeInterface e: lista)
            e.work();

        tm2.start(new Date());
        tm2.work();
        tm2.colaborate();
        tl2.plan();
        tm2.terminate(new Date());
        m2.manage();
        System.out.println("\n\n");
        System.out.println("End!");
    }
}