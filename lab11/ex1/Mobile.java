package ex1;

public class Mobile {
    private String processor;
    private float price;
    private float memory;
    private String camera;

    public Mobile(String proc, float price, float m, String c) {
        this.processor = proc;
        this.price = price;
        this.memory = m;
        this.camera = c;
    }
    
    public String getCamera() {
        return camera;
    }

    public float getPrice() {
        return price;
    }

    public float getMemory() {
        return memory;
    }

    public String getProcessor() {
        return processor;
    }
}
