package ex1;

public class SweetsEmpAdapter implements EmployeeAdapter {
    private Employee employee;

    public SweetsEmpAdapter(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String getName() {
        return employee.getName();
    }

    @Override
    public long getEmpNum() {
        return employee.getEmpNum();
    }

    @Override
    public double getSalary() {
        return employee.getSalary();
    }
}
