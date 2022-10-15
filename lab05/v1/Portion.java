package v1;

/*
*   This java file contains the Portion interface.
    This interface is implemented in classes like Drink and Meat.
*/
public interface Portion {
        public Temperature getTemperature();    // Mandatory getTemperature() method.
        public State getState();                // Mandatory getState() method.
        public String toString();               // Mandatory toString() method.
}
