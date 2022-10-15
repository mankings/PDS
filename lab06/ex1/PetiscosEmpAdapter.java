package ex1;

public class PetiscosEmpAdapter implements EmployeeAdapter {
    private Empregado employee;

    public PetiscosEmpAdapter(Empregado employee) {
        this.employee = employee;
    }

    @Override
    public String getName() {
        return employee.nome() + ' ' + employee.apelido();
    }

    @Override
    public long getEmpNum() {
        return employee.codigo();
    }

    @Override
    public double getSalary() {
        return employee.salario();
    }
}
