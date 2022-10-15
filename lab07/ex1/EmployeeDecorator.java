import java.util.Date;

public abstract class EmployeeDecorator implements EmployeeInterface {
    EmployeeInterface interf;

    public EmployeeDecorator(EmployeeInterface interf) {
        this.interf = interf;
    }

    public void start(Date date) {
        this.interf.start(date);
    }

    public void terminate(Date date) {
        this.interf.terminate(date);
    }

    public void work() {
        this.interf.work();
    }
}