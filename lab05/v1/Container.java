package v1;

/*
*   This java file contains the Container class and its methods.
    Container implementes the 'Factory Desgin Pattern' allowing us to check if the
    object we're trying to create is valid. Thus, if the object has valid attributes only then it's initialized.
    This saves memory because invalid objects are never created.
*/
public class Container {
    String type;                // Type of Container
    State state;                // State of the portion the given container can store
    Temperature temp;           // Temperature of the portion the given container can store
    Portion portion;            // Portion that the container is going to store

    /*
    *   -- Container Constructor --
        This is the contructor of the Container, although not used
    */
    public Container(Portion portion, String type, State state, Temperature temp) {
        this.type = type;
        this.state = state;
        this.temp = temp;
        this.portion = portion;
    }

    /*
    * -- toString Method --
       This method allows us to print all the atributes of a 'Container' object.
   */
    @Override
    public String toString() {
        return (this.type + " with portion = " + this.portion.toString());
    }

    /*
    * -- create Method --
        Method used to validate arguments and create 'Container' objects.
    */
    public static Container create(Portion portion) {
        Temperature temp = portion.getTemperature();    // Gets the temperature of the given portion
        State state = portion.getState();               // Gets the state of the given portion

        if (state == State.Liquid) {
            if (temp == Temperature.COLD) {
                return new Container(portion,"PlasticBottle", state, temp);

            } else if (temp == Temperature.WARM) {
                return new Container(portion,"TermicBottle", state, temp);

            } else {
                return null; // Input wasn't valid, so null is returned
            }

        } else if (state == State.Solid) {
            if (temp == Temperature.COLD) {
                return new Container(portion,"PlasticBag", state, temp);

            } else if (temp == Temperature.WARM) {
                return new Container(portion,"Tupperware", state, temp);

            } else {
                return null; // Input wasn't valid, so null is returned
            }

        }
        return null; // Input wasn't valid, so null is returned
    }
}