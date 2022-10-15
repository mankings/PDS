import java.util.ArrayList;
import java.util.List;

public class Insurance {
    private List<Employee> EmpArray;

    public Insurance() {
        EmpArray = new ArrayList<Employee>();
    }

    public void regist(Employee e) {
        if (!EmpArray.contains(e)) EmpArray.add(e);
        return;
    }

    public List getInsuranceRegist() {
        return EmpArray;
    }
}