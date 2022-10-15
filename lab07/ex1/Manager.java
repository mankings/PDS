class Manager extends EmployeeDecorator{

    Manager(EmployeeInterface interf){ super(interf); }

    @Override
    public void work(){
        super.work();
        System.out.print("as Manager ");
    }

    public void manage(){
        System.out.print("\nI'm managing!");
    }
}