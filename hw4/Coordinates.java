/**
 * Coordinates class containing Coordinates information.
 * @author slee3245
 * @version 1.0
 */
public class Coordinates {

    private final double latitude;
    private final double longitude;

    /**
     * Coordinates constructor.
     * @param latitude latitude info.
     * @param longitude longitude info.
     */
    public Coordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Getter for latitude.
     * @return latitude.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Getter for longitude.
     * @return longitude.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Concrete equals method that overrides Object class.
     * @param other object to be compared with.
     * @return true if object are equal to each other.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Coordinates)) {
            return false;
        }
        Coordinates that = (Coordinates) other;
        return Double.compare(that.latitude, latitude) == 0
                && Double.compare(that.longitude, longitude) == 0;
    }

    /**
     * toString method that overrides Object class.
     * @return string representation of coordinates (latitude, longitude).
     */
    @Override
    public String toString() {
        return String.format("latitude: %.2f, longitude: %.2f"
                , latitude, longitude);
    }

}
