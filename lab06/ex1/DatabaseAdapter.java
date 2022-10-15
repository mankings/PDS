package ex1;

public interface DatabaseAdapter {
    public void addEmployee(EmployeeAdapter employee);

    public void removeEmployee(long empNum);

    public boolean isEmployee(long empNum);

    public EmployeeAdapter[] getEmployees();
}
