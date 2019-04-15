package sample;

import java.util.ArrayList;

public class Province extends Location {

    // Data Fields
    private City capital;
    private ArrayList<City> cities;

    // Constructors
    public Province(){}

    public Province(City capital, ArrayList<City> cities) {
        super();
        this.capital = capital;
        this.cities = cities;
    }

    // Getters and setters
    public City getCapital() {
        return capital;
    }

    public void setCapital(City capital) {
        this.capital = capital;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }

}
