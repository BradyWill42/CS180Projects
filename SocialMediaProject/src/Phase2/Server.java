package Phase2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;

import Phase1.UserDatabase;

public class Server {
    public static UserDatabase db = new UserDatabase(
        "profiles.txt", 
        "messages.txt", 
        "photos.txt");
    public static Object lock = new Object();
    private BufferedReader reader;
    private PrintWriter writer;

    public static void main(String[] args) {
        int port = 1111;
        boolean listening = true;
        try {
            db.readProfiles();
            db.readMessages();
            db.readPhotoMessages();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        // loop infinitely so that it can multi thread
        try (ServerSocket serverSocket = new ServerSocket(port)) {

            while (listening) {
                System.out.println("Client connecting ... ");
                Socket socket = serverSocket.accept();
                System.out.println("Client Connected!");
                new Thread(new ClientManager(socket)).start();
                // add thread here for multi threading
            }
            serverSocket.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
