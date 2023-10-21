package application.ecoTracker.domain.utils;

import java.io.Serializable;

public class GPSCoordinates implements Serializable{

    private double longitude;
    private double latitude;

    protected GPSCoordinates() {

    }

    public GPSCoordinates(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "(" + longitude + ", " + latitude + ")";
    }

    
    
    
}
