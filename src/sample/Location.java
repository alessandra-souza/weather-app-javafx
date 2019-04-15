package sample;

public abstract class Location {

    // Data fields
    private double lon;
    private double lat;
    private String name;
    private int population;

    // Constructors
    public Location(){}

    public Location(double lon, double lat, String name, int population) {
        super();
        this.lon = lon;
        this.lat = lat;
        this.name = name;
        this.population = population;
    }

    // Getters and setters
    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

}

