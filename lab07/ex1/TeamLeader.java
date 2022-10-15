class TeamLeader extends EmployeeDecorator{

    TeamLeader(EmployeeInterface interf){ super(interf); }

    @Override
    public void work(){
        super.work();
        System.out.print("as TeamLeader ");
    }

    public void plan(){
        System.out.print("\nI'm planing!");
    }
}