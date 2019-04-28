package sample;

import java.io.InputStream;
import java.net.URL;
import javax.json.*;
import java.util.*;

public class Weather {

    private double temp;
    private double tempMin;
    private double tempMax;
    private int pressure;
    private int humidity;
    private double windSpeed;
    private int windDeg;
    private double windGust;
    private double lat;
    private double lon;
    private int visibility;
    private String icon;
    private String description;
    private String country;

    // getters and setters
    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(int windDeg) {
        this.windDeg = windDeg;
    }

    public double getWindGust() {
        return windGust;
    }

    public void setWindGust(double windGust) {
        this.windGust = windGust;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    // Methods
    public static Weather currWeather(String selCity){

        Weather myWeather = new Weather();

        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + selCity
                    + "&units=metric&appid=122a9b7457682449757326422f77a195");

            try (InputStream is = url.openStream(); JsonReader rdr = Json.createReader(is)) {
                JsonObject obj = rdr.readObject();
                System.out.println("Current weather in: " + obj.getString("name"));
                System.out.println("------------------------------");

                JsonArray results = obj.getJsonArray("weather");
                JsonObject weather = results.getValuesAs(JsonObject.class).get(0);

                List<String> icon = new ArrayList<String>();
                icon.add(results.getJsonObject(0).getString("icon"));

                List<String> description = new ArrayList<>();
                description.add(results.getJsonObject(0).getString("description"));

                JsonString country = (JsonString) obj.getJsonObject("sys").get("country");

                // fields
                JsonNumber 	temp = (JsonNumber) obj.getJsonObject("main").get("temp"),
                        tempMin = (JsonNumber) obj.getJsonObject("main").get("temp_min"),
                        tempMax = (JsonNumber) obj.getJsonObject("main").get("temp_max"),
                        pressure = (JsonNumber) obj.getJsonObject("main").get("pressure"),
                        humidity = (JsonNumber) obj.getJsonObject("main").get("humidity"),
                        windSpeed = (JsonNumber) obj.getJsonObject("wind").get("speed"),
                        windDeg = (JsonNumber) obj.getJsonObject("wind").get("deg"),
//                        windGust = (JsonNumber) obj.getJsonObject("wind").get("gust"),
                        lat = (JsonNumber) obj.getJsonObject("coord").get("lat");
//                        country = (JsonNumber) obj.getJsonObject("sys").get("country");
                //visibility = (JsonNumber) obj.getString("visibility");


                // assign values to myWeather obj
                myWeather.temp = temp.doubleValue();
                myWeather.tempMin = tempMin.doubleValue();
                myWeather.tempMax = tempMax.doubleValue();
                myWeather.pressure = pressure.intValue();
                myWeather.humidity = humidity.intValue();
                myWeather.lat = lat.doubleValue();
                myWeather.windSpeed = windSpeed.doubleValue();
                myWeather.windDeg = windDeg.intValue();
//                myWeather.windGust = windGust.doubleValue();
                myWeather.icon = icon.toString();
                myWeather.description = description.toString();
                myWeather.country = country.toString();

                System.out.println("Temperature: " + myWeather.getTemp() +
                        "\nMin temp: " + myWeather.getTempMin() +
                        "\nMax temp: " + myWeather.getTempMax() +
                        "\nPressure: " + myWeather.getPressure() +
                        "\nHumidity: " + myWeather.getHumidity() +
                        "\nLatitude: " + myWeather.getLat() +
                        "\nWind speed: " + myWeather.getWindSpeed() +
                        "\nWind direction: " + myWeather.getWindDeg() +
                        "\nIcon: " + myWeather.getIcon() +
                        "\nDescription: " + myWeather.getDescription() +
                        "\nCountry: " + myWeather.getCountry() +
                        "\nWind gust: " + myWeather.getWindGust());
            }

        } catch (Exception e) {
            System.out.println("Exception: "+e.getMessage());
        }

        return myWeather;
    }

}

