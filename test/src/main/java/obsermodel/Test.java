package obsermodel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Test {
    public static void main(String[] args) {
//        WeatherData weatherData = new WeatherData();
//        CurrentConditionDisplay c1 = new CurrentConditionDisplay(weatherData);
//        CurrentConditionDisplay c2 = new CurrentConditionDisplay(weatherData);
//
//        weatherData.setHumidity(12.2);
//        weatherData.setPressure(123);
//        weatherData.setTemp(30.2);
//
//        weatherData.notifyObservers();


        Calendar c = new GregorianCalendar();
        System.out.println(c.getTime());
        System.out.println(c.getCalendarType());
    }
}