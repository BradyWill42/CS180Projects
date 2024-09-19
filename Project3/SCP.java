//Javadoc
/**
 * Project 3 - SCP
 *
 * The class that contains the
 * information about the SCP creatures
 * 
 * @author Brady Williams, L23
 *
 * @version Mar 25, 2024
 *
 */
public class SCP {
    private int clearanceLevel;
    private boolean contact;
    private int itemNumber;
    private String objectClass;
    private String objectName;
    private Researcher[] researchers;


    public SCP(BadDataException e) {
        this.clearanceLevel = 0;
        this.contact = false;
        this.objectName = "Bad SCP Data";
        this.objectClass = null;
        this.researchers = null;
        this.itemNumber = Integer.parseInt(e.getMessage().substring(5, 8));
    }

    public SCP(String data) throws BadDataException {
        String currentStr = "";
        int counter = 0;
        String clearStr = "";
        String contactStr = "";
        String itemStr = "";
        String objectClassStr = "";
        String objectNameStr = "";


        String myData = data.substring(4, data.length());
    
        for (int i = 0; i < myData.length(); i++) {
            currentStr = myData.substring(i, i + 1);
            if (currentStr.equals(",")) {
                counter++;
                continue;
            } 

            switch (counter) {
                case 0: 
                    itemStr += currentStr;
                    break;
                case 1: 
                    objectNameStr += currentStr;
                    break;
                case 2: 
                    objectClassStr += currentStr;
                    break;
                case 3: 
                    clearStr += currentStr;
                    break;
                case 4:
                    contactStr += currentStr;
                    break;
            }
        }
        try {
            int clearance = Integer.parseInt(clearStr);
            this.clearanceLevel = clearance;
        } catch (Exception e) {
            throw new BadDataException("SCP-" + itemNumber + ": Bad SCP Data");
        }

        try {
            boolean contactBool = Boolean.parseBoolean(contactStr);
            this.contact = contactBool;
        } catch (Exception e) {
            throw new BadDataException("SCP-" + itemNumber + ": Bad SCP Data");
        }

        try {
            int item = Integer.parseInt(itemStr);
            this.itemNumber = item;
        } catch (Exception e) {
            throw new BadDataException("SCP-" + itemNumber + ": Bad SCP Data");
        }

        if (!objectClassStr.equals("")) {
            boolean found = false;
            String[] classifications = {
                "SAFE", 
                "EUCLID", 
                "KETER", 
                "THAUMIEL", 
                "NEUTRALIZED", 
                "DECOMMISSIONED", 
                "APOLLYON", 
                "ARCHON"
            };         
            for (int j = 0; j < classifications.length; j++) {
                if (objectClassStr.toUpperCase().equals(classifications[j])) {
                    found = true;
                    break;
                }
            }
            if (found) {
                this.objectClass = objectClassStr.toUpperCase();
            } else {
                throw new BadDataException("SCP-" + itemNumber + ": Bad SCP Data");
            }
        } else  {
            throw new BadDataException("SCP-" + itemNumber + ": Bad SCP Data");
        }

        if (!objectNameStr.equals("")) {
            this.objectName = objectNameStr;
        } else  {
            throw new BadDataException("SCP-" + itemNumber + ": Bad SCP Data");
        }
    }

    public Researcher[] getResearchers() {
        return researchers;
    }

    public void setResearchers(Researcher[] researchers) {
        this.researchers = researchers;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getObjectClass() {
        return objectClass;
    }

    public void setObjectClass(String objectClass) {
        this.objectClass = objectClass;
    }

    public int getClearanceLevel() {
        return clearanceLevel;
    }

    public void setClearanceLevel(int clearanceLevel) {
        this.clearanceLevel = clearanceLevel;
    }

    public boolean isContact() {
        return contact;
    }

    public void setContact(boolean contact) {
        this.contact = contact;
    }

    public boolean equals(Object o) {
        SCP newSCP = (SCP) o;
        if (newSCP.getObjectName().equals("Bad SCP Data")) {
            return false;
        } else if (newSCP.getItemNumber() == itemNumber) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return String.format("SCP-%d,%s,%s,%d,%b", itemNumber, objectName, objectClass, clearanceLevel, contact);
    }
}
