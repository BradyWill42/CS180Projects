import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.*;

import javax.swing.JOptionPane;


/**
 * Challenge 11 -- SearchClient
 *
 * This class acts as the client
 * accessing a database hosted on
 * a server
 *
 * @author Brady Williams, L23
 *
 * @version April 1, 2024
 *
 */
public class SearchClient { //for this client, we will be connecting using localhost and the port 4242
    public static void main(String[] args)  {
        ArrayList<String> titles;
        int indexDesc = 0;
        String choice = "";
        String search = "";
        String desc = "";
        int selection = 0;

        String line = "";
        String hostName = "";
        Integer portNumber = -1;
        boolean exit = false;

        BufferedReader br = null;
        PrintWriter pw = null;
    
        Socket socket = null;

        JOptionPane.showMessageDialog(null, "Welcome to the Database Search", "Database Search", 
            JOptionPane.INFORMATION_MESSAGE);

        do {
            hostName = JOptionPane.showInputDialog(null, "Input Host Name", "Database Search",
                JOptionPane.QUESTION_MESSAGE);
            if (hostName == null || hostName.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter a valid host name", "Database Search",
                    JOptionPane.ERROR_MESSAGE);
            }
        } while (hostName == null || hostName.equals(""));


        do {
            try {
                portNumber = Integer.parseInt(JOptionPane.showInputDialog(null, "Input Port Number", "Database Search",
                    JOptionPane.QUESTION_MESSAGE));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid integer port number", "Database Search",
                    JOptionPane.ERROR_MESSAGE);
            }
        } while (portNumber == -1);

        while (!exit) {
            try {
                socket = new Socket(hostName, portNumber);
                if (socket.isConnected()) {
                    JOptionPane.showMessageDialog(null, "Connection successful", "Database Search",
                        JOptionPane.INFORMATION_MESSAGE);
                }

                while (socket.isConnected() && !exit) {
                    titles = new ArrayList<String>();

                    try {
                        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        pw = new PrintWriter(socket.getOutputStream(), true);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Socket Connection Broken", "Database Search",
                            JOptionPane.ERROR_MESSAGE);
                        exit = true;
                    }

                    do {
                        search = JOptionPane.showInputDialog(null, "Input text to search for", "Database Search",
                            JOptionPane.QUESTION_MESSAGE); 
                        if (search != null) {
                            pw.write(search);
                            pw.println();
                            pw.write("EOT");
                            pw.println();
                        
                            try {
                                while (true) {
                                    line = br.readLine();
                                    if (line.equals("EOT")) {
                                        break;
                                    }
                                    if (!line.equals("N/A")) {
                                        titles.add(line);
                                    } else {
                                        break;
                                    }
                                }
                            } catch (IOException e) {
                                JOptionPane.showMessageDialog(null, "Socket Connection Broken", 
                                    "Database Search", JOptionPane.ERROR_MESSAGE);
                                exit = true;
                            }
    
                            if (titles.size() < 1) {
                                selection = JOptionPane.showConfirmDialog(null, "No valid results, Quit?", 
                                    "Database Search", JOptionPane.YES_NO_OPTION);
                        
                                if (selection == JOptionPane.YES_OPTION) {
                                    exit = true;
                                    break;
                                } else {
                                    exit = false;
                                }
                            }
                        }
                    } while (titles.size() < 1);
                                            
                    if (titles.size() >= 1) {
                        choice = (String) JOptionPane.showInputDialog(null, "Choose a selection...", 
                        "Database Search", JOptionPane.PLAIN_MESSAGE, null, titles.toArray(), titles.toArray()[0]); 
                        if (choice != null) {
                            for (int i = 0; i < titles.size(); i++) {
                                if (choice.toLowerCase().equals(titles.get(i).toLowerCase())) {
                                    indexDesc = i;
                                    break;
                                }
                            }
        
                            pw.print(indexDesc);
                            pw.println();
                            pw.write("EOT");
                            pw.println();
        
                            try {
                                while (true) {
                                    line = br.readLine();
                                    if (line.equals("EOT")) {
                                        break;
                                    }
                                    desc = line;
                                }
                            } catch (IOException e) {
                                JOptionPane.showMessageDialog(null, "Socket Connection Broken", 
                                    "Database Search", JOptionPane.ERROR_MESSAGE);
                                exit = true;
                            }
        
                            JOptionPane.showMessageDialog(null, desc, "Database Search",
                                JOptionPane.INFORMATION_MESSAGE);   
                            
                            selection = JOptionPane.showConfirmDialog(null, "Quit?", "Database Search", 
                                JOptionPane.YES_NO_OPTION);
                            
                            if (selection == JOptionPane.YES_OPTION) {
                                exit = true;
                            } else {
                                exit = false;
                            }
                        } else {
                            continue;
                        }
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Socket Connection Broken", "Database Search",
                    JOptionPane.ERROR_MESSAGE);
                exit = true;
            }
        } 

        JOptionPane.showMessageDialog(null, "Thank you for using Database Search", "Database Search", 
            JOptionPane.INFORMATION_MESSAGE);
    }
}