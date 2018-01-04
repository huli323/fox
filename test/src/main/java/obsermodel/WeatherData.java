package obsermodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WeatherData implements Subject {
    private ArrayList<Observer> observers;

    private double temp;

    private double humidity;

    private double pressure;

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        Map map = new HashMap();
        map.put("temp", temp);
        map.put("humidity", humidity);
        map.put("pressure", pressure);
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).update(map);
        }
    }

    public WeatherData(){
        observers = new ArrayList();
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getTemp() {
        return temp;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getPressure() {
        return pressure;
    }
}
