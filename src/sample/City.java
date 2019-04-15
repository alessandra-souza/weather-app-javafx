package sample;

public class City extends Location implements Sailable, Skiiable, Bikable, Flyable {

    // Data fields
    private int capital;
    private final static int PRIMARY = 1;
    private final static int ADMIN = 2;
    private final static int NONE = 3;
    private boolean isSailable = false;
    private boolean isSkiiable = false;
    private boolean isBikable = false;
    private boolean isFlyable = false;

    // Getters and setters
    public boolean isSailable() {
        return isSailable;
    }

    public void setSailable(boolean isSailable) {
        this.isSailable = isSailable;
    }

    public boolean isSkiiable() {
        return isSkiiable;
    }

    public void setSkiiable(boolean isSkiiable) {
        this.isSkiiable = isSkiiable;
    }

    public boolean isBikable() {
        return isBikable;
    }

    public void setBikable(boolean isBikable) {
        this.isBikable = isBikable;
    }

    public boolean isFlyable() {
        return isFlyable;
    }

    public void setFlyable(boolean isFlyable) {
        this.isFlyable = isFlyable;
    }

    // Methods
    @Override
    public String checkSkiiable(Weather weather) {
        if (Double.valueOf(weather.getLat()) != null) {
            if (Double.valueOf(weather.getLat()) < -35 || Double.valueOf(weather.getLat()) > 45.0) {
                this.isSkiiable = true;
                return "skiiable";
            } else {
                return "not skiiable";
            }
        } else {
            return "no information about skiing";
        }
    }

    @Override
    public String checkSailable(Weather weather) {
        if (Double.valueOf(weather.getWindGust()) != null) {
            if ( (Double.valueOf(weather.getWindSpeed()) <= 5 && Double.valueOf(weather.getWindSpeed()) >= 20) && Double.valueOf(weather.getWindGust()) < 25 ) {
                this.isSailable = true;
                return "sailable";
            } else {
                return "not sailable";
            }
        } else {
            return "no information about sailing";
        }
    }

    @Override
    public String checkFlyiable(Weather weather) {
        if (Integer.valueOf(weather.getVisibility()) != null && Double.valueOf(weather.getWindSpeed()) != null) {
            if ( Integer.valueOf(weather.getVisibility()) > 5000 && Double.valueOf(weather.getWindSpeed()) < 30 ) {
                this.isFlyable = true;
                return "bikable";
            } else {
                return "not bikable";
            }
        } else {
            return "no information about biking";
        }
    }

    @Override
    public String checkBikable(Weather weather) {
        if (Double.valueOf(weather.getTemp()) != null) {
            if ( Double.valueOf(weather.getTemp()) >= -5 && Double.valueOf(weather.getTemp()) <= 30 ) {
                this.isBikable = true;
                return "bikable";
            } else {
                return "not bikable";
            }
        } else {
            return "no information about biking";
        }
    }

}
