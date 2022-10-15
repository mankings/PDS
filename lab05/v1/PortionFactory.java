package v1;

/*
*   This java file contains the PortionFactory class and its methods.
    PortionFactory implementes the 'Factory Desgin Pattern' allowing us to check if the
    object we're trying to create is valid. Thus, if the object has valid attributes only then it's initialized.
    This saves memory because invalid objects are never created.
*/

public class PortionFactory {
    /*
    * -- create Method --
        Method used to validate arguments and create 'Portion' objects.
    */
    public static Portion create (String food, Temperature temperature) {
        if (temperature == Temperature.COLD) {
            if (food == "Meat") {
                return new Meat("Tuna", temperature);
            } else if (food == "Beverage") {
                return new Drink("FruitJuice", temperature, "Orange");
            }
        } else if (temperature == Temperature.WARM) {
            if (food == "Meat") {
                return new Meat("Pork", temperature);
            } else if (food == "Beverage") {
                return new Drink("Milk", temperature);
            }
        }
        return null; // Input wasn't valid, so null is returned
    }
}