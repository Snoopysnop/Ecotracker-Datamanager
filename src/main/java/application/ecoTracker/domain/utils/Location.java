package application.ecoTracker.domain.utils;

import java.io.Serializable;

public class Location implements Serializable {
    
    private GPSCoordinates coordinates;
    private double radius;

    protected Location() {

    }

    public GPSCoordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(GPSCoordinates coordinates) {
        this.coordinates = coordinates;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }


    

}
