//Javadoc
/**
 * Midterm 1 -- Booking
 *
 * Document the specific details associated with a given booking.
 *
 * @author Brady Williams, L23
 *
 * @version Feb 20, 2024
 *
 */
public class Booking {

    private String airlineName;
    private String originAirport;
    private String destinationAirport;
    private String travelClass;
    private double distanceInMiles;
    private boolean layover;

    public Booking(String airlineName, String originAirport, String destinationAirport, 
        String travelClass, double distanceInMiles, boolean layover) {
            
            this.airlineName = airlineName;
            this.originAirport = originAirport;
            this.destinationAirport = destinationAirport;
            this.travelClass = travelClass;
            this.distanceInMiles = distanceInMiles;
            this.layover = layover;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public String getOriginAirport() {
        return originAirport;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public String getTravelClass() {
        return travelClass;
    }

    public double getDistanceInMiles() {
        return distanceInMiles;
    }

    public boolean hasLayover() {
        return layover;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }
    
    public void setOriginAirport(String originAirport) {
        this.originAirport = originAirport;
    }

    public void setTravelClass(String travelClass) {
        this.travelClass = travelClass;
    }

    public void setDistanceInMiles(double distanceInMiles) {
        this.distanceInMiles = distanceInMiles;
    }

    public void setLayover(boolean layover) {
        this.layover = layover;
    }

    public String toString() {
        return String.format("Booking<airlineName=%s, originAirport=%s, destinationAirport=%s, travelClass=%s, distanceInMiles=%.2f, layover=%b>", airlineName, originAirport, destinationAirport, travelClass, distanceInMiles, layover);
    }

}