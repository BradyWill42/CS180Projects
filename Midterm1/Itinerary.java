//Javadoc
/**
 * Midterm 1 -- Itinerary
 *
 * Contains Useful Calculations to help Book trips
 *
 * @author Brady Williams, L23
 *
 * @version Feb 20, 2024
 *
 */
public class Itinerary {

    private Booking bookingOne;
    private Booking bookingTwo;
    private Booking bookingThree;

    public Itinerary(Booking bookingOne, Booking bookingTwo, Booking bookingThree) {
        this.bookingOne = bookingOne;
        this.bookingTwo = bookingTwo;
        this.bookingThree = bookingThree;
    }

    public Itinerary() {
        bookingOne = null;
        bookingTwo = null;
        bookingThree = null;
    }

    public boolean addBooking(Booking booking) {
        if (bookingOne == null) {
            bookingOne = booking;
            return true;
        } else if (bookingTwo == null) {
            bookingTwo = booking;
            return true;
        } else if (bookingThree == null) {
            bookingThree = booking;
            return true;
        } else {
            return false;
        }
    }   

    public boolean removeBooking(Booking booking) {
        if (bookingOne != null && 
            bookingOne.getAirlineName().equals(booking.getAirlineName()) && 
            bookingOne.getOriginAirport().equals(booking.getOriginAirport()) && 
            bookingOne.getDestinationAirport().equals(booking.getDestinationAirport()) &&
            bookingOne.getTravelClass().equals(booking.getTravelClass())) {
                bookingOne = null;
                return true;
        } else if (bookingTwo != null && 
            bookingTwo.getAirlineName().equals(booking.getAirlineName()) && 
            bookingTwo.getOriginAirport().equals(booking.getOriginAirport()) && 
            bookingTwo.getDestinationAirport().equals(booking.getDestinationAirport()) &&
            bookingTwo.getTravelClass().equals(booking.getTravelClass())) {
                bookingTwo = null;
                return true;
        } else if (bookingThree != null && 
            bookingThree.getAirlineName().equals(booking.getAirlineName()) && 
            bookingThree.getOriginAirport().equals(booking.getOriginAirport()) && 
            bookingThree.getDestinationAirport().equals(booking.getDestinationAirport()) &&
            bookingThree.getTravelClass().equals(booking.getTravelClass())) {
                bookingThree = null;
                return true;
        } else {
            return false;
        }
    }

    public int getOpenings() {
        int numNull = 0;
        if (bookingOne == null) {
            numNull++;
        }
        if (bookingTwo == null) {
            numNull++;
        }
        if (bookingThree == null) {
            numNull++;
        }
        return numNull;
    }

    public int getBookingCount() {
        int nonNull = 0;
        if (bookingOne != null) {
            nonNull++;
        }
        if (bookingTwo != null) {
            nonNull++;
        }
        if (bookingThree != null) {
            nonNull++;
        }
        return nonNull;
    }

    public double calculateAverageDistanceTraveled() {
        double sumMiles = 0;
        if (bookingOne != null) {
            sumMiles += bookingOne.getDistanceInMiles();
        }
        if (bookingTwo != null) {
            sumMiles += bookingTwo.getDistanceInMiles();
        }
        if (bookingThree != null) {
            sumMiles += bookingThree.getDistanceInMiles();
        }
        return sumMiles / getBookingCount();
    }

    public boolean upgradeTravelClass() {

        if ((bookingOne != null && bookingOne.getTravelClass().equals("First")) || 
            (bookingTwo != null && bookingTwo.getTravelClass().equals("First")) || 
            (bookingThree != null && bookingThree.getTravelClass().equals("First"))) {
                return false;
        }

        if (bookingOne != null) {
            if (bookingOne.getTravelClass().equals("Economy")) {
                bookingOne.setTravelClass("Premium Economy");
            } else if (bookingOne.getTravelClass().equals("Premium Economy")) {
                bookingOne.setTravelClass("First");
            } else {
                return false;
            }
        }
        if (bookingTwo != null) {
            if (bookingTwo.getTravelClass().equals("Economy")) {
                bookingTwo.setTravelClass("Premium Economy");
            } else if (bookingTwo.getTravelClass().equals("Premium Economy")) {
                bookingTwo.setTravelClass("First");
            } else {
                return false;
            }
        }
        if (bookingThree != null) {
            if (bookingThree.getTravelClass().equals("Economy")) {
                bookingThree.setTravelClass("Premium Economy");
            } else if (bookingThree.getTravelClass().equals("Premium Economy")) {
                bookingThree.setTravelClass("First");
            } else {
                return false;
            }
        }
        return true;
    }
}