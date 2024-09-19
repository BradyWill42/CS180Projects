//Javadoc
/**
 * Project 3 - Researcher
 *
 * The class that contains 
 * ways to get information about the   
 * researchers for this scp project
 * 
 * @author Brady Williams, L23
 *
 * @version Mar 25, 2024
 *
 */
public class Researcher {
    private boolean active;
    private char classification;
    private int clearance;
    private int idNumber;
    private String name;

    public Researcher(BadDataException e) {
        this.active = false;
        this.classification = '\0';
        this.clearance = 0;
        this.idNumber = 0;
        this.name = null;
    }

    public Researcher(String data) throws BadDataException {
        String currentStr = "";
        int counter = 0;
        String activeStr = "";
        String classStr = "";
        String clearStr = "";
        String idStr = "";
        String nameStr = "";


        for (int i = 0; i < data.length(); i++) {
            currentStr = data.substring(i, i + 1);
            if (currentStr.equals(",")) {
                counter++;
                continue;
            } 

            switch (counter) {
                case 0: 
                    nameStr += currentStr;
                    break;
                case 1: 
                    idStr += currentStr;
                    break;
                case 2: 
                    clearStr += currentStr;
                    break;
                case 3: 
                    classStr += currentStr;
                    break;
                case 4:
                    activeStr += currentStr;
                    break;
            }
        }
        if (!nameStr.equals("")) {
            this.name = nameStr;
        } else  {
            throw new BadDataException("Bad Researcher Data");
        }

        try {
            int id = Integer.parseInt(idStr);
            this.idNumber = id;
        } catch (Exception e) {
            throw new BadDataException("Bad Researcher Data");
        }

        try {
            int clear = Integer.parseInt(clearStr);
            this.clearance = clear;
        } catch (Exception e) {
            throw new BadDataException("Bad Researcher Data");
        }

        if (classStr.toCharArray().length == 1 && Character.isUpperCase(classStr.toCharArray()[0])) {
            this.classification = classStr.toCharArray()[0];
        } else {
            throw new BadDataException("Bad Researcher Data");
        }

        try {
            boolean activeBool = Boolean.parseBoolean(activeStr);
            this.active = activeBool;
        } catch (Exception e) {
            throw new BadDataException("Bad Researcher Data");
        }

        if (classification == 'E' && active) {
            throw new BadDataException("Bad Researcher Data");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public int getClearance() {
        return clearance;
    }

    public void setClearance(int clearance) {
        this.clearance = clearance;
    }

    public char getClassification() {
        return classification;
    }

    public void setClassification(char classification) {
        this.classification = classification;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean compatible(SCP scp) {

        if (classification == 'E' || !active) {
            return false;
        }
        if (classification == 'A') {
            if (scp.isContact()) {
                return false;
            }
        }
        if (classification == 'B') {
            if (scp.isContact() && !scp.getObjectClass().toUpperCase().equals("SAFE")) {
                return false;
            }
        }
        if (scp.getObjectName().equals("Bad SCP Data")) {
            return false;
        }

        if(clearance < scp.getClearanceLevel()) {
            return false;
        }

        return true;
    }

    public boolean equals(Object o) {
        Researcher testResearcher = (Researcher) o;
        if (name.equals(testResearcher.getName()) && !name.equals("")) {
            if (idNumber == testResearcher.getIdNumber() && idNumber != 0) {
                if (clearance == testResearcher.getClearance() && clearance != 0) {
                    return true;
                }
            }
        } 
        return false;
    }

    public String toString() {
        return String.format("%s,%d,%d,%c,%b", name, idNumber, clearance, classification, active);
    }
}
