package obsermodel;

import java.util.Map;

public class CurrentConditionDisplay implements Observer, DisplayElement {
    private double temp;

    private double humidity;

    private double pressure;

    private Subject weatherData;

    public CurrentConditionDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("Current condition: temperature -> " + temp + "\thumidity -> " + humidity + "\tpressure -> " + pressure);
    }

    @Override
    public void update(Map map) {
        temp = (double) map.get("temp");
        humidity = (double) map.get("humidity");
        pressure = (double) map.get("pressure");

        display();
    }

    @Override
    public void unsubscribe() {
        weatherData.registerObserver(this);
    }
}
