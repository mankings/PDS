package ex1;

import java.util.stream.Stream;

import javax.xml.crypto.Data;

public class SweetsDBAdapter implements DatabaseAdapter {
    private Database database;

    public SweetsDBAdapter() {
        this.database = new Database();
    }

    public SweetsDBAdapter(Database database) {
        this.database = database;
    }

    @Override
    public void addEmployee(EmployeeAdapter employee) {
        database.addEmployee(new Employee(employee.getName(), employee.getEmpNum(), employee.getSalary()));
    }

    @Override
    public void removeEmployee(long empNum) {
        database.deleteEmployee(empNum);
    }

    @Override
    public boolean isEmployee(long empNum) {
        for(EmployeeAdapter emp : getEmployees())
            if(emp.getEmpNum() == empNum)
                return true;
        return false;
    }

    @Override
    public EmployeeAdapter[] getEmployees() {
        Employee[] employees = database.getAllEmployees();
        SweetsEmpAdapter[] employeesAdapted = Stream.of(employees).map(SweetsEmpAdapter::new).toArray(SweetsEmpAdapter[]::new);
        return employeesAdapted;
    }
}
