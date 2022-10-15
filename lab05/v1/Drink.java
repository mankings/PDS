package v1;

/*
*   This java file contains the Drink class and its methods.
    Since Drink "is" a Portion we implement the Portion Interface.
*/
class Drink implements Portion {
    String name;                    // Drink Name
    State state = State.Liquid;     // Drink State
    Temperature temp;               // Drink Temperature
    String fruit = null;            // Drink Flavour -- If Drink is FruitJuice it also has a flavour

    /*
     * -- Drink Constructor --
        This constructor version is for the drinks that aren't FruitJuice.
        So, this doesn't have the 'Drink Flavour' argument.
     */
    public Drink(String name, Temperature temp) {
        this.temp = temp;
        this.name = name;
    }

    /*
     * -- Drink Constructor --
        This constructor version is for the FruitJuice drink.
    */
    public Drink(String name, Temperature temp, String flavour) {
        this.temp = temp;
        this.name = name;
        this.fruit = flavour;
    }

    /*
    * -- toString Method --
       This method allows us to print all the atributes of a 'Drink' object.
       This method is mandatory because we implemented the Portion interface in this class.
   */
    @Override
    public String toString() {
        if (fruit == null) { // Checks if 'flavour' has been initialized in the constructor.
            return (this.name + ": Temperature " + this.temp + ", State " + this.state);
        } else { // If 'flavour' has a value
            return (this.name + ": " + this.fruit + ", Temperature " + this.temp + ", State " + this.state );
        }
    }

    /*
     * -- getTemperature() Method --
        This method returns the temperature of the given object.
        This method is mandatory because we implemented the Portion interface in this class.
    */
    public Temperature getTemperature() { return this.temp; }

    /*
     * -- getState() Method --
        This method returns the state of the given object.
        This method is mandatory because we implemented the Portion interface in this class.
    */
    public State getState() { return this.state; }
}