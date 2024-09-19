import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

//Javadoc
/**
 * Project 3 - TheSCPFoundation
 *
 * The main class for where the 
 * reading and writing occurs. 
 * 
 * @author Brady Williams, L23
 *
 * @version Mar 25, 2024
 *
 */
public class TheSCPFoundation {
    
    public static void main(String[] args) {
        String error = "";
        File input;
        String line = "";
        input = new File("input.txt");
        try {
            FileReader fr = new FileReader(input);
            BufferedReader br = new BufferedReader(fr);
            try {
                line = br.readLine();
                br.close();
            } catch (IOException e) {
                error = "IO Read Failure";
            }
        } catch (FileNotFoundException e) {
            error = "Command Failure";
        }
        int counter = 0;
        String currentStr;
        String scpIn = "";
        String researcherIn = "";
        String databaseOutput = "";
        String mainMethodResults = "";
        for (int i = 0; i < line.length(); i++) {
            currentStr = line.substring(i, i + 1);
            if (currentStr.equals(" ")) {
                counter++;
                continue;
            }

            switch (counter) {
                case 0:
                    scpIn += currentStr;
                    break;
                case 1: 
                    researcherIn += currentStr;
                    break;
                case 2:
                    databaseOutput += currentStr;
                    break;
                case 3: 
                    mainMethodResults += currentStr;  
                    break;                     
            }

        }
        
        File mainOutput = new File(mainMethodResults);
        String line1;
        try {
            PrintWriter pw = new PrintWriter(mainOutput);
            FoundationDatabase fd = new FoundationDatabase(scpIn, researcherIn, databaseOutput);
            pw.println("Foundation Database Started");
            FileReader fr1 = new FileReader(new File("input.txt"));
            BufferedReader br1 = new BufferedReader(fr1);
            int command;
            try {
                br1.readLine();
                while ((line1 = br1.readLine()) != null) {
                    try {
                        command = Integer.parseInt(line1);
                    } catch (NumberFormatException e) {
                        pw.println("Command Failure");
                        continue;
                    }
                    switch (command) {
                        case 1: 
                            if (fd.readSCP()) {
                                pw.println("1 Success");
                            } else {
                                pw.println("1 Failure");
                            }
                            break;
                        case 2: 
                            if (fd.readResearcher()) {
                                pw.println("2 Success");
                            } else {
                                pw.println("2 Failure");
                            }
                            break;
                        case 3: 
                            if (fd.autoAssignResearcher()) {
                                pw.println("3 Success");
                            } else {
                                pw.println("3 Failure");
                            }
                            break;
                        case 4: 
                            if (fd.modifySCP(br1.readLine(), br1.readLine())) {
                                pw.println("4 Success");
                            } else {
                                pw.println("4 Failure");
                            }
                            break;
                        case 5: 
                            if (fd.modifyResearcher(br1.readLine(), br1.readLine())) {
                                pw.println("5 Success"); 
                            } else {
                                pw.println("5 Failure");
                            }
                            break;
                        case 6: 
                            if (fd.addResearcher(Integer.parseInt(br1.readLine()), br1.readLine())) {
                                pw.println("6 Success");
                            } else {
                                pw.println("6 Failure");
                            }
                            break;
                        case 7: 
                            if (fd.outputDatabase()) {
                                pw.println("7 Success");
                            } else {
                                pw.println("7 Failure");
                            }
                            break;
                    }
                    
                }
                br1.close();
            } catch (IOException e) {
                pw.println("IO Read Failure");
            }
            pw.println(error);
            pw.close();

        } catch (FileNotFoundException e) {
            error = "Command Failure";
        }



       
        
    }
}
