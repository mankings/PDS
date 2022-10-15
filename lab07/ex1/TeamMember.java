class TeamMember extends EmployeeDecorator{

    TeamMember(EmployeeInterface interf){ super(interf); }

    @Override
    public void work(){
        super.work();
        System.out.print("as TeamMember ");
    }

    public void colaborate(){
        System.out.print("\nIm colaborating!");
    }
}