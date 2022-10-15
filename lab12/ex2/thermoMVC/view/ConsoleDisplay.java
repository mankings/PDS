package mvc.thermoMVC.view;

import mvc.thermoMVC.model.Thermometer;
import mvc.thermoMVC.model.ThermometerListener;

/**
 * A thermometer that displays as a digital thermometer.
 */
public class ConsoleDisplay implements ThermometerListener {

    // The Unicode symbol for degrees
    private static final char DEGREE_SYMBOL = '\u00B0';

    /** The thermometer whose temperature is being displayed */
    protected Thermometer thermometer;

    /**
     * Creates a console-displayed thermometer
     * @param t the thermometer whose temperature is displayed
     */
    public ConsoleDisplay(Thermometer t) {
        thermometer = t;
    }

    /**
     * Create the string to display in the thermometer
     * @return the string to display in the thermometer
     */
    private String getDisplayString() {
        double celsius = (thermometer.getTemperature() - 32) / 1.8;
        double kelvin = celsius + 273.15;
        double scale = Math.pow(10, 1);

        double celsiusRounded = Math.round(celsius*scale)/scale;
        double kelvinRounded = Math.round(kelvin*scale)/scale;

        return ""+thermometer.getTemperature()+DEGREE_SYMBOL+"F = "+celsiusRounded+DEGREE_SYMBOL+"C = "+kelvinRounded+"K";
    }

    /**
     * Change the temperature displayed
     */
    @Override
    public void temperatureChanged() {
        System.out.println(getDisplayString());
    }
}