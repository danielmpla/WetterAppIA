package de.hszg.fei.durzo.wetterappia.repo;

/**
 * @author Daniel Müssig
 */
public class Location {
    private String location;
    private String locationCode;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    @Override
    public String toString() {
        return this.location;
    }
}
