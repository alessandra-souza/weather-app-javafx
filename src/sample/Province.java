package sample;

import java.util.ArrayList;

public class Province extends Location {

    // Data Fields
    private City capital;
    private ArrayList<City> cities;

    // Constructors
    public Province(){}

    public Province(String name){
        super(0,0,name,0);
        this.capital = null;
        this.cities = new ArrayList<City>();
    }

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
