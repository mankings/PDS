public class Plugin2 implements IPlugin {
    @Override
    public void fazQualquerCoisa() {
        System.out.println("Contando até deixar de contar.");
        int mankings = (int) (Math.random() * 100);
        for(int i = 0; i < mankings; i++) {
            System.out.println(i);
        }
    }
}
