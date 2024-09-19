import java.util.ArrayList;

/**
 * HW-09 -- AmusementPark
 *
 * This program documents
 * information regarding a park
 * and manages its rides and land
 *
 * @author Brady Williams, L23
 *
 * @version Mar 16, 2024
 *
 */
public class AmusementPark implements Park {
    private double admissionCost;
    private boolean arcade;
    private boolean bowling;
    private boolean indoor;
    private double land;
    private String name;
    private boolean outdoor;
    private ArrayList<Ride> rides;
    private boolean[] seasons = new boolean[4];
    
    public AmusementPark(String name, 
        double admissionCost, 
        double land, 
        ArrayList<Ride> rides, 
        boolean indoor, 
        boolean outdoor,
        boolean arcade, 
        boolean bowling, 
        boolean[] seasons) {
        this.name = name;
        this.admissionCost = admissionCost;
        this.land = land;
        this.rides = rides;
        this.indoor = indoor;
        this.outdoor = outdoor;
        this.arcade = arcade;
        this.bowling = bowling;
        this.seasons = seasons;
    }

    public void addRide(Ride ride) throws WrongRideException {
        if (ride instanceof Rollercoaster) {
            rides.add(ride);
        } else  {
            throw new WrongRideException("An amusement park can only have rollercoaster rides!");
        }
    }

    public void close() {
        this.name = "";
        this.admissionCost = 0;
        this.land = 0;
        this.rides = null;
        this.seasons = null;
        this.indoor  = false;
        this.outdoor = false;
        this.arcade = false;
        this.bowling = false;
    }

    public void enlarge(double addedLand, 
        double maxLand, 
        boolean addedIndoor, 
        boolean addedOutdoor) throws SpaceFullException {
        if (land + addedLand < maxLand) {
            land = land + addedLand;
            if (addedIndoor) {
                indoor = true;
            } 
            if (addedOutdoor) {
                outdoor = true;
            }
        } else {
            throw new SpaceFullException("There is no more land to use for this park!");
        }
    }

    public double getAdmissionCost() {
        return admissionCost;
    }

    public double getLand() {
        return land;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Ride> getRides() {
        return rides;
    }

    public boolean[] getSeasons() {
        return seasons;
    }

    public boolean isArcade() {
        return arcade;
    }

    public boolean isBowling() {
        return bowling;
    }

    public boolean isIndoor() {
        return indoor;
    }

    public boolean isOutdoor() {
        return outdoor;
    }

    public void modifyRide(Ride ride, 
        String newName, 
        String newColor, 
        int newMinHeight, 
        int newMaxRiders, 
        boolean simulated) {
        for (int i = 0; i < rides.size(); i++) {
            if (rides.get(i).equals(ride)) {
                rides.get(i).setName(newName);
                rides.get(i).setColor(newColor);
                rides.get(i).setMinHeight(newMinHeight);
                rides.get(i).setMaxRiders(newMaxRiders);
                break;
            }
        }
    }

    public void removeRide(Ride ride) {
        for (int i = 0; i < rides.size(); i++) {
            if (rides.get(i).equals(ride)) {
                rides.remove(i);
                break;
            }
        }
    }

    public void setAdmissionCost(double admissionCost) {
        this.admissionCost = admissionCost;
    }

    public void setArcade(boolean arcade) {
        this.arcade = arcade;
    }

    public void setBowling(boolean bowling) {
        this.bowling = bowling;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSeasons(boolean[] seasons) {
        this.seasons = seasons;
    }



}
