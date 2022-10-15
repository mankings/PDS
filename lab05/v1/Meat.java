package v1;

/*
*   This java file contains the Meat class and its methods.
    Since Meat "is" a Portion we implement the Portion Interface.
*/
class Meat implements Portion {
    String name;                    // Meat name
    State state = State.Solid;      // Meat state
    Temperature temp;               // Meat temperature

    /*
     * -- Meat Constructor --
        This creates a 'Meat' object
     */
    public Meat(String name, Temperature temp) {
        this.temp = temp;
        this.name = name;
    }

    /*
      * -- getTemperature() Method --
         This method returns the temperature of the given object.
         This method is mandatory because we implemented the Portion interface in this class.
     */
    public Temperature getTemperature() {
        return this.temp;
    }

    /*
     * -- getState() Method --
        This method returns the state of the given object.
        This method is mandatory because we implemented the Portion interface in this class.
    */
    public State getState() {
        return this.state;
    }

    /*
    * -- toString Method --
       This method allows us to print all the atributes of a 'Meat' object.
       This method is mandatory because we implemented the Portion interface in this class.
   */
    @Override
    public String toString() { return (this.name + ": Temperature " + this.temp + ", State " + this.state ); }


}