import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

//Javadoc
/**
 * Project 3 - FoundationDatabase
 *
 * The Foundation for the database, where
 * all calculations occur
 * 
 * @author Brady Williams, L23
 *
 * @version Mar 25, 2024
 *
 */
public class FoundationDatabase {
    private String databaseOutput;
    private Researcher[] researcher;
    private String researcherIn;
    private SCP[] scp;
    private String scpIn;

    public FoundationDatabase(String scpIn, String researcherIn, String databaseOutput) {
        this.scpIn = scpIn;
        this.researcherIn = researcherIn;
        this.databaseOutput = databaseOutput;
        this.researcher = null;
        this.scp = null;
    }

    public boolean readSCP() {
        File f;
        String line;
        BufferedReader br;
        FileReader fr;
        ArrayList<SCP> scpTemp = new ArrayList<SCP>();
        try {
            f = new File(scpIn);
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                try {
                    SCP temp = new SCP(line);
                    scpTemp.add(temp);
                } catch (BadDataException e) { 
                    br.close();
                    return false;
                }
            }
            br.close();
    
        } catch (IOException e) {
            return false;
        }

        scp = new SCP[scpTemp.size()];

        for (int i = 0; i < scpTemp.size(); i++) {
            scp[i] = scpTemp.get(i);
        }

        // System.out.println(scpTemp.toString());
        
        
        return true;
    }

    public boolean readResearcher() {
        File f;
        String line;
        BufferedReader br;
        FileReader fr;
        ArrayList<Researcher> tempArr = new ArrayList<Researcher>();
        try {
            f = new File(researcherIn);
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                try {
                    tempArr.add(new Researcher(line));
                } catch (BadDataException e) {
                    continue;
                }
            }
            br.close();
        } catch (IOException e) {
            return false;
        }

        researcher = new Researcher[tempArr.size()];

        for (int i = 0; i < tempArr.size(); i++) {
            researcher[i] = tempArr.get(i);
        }

        // System.out.println(tempArr.toString());

        return true;
    }

    public boolean autoAssignResearcher() {  
        boolean located = false;
        boolean assigned = false;

        for (int i = 0; i < scp.length; i++) {
            located = false;

            for (int k = 0; k < researcher.length && !located; k++) {
                if (scp[i].getClearanceLevel() == researcher[k].getClearance() && researcher[k].compatible(scp[i])) {
                    assigned = false;
                    for (int j = 0; j < scp.length; j++) {
                        if (scp[j].getResearchers() != null) {
                            for (int l = 0; l < scp[j].getResearchers().length; l++) {
                                if (researcher[k].equals(scp[j].getResearchers()[l])) {
                                    assigned = true;
                                }
                            }
                        }
                    }

                    if (!assigned) {
                        
                        located = true;

                        if (scp[i].getResearchers() == null) {
                            Researcher[] newArr = new Researcher[1];
                            newArr[0] = researcher[k];
                            scp[i].setResearchers(newArr);
                        } else {
                            addResearcher(scp[i].getItemNumber(), researcher[k].toString());
                            break;
                        }
                    }
                }
            }

            if (!located) {
                for (int k = 0; k < researcher.length && !located; k++) {
                    if (researcher[k].compatible(scp[i])) {

                        assigned = false;

                        for (int j = 0; j < scp.length; j++) {
                            if (scp[j].getResearchers() != null) {
                                for (int l = 0; l < scp[j].getResearchers().length; l++) {
                                    if (researcher[k].equals(scp[j].getResearchers()[l])) {
                                        assigned = true;
                                    }
                                }
                            }
                        }

                        if (!assigned) {
                            located = true;

                            if (scp[i].getResearchers() == null) {
                                Researcher[] newArr = new Researcher[1];
                                newArr[0] = researcher[k];
                                scp[i].setResearchers(newArr);
                            } else {
                                addResearcher(scp[i].getItemNumber(), researcher[k].toString());
                                break;
                            }
                        }
                    }
                }
            }
        }

        for (int a = 0; a < scp.length; a++) {
            if (!scp[a].getObjectName().equals("Bad SCP Data") && scp[a].getResearchers() == null) {
                return false;
            }
        }

        return true;

    }

    public boolean modifyResearcher(String oldData, String newData) {
        for (int i = 0; i < researcher.length; i++) {
            if (researcher[i].toString().equals(oldData)) {
                try {
                    researcher[i] = new Researcher(newData);
                } catch (Exception e) {
                    return false;
                }
            }
        }
        autoAssignResearcher();
        return true;
    }

    public boolean modifySCP(String oldData, String newData) {
        for (int i = 0; i < scp.length; i++) {
            if (scp[i].toString().equals(oldData)) {
                try {
                    scp[i] = new SCP(newData);
                } catch (Exception e) {
                    return false;
                }
            }
        }
        autoAssignResearcher();
        return true;
    }

    public boolean addResearcher(int itemNumber, String data) {
        try {
            Researcher researchee = new Researcher(data);
            SCP mySCP = null;
            for (int i = 0; i < scp.length; i++) {
                if (itemNumber == scp[i].getItemNumber()) {
                    mySCP = scp[i];
                    break;
                }
            }

            boolean located = false;
            for (int i = 0; i < researcher.length; i++) {
                if (researcher[i].equals(researchee)) {
                    located = true;
                }
            }

            if (!located) {
                ArrayList<Researcher> testRes = new ArrayList<Researcher>();
            
                for (int i = 0; i < researcher.length; i++) {
                    testRes.add(researcher[i]);
                }
    
                testRes.add(researchee);

                researcher = new Researcher[testRes.size()];

                for (int i = 0; i < testRes.size(); i++) {
                    researcher[i] = testRes.get(i);
                }



                
            }

            if (mySCP != null && researchee.compatible(mySCP)) {
                ArrayList<Researcher> scpRes = new ArrayList<Researcher>();

                if (mySCP.getResearchers() != null) {
                    for (int i = 0; i < mySCP.getResearchers().length; i++) {
                        scpRes.add(mySCP.getResearchers()[i]);
                    }
                }
                
                scpRes.add(researchee);

                researcher = new Researcher[scpRes.size()];

                for (int i = 0; i < scpRes.size(); i++) {
                    researcher[i] = scpRes.get(i);
                }
        
                mySCP.setResearchers(researcher);
                
            } else {
                return false;
            }

            
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean outputDatabase() {
        String researched = "";
        try {
            File f = new File(databaseOutput);
            PrintWriter pw = new PrintWriter(f);
            for (int i = 0; i < scp.length; i++) {
                pw.print(scp[i] + ",");
                if (scp[i].getResearchers() != null) {
                    for (int j = 0; j < scp[i].getResearchers().length; j++) {
                        researched += scp[i].getResearchers()[j] + ",";
                    }
                    researched = researched.substring(0, researched.length() - 1);
                    pw.print(researched);
                    pw.println();
                } else  {
                    pw.print("VACANT");
                    pw.println();
                }
                researched = "";
                
            }
            pw.close();
            
        
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    
        

    }
}
