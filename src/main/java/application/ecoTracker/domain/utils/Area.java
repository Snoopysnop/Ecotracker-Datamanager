package application.ecoTracker.domain.utils;

import java.io.Serializable;

public class Area implements Serializable {
    
    private GPSCoordinates coordinates;
    private double radius;

    protected Area() {

    }

    public Area(GPSCoordinates coordinates, double radius) {
        this.coordinates = coordinates;
        this.radius = radius;
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
