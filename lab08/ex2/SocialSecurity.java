import java.util.ArrayList;
import java.util.List;

public class SocialSecurity {
    private List<Employee> EmpArray;

    public SocialSecurity() {
        EmpArray = new ArrayList<Employee>();
    }

    public void regist(Employee e) {
        if (!EmpArray.contains(e)) EmpArray.add(e);
        return;
    }

    public List getSocialSecurityRegist() {
        return EmpArray;
    }
}