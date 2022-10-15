import java.util.ArrayList;
import java.util.List;

public class Parking {
    private List<Employee> EmpArray;

    public Parking() {
        EmpArray = new ArrayList<Employee>();
    }

    public void allow(Employee e, List<Employee> CompEmp) {
        int avg = 0;
        for (Employee emp : CompEmp) {
            avg += emp.getSalary();
        }
        avg = avg/CompEmp.size();

        if ( (!EmpArray.contains(e)) && (e.getSalary() > avg) ) EmpArray.add(e);
        else if ( (!EmpArray.contains(e)) && (e.getSalary() <= avg) ) {
            System.out.println("low salary");
        }
        else System.out.println("already registered");
    }

    public List getParking() {
        return EmpArray;
    }
}