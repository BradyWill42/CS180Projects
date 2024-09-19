package Phase2;

// import SocialMediaProject.Phase1.Profile;

import Phase1.Message;
import Phase1.Profile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Scanner;
// import SocialMediaProject.Phase1.UserDatabase;


public class ClientManager implements Runnable {

    private Socket clientSocket;
    private BufferedReader reader;
    private PrintWriter writer;
    
    public ClientManager(Socket socket) {
        try {
            this.clientSocket = socket;
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new PrintWriter(clientSocket.getOutputStream(), true);
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unable to connect socket");
        }
    }


    public void run() { 
        Profile profile = new Profile();
        String option;
        try {
            do {
                
                synchronized(Server.lock) {
                    Server.db.writeProfiles();
                    Server.db.writeMessages();
                    Server.db.writePhotoMessages();
                    Server.db.readProfiles();
                    Server.db.readMessages();
                    Server.db.readPhotoMessages();
                    
                }
                    

                // println("Main Menu. Please choose an option.\n1.Create Account\n2.Login\n3.Exit");
                option = reader.readLine();
    
                // goes through all 3 options
                switch(option) {
                    case "1":
                        // "username:password:email"
                        String[] newProfileArray = reader.readLine().split(":");
                        synchronized(Server.lock) {
                            Server.db.addProfile(new Profile(newProfileArray[0], newProfileArray[1], newProfileArray[2], null, null));
                            Server.db.writeProfiles();
                            Server.db.readProfiles();    
                        }
                        
                        profile = Server.db.loginProfile(newProfileArray[0], newProfileArray[1]);
                        System.out.println(profile);
                        continueHomePage(profile);

                        break;

                        //After we create the new profile, send the user to the login page to login
                    case "2":
                        Profile profileLoggedIn;
                        do {
                            // username:password
                            String[] newLoginArray = reader.readLine().split(":");

                            synchronized (Server.lock) {
                                Server.db.readProfiles();
                                profileLoggedIn = Server.db.loginProfile(newLoginArray[0], newLoginArray[1]);
                                System.out.println(profileLoggedIn);
                            }

                            if (profileLoggedIn == null) {
                                writer.println("2");
                            }
                            else {
                                writer.println("1");
                            }
                        } while (profileLoggedIn == null);

                        continueHomePage(profileLoggedIn);
                        break;

                    case "3":
                        break;
                }
    
            }
            while (!option.equals("3"));


        } catch (IOException e) {
            System.out.println("IO Exception in thread");
        }


    }

    public void continueHomePage(Profile profile) {

        try {
            synchronized (Server.lock) {
                Server.db.writeMessages();
                Server.db.writeProfiles();
                Server.db.readMessages();
                Server.db.readProfiles();
            }
            
            String choice2 = reader.readLine();

            // "Home Page. Choose an Option.\n1.Messages\n2.Block\n3.Friend\n4.Settings\n5.Exit"
            switch (choice2) {
                case "1":
                    String choice3 = reader.readLine();
                    // "Choose an Option.\n1.Send message\n2.Delete message\n3.View message"

                    switch (choice3) {
                        case "1":
                            do {
                                // recipientUsername:message
                                try {
                                    String[] sendMessageArray = reader.readLine().split(":");
                                    synchronized (Server.lock) {
                                        Server.db.sendMessage(profile, Server.db.findProfile(sendMessageArray[0]), sendMessageArray[1]);
                                    }
                                    writer.println("1");
                                    break;
                                }
                                catch (Exception e) {
                                    writer.println("2");
                                    e.printStackTrace();
                                }
                            } while (true);
                            break;
                        case "2":
                            do {
                                String[] messageToDeleteToString = reader.readLine().split("##");
                                Message deleteMessage = new Message(messageToDeleteToString[0],
                                        messageToDeleteToString[1], messageToDeleteToString[2]);
                                synchronized (Server.lock) {
                                    if (Server.db.viewMessages(profile.getUsername()).contains(deleteMessage.toString())) {
                                        Server.db.deleteMessage(profile, deleteMessage.getReceiverUsername(),
                                                deleteMessage.getMessage());
                                        writer.println("1");
                                        break;
                                    }
                                    else {
                                        writer.println("2");
                                    }
                                }
                                    

                            } while (true);
                            break;
                        case "3":
                            // printed the line in client already
                    }
    
                case "2":
                    String blockedChoice = reader.readLine();

                    // "Choose an Option.\n1.Block user\n2.Unblock user\n3.View your blocked users"
                    switch (blockedChoice) {
                        case "1":
                            do {
                                String usernameToBlock = reader.readLine();
                                boolean validUsername = false;
                                synchronized (Server.lock) {
                                    for (Profile user : Server.db.getUsers()) {
                                        if (user.getUsername().equals(usernameToBlock)) {
                                            validUsername = true;
                                        }
                                    }
                                    if (validUsername) {
                                        // profile.blockUser(usernameToBlock);
                                        synchronized (Server.lock) {
                                            Server.db.blockUser(profile, usernameToBlock);
                                            Server.db.writeProfiles();
                                        }
                                        writer.println("1");
                                        break;
                                    } else {
                                        writer.println("2");
                                    }
                                }
                                
                            } while (true);
                            break;
                        case "2":
                            do {
                                String usernameToUnBlock = reader.readLine();
                                boolean validUsername = false;
                                synchronized (Server.lock) {
                                    for (Profile user : Server.db.getUsers()) {
                                        if (user.getUsername().equals(usernameToUnBlock)) {
                                            validUsername = true;
                                        }
                                    }
                                }
                                if (validUsername) {
                                    profile.unblockUser(usernameToUnBlock);
                                    synchronized (Server.lock) {
                                        Server.db.writeProfiles();
                                    }
                                    writer.println("1");
                                    break;
                                } else {
                                    writer.println("2");
                                }
                                
                            } while (true);
                            break;
                        case "3":
                            break;
                            // done in client side
                    }

    
                case "3":
                    String friendChoice = reader.readLine();

                    // "Choose an Option.\n1.Block user\n2.Unblock user\n3.View your blocked users"
                    switch (friendChoice) {
                        case "1":
                            do {
                                String usernameToFriend = reader.readLine();
                                boolean validUsername = false;
                                synchronized (Server.lock) {
                                    for (Profile user : Server.db.getUsers()) {
                                        if (user.getUsername().equals(usernameToFriend)) {
                                            validUsername = true;
                                        }
                                    }
                                    if (validUsername) {
                                        profile.addFriend(usernameToFriend);
                                        synchronized (Server.lock) {
                                            Server.db.writeProfiles();
                                        }
                                        writer.println("1");
                                        break;
                                    } else {
                                        writer.println("2");
                                    }
                                }
                                
                            } while (true);
                            break;
                        case "2":
                            do {
                                String usernameToUnfriend = reader.readLine();
                                boolean validUsername = false;
                                synchronized (Server.lock) {
                                    for (Profile user : Server.db.getUsers()) {
                                        if (user.getUsername().equals(usernameToUnfriend)) {
                                            validUsername = true;
                                        }
                                    }
                                }
                                if (validUsername) {
                                    profile.removeFriend(usernameToUnfriend);
                                    synchronized (Server.lock) {
                                        Server.db.writeProfiles();
                                    }
                                    writer.println("1");
                                    break;
                                } else {
                                    writer.println("2");
                                }
                                
                            } while (true);
                            break;
                        case "3":
                            break;
                            // done in client side
                }
    
    
                case "4":
                    String settings = reader.readLine();
                    if (settings.equals("1")) {
                        String messagingSettings = reader.readLine();
                        if (messagingSettings.equals("1")) {
                            profile.setFriendsOnly(true);
                        } else if (messagingSettings.equals("2")) {
                            profile.setFriendsOnly(false);
                        }
                        synchronized (Server.lock) {
                            Server.db.writeProfiles();
                        }

                    } else if (settings.equals("2")) {
                        String newPass;
                        do {
                            newPass = reader.readLine();
                            if (!newPass.contains(":")) {
                                profile.setPassword(newPass);
                                writer.println("1");
                            } else {
                                writer.println("2");
                            }
                        } while (newPass.contains(":"));
                        synchronized (Server.lock) {
                            Server.db.writeProfiles();
                        }
                    } else if (settings.equals("3")) {
                        String newEmail;
                        do {
                            newEmail = reader.readLine();
                            if (!newEmail.contains(":")) {
                                profile.setPassword(newEmail);
                                writer.println("1");
                            } else {
                                writer.println("2");
                            }
                        } while (newEmail.contains(":"));
                        Server.db.writeProfiles();
                    } 
                    break;                    
                case "5":
                    String exitMessage = reader.readLine();
                    break;
            }    
        } catch (IOException e) {
            System.out.println("Invalid inputs");
        }
        
    }
}
