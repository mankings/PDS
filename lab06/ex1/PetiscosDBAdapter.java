package ex1;

import java.util.Arrays;
import java.util.stream.Stream;

public class PetiscosDBAdapter implements DatabaseAdapter  {
    private Registos database;

    public PetiscosDBAdapter() {
        database = new Registos();
    }

    public PetiscosDBAdapter(Registos database) {
        this.database = new Registos();
    }

    @Override
    public void addEmployee(EmployeeAdapter employee) {
        String names[] = employee.getName().split(" ");
        String name = String.join(" ", Arrays.copyOfRange(names, 0, names.length/2));
        String surname = String.join(" ", Arrays.copyOfRange(names, names.length/2, names.length));
        database.insere(new Empregado(name, surname, (int) employee.getEmpNum(), employee.getSalary()));
    }

    @Override
    public void removeEmployee(long empNum) {
        database.remove((int) empNum);
    }

    @Override
    public boolean isEmployee(long empNum) {
        return database.isEmpregado((int) empNum);
    }

    @Override
    public EmployeeAdapter[] getEmployees() {
        Empregado[] employees = database.listaDeEmpregados().toArray(new Empregado[0]);
        PetiscosEmpAdapter[] employeesAdapted = Stream.of(employees).map(PetiscosEmpAdapter::new)
        .toArray(PetiscosEmpAdapter[]::new);

        return employeesAdapted;

    }
}
