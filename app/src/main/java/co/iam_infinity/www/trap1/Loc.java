package co.iam_infinity.www.trap1;

/**
 * Created by ASUS on 3/14/2018.
 */

public class Loc {
    Float lat;
    Float lon;

    public Loc() {
    }

    public Loc(Float lat, Float lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLon() {
        return lon;
    }

    public void setLon(Float lon) {
        this.lon = lon;
    }
}
