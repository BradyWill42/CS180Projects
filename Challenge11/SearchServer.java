import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


/**
 * Challenge 11 -- SearchServer
 *
 * This class acts as the server
 * on which the database is hosted
 * and where the client accesses the 
 * stored information 
 * 
 * @author Brady Williams, L23
 *
 * @version April 1, 2024
 *
 */
public class SearchServer { //For this server, we will be using port 4242
    public static void main(String[] args) {
        String line = "";
        String search = "";
        int indexDesc = 0;
        ArrayList<String> matches;
        String[] matchedTitle;
        String[] matchedDescription;
        ServerSocket serverSocket = null;
        Socket socket = null;
        BufferedReader fileReader = null;
        BufferedReader socketReader = null;
        PrintWriter pw = null;
        
        try {
            serverSocket = new ServerSocket(4242);
            serverSocket.setSoTimeout(0);
            socket = serverSocket.accept();

            
            socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream(), true);
            

            do {

                matches = new ArrayList<String>();

                do {

                    fileReader = new BufferedReader(new FileReader(new File("searchDatabase.txt")));

                    while (true) {
                        line = socketReader.readLine();
                        if (line.equals("EOT")) {
                            break;
                        }
                        search = line;
                    }

                    while ((line = fileReader.readLine()) != null) {
                        if (line.toLowerCase().contains(search.toLowerCase())) {
                            matches.add(line);
                        }
                    }

                    if (matches.size() < 1) {
                        pw.write("N/A");
                        pw.println();
                    }

                } while (matches.size() < 1);

                if (matches.size() >= 1) {
                    fileReader.close();

                    matchedTitle = new String[matches.size()];
                    for (int i = 0; i < matches.size(); i++) {
                        matchedTitle[i] = matches.get(i).split(";")[1];
                    }

                    matchedDescription = new String[matches.size()];
                    for (int i = 0; i < matches.size(); i++) {
                        matchedDescription[i] = matches.get(i).split(";")[2];
                    }

                    for (int i = 0; i < matchedTitle.length; i++) {
                        pw.write(matchedTitle[i]);
                        pw.println();
                    }

                    pw.write("EOT");
                    pw.println();

                    while (true) {
                        line = socketReader.readLine();
                        if (line.equals("EOT")) {
                            break;
                        }
                        indexDesc = Integer.parseInt(line);
                    }

                    pw.write(matchedDescription[indexDesc]);      
                    pw.println();
                    pw.write("EOT");
                    pw.println();                  
                }
            } while (true);

        } catch (Exception e) {
            try {
                serverSocket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        
    }
}