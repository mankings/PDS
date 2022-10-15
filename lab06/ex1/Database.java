package ex1;

import java.util.Vector;

public class Database {
    // Data elements
    private Vector<Employee> employees; // Stores the employees

    public Database() {
        employees = new Vector<>();
    }

    public boolean addEmployee(Employee employee) {
        // Code to add employee
        return employees.add(employee);
    }

    public void deleteEmployee(long emp_num) {
        // Code to delete employee
        for(Employee e : employees)
            if(e.getEmpNum() == emp_num) {
                employees.remove(e);
                break;
            }
    }

    public Employee[] getAllEmployees() {
        // Code to retrieve collection
        return (Employee[]) employees.toArray();
    }
}