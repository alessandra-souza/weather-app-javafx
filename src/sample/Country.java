package sample;

import java.util.ArrayList;

public class Country extends Location {

    // Data fields
    private ArrayList<Province> provinces;
    private City capital;
    private String iso2;


    // Constructors
    public Country(){}

    public Country(ArrayList<Province> provinces, City capital, String iso2) {
        super();
        this.provinces = provinces;
        this.capital = capital;
        this.iso2 = iso2;
    }

    // Getters and setters
    public ArrayList<Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(ArrayList<Province> provinces) {
        this.provinces = provinces;
    }

    public City getCapital() {
        return capital;
    }

    public void setCapital(City capital) {
        this.capital = capital;
    }

    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

}

