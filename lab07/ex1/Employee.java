import java.util.Date;

public class Employee implements EmployeeInterface {
     private String name;

     public Employee(String name){
          this.name = name;
     }

     @Override
     public void start(Date d){
          System.out.print("\n" + name + " started on " + d.toString());
     }

     @Override
     public void terminate(Date d){
          System.out.print("\n" + name + " stoped on " + d.toString());
     }

     @Override
     public void work() {
          System.out.print("\n" + name + " worked ");
     }
}