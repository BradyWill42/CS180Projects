package Phase2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;

public class Client extends JFrame implements Runnable {
    private boolean loggedIn;
    private static boolean exit;
    private static boolean stop;
    private static BufferedReader reader;
    private static PrintWriter writer;
    private CardLayout cardLayout;
    private JPanel cards;

    public Client() {
        setTitle("Social Media Client");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeUI();
        setVisible(true);
    }

    private void initializeUI() {
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        cards.add(createLoginPanel(), "Login");
        cards.add(createMainMenuPanel(), "MainMenu");
        cards.add(createMessagesPanel(), "Messages");
        cards.add(createBlockPanel(), "Block");
        cards.add(createFriendsPanel(), "Friends");
        cards.add(createSettingsPanel(), "Settings");

        add(cards);
        cardLayout.show(cards, "Login");
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");
        JButton createAccountButton = new JButton("Create Account");

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(createAccountButton);

        loginButton.addActionListener(e -> login(usernameField.getText(), new String(passwordField.getPassword())));
        createAccountButton.addActionListener(e -> cardLayout.show(cards, "CreateAccount"));

        return panel;
    }

    private void login(String username, String password) {
        writer.println(username + ":" + password);
        try {
            String response = reader.readLine();
            if ("LoginSuccessful".equals(response)) {
                JOptionPane.showMessageDialog(null, "Successfully logged in.");
                loggedIn = true;
                cardLayout.show(cards, "MainMenu");
            } else {
                JOptionPane.showMessageDialog(null, "Invalid login information. Try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading from server.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel createAccountCreationPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2));
        JTextField usernameField = new JTextField(20);
        JTextField passwordField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JButton createButton = new JButton("Create Account");

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(createButton);

        createButton.addActionListener(e -> createAccount(usernameField.getText(), passwordField.getText(), emailField.getText()));

        return panel;
    }

    private void createAccount(String username, String password, String email) {
        writer.println(username + ":" + password + ":" + email);
        try {
            String response = reader.readLine();
            if ("AccountCreated".equals(response)) {
                JOptionPane.showMessageDialog(null, "New profile created!");
                cardLayout.show(cards, "Login");
            } else {
                JOptionPane.showMessageDialog(null, "Account creation failed. Try again.", "Account Creation Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading from server.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel createMainMenuPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1));
        JButton msgButton = new JButton("Messages");
        JButton friendButton = new JButton("Friends");
        JButton blockButton = new JButton("Block/Unblock Users");
        JButton settingsButton = new JButton("Settings");
        JButton logoutButton = new JButton("Logout");

        panel.add(msgButton);
        panel.add(friendButton);
        panel.add(blockButton);
        panel.add(settingsButton);
        panel.add(logoutButton);

        msgButton.addActionListener(e -> cardLayout.show(cards, "Messages"));
        friendButton.addActionListener(e -> cardLayout.show(cards, "Friends"));
        blockButton.addActionListener(e -> cardLayout.show(cards, "Block"));
        settingsButton.addActionListener(e -> cardLayout.show(cards, "Settings"));
        logoutButton.addActionListener(e -> logout());

        return panel;
    }

    private void logout() {
        loggedIn = false;
        cardLayout.show(cards, "Login");
    }

    private JPanel createMessagesPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1));
        JTextField messageField = new JTextField(20);
        JButton sendButton = new JButton("Send");
        JButton viewButton = new JButton("View Messages");
        JButton backButton = new JButton("Back to Menu");

        panel.add(new JLabel("Enter Message:"));
        panel.add(messageField);
        panel.add(sendButton);
        panel.add(viewButton);
        panel.add(backButton);

        sendButton.addActionListener(e -> sendMessage(messageField.getText()));
        viewButton.addActionListener(e -> viewMessages());
        backButton.addActionListener(e -> cardLayout.show(cards, "MainMenu"));

        return panel;
    }

    private void sendMessage(String message) {
        writer.println("SendMessage:" + message);
        try {
            String response = reader.readLine();
            JOptionPane.showMessageDialog(null, response);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error sending message.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewMessages() {
        writer.println("ViewMessages");
        try {
            String messages = reader.readLine(); // Assuming messages are sent line by line
            JOptionPane.showMessageDialog(null, messages);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error retrieving messages.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel createBlockPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1));
        JTextField userField = new JTextField(20);
        JButton blockButton = new JButton("Block User");
        JButton unblockButton = new JButton("Unblock User");
        JButton viewBlockedButton = new JButton("View Blocked Users");
        JButton backButton = new JButton("Back to Menu");

        panel.add(new JLabel("Username:"));
        panel.add(userField);
        panel.add(blockButton);
        panel.add(unblockButton);
        panel.add(viewBlockedButton);
        panel.add(backButton);

        blockButton.addActionListener(e -> blockUser(userField.getText()));
        unblockButton.addActionListener(e -> unblockUser(userField.getText()));
        viewBlockedButton.addActionListener(e -> viewBlockedUsers());
        backButton.addActionListener(e -> cardLayout.show(cards, "MainMenu"));

        return panel;
    }

    private void blockUser(String username) {
        writer.println("BlockUser:" + username);
        try {
            String response = reader.readLine();
            JOptionPane.showMessageDialog(null, response);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error blocking user.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void unblockUser(String username) {
        writer.println("UnblockUser:" + username);
        try {
            String response = reader.readLine();
            JOptionPane.showMessageDialog(null, response);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error unblocking user.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewBlockedUsers() {
        writer.println("ViewBlockedUsers");
        try {
            String users = reader.readLine();
            JOptionPane.showMessageDialog(null, users);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error retrieving blocked users.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel createFriendsPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1));
        JTextField friendField = new JTextField(20);
        JButton addButton = new JButton("Add Friend");
        JButton removeButton = new JButton("Remove Friend");
        JButton viewFriendsButton = new JButton("View Friends");
        JButton backButton = new JButton("Back to Menu");

        panel.add(new JLabel("Friend Username:"));
        panel.add(friendField);
        panel.add(addButton);
        panel.add(removeButton);
        panel.add(viewFriendsButton);
        panel.add(backButton);

        addButton.addActionListener(e -> addFriend(friendField.getText()));
        removeButton.addActionListener(e -> removeFriend(friendField.getText()));
        viewFriendsButton.addActionListener(e -> viewFriends());
        backButton.addActionListener(e -> cardLayout.show(cards, "MainMenu"));

        return panel;
    }

    private void addFriend(String username) {
        writer.println("AddFriend:" + username);
        try {
            String response = reader.readLine();
            JOptionPane.showMessageDialog(null, response);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error adding friend.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeFriend(String username) {
        writer.println("RemoveFriend:" + username);
        try {
            String response = reader.readLine();
            JOptionPane.showMessageDialog(null, response);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error removing friend.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewFriends() {
        writer.println("ViewFriends");
        try {
            String friends = reader.readLine();
            JOptionPane.showMessageDialog(null, friends);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error viewing friends list.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel createSettingsPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1));
        JTextField newPasswordField = new JTextField(20);
        JButton changePasswordButton = new JButton("Change Password");
        JButton backButton = new JButton("Back to Menu");

        panel.add(new JLabel("New Password:"));
        panel.add(newPasswordField);
        panel.add(changePasswordButton);
        panel.add(backButton);

        changePasswordButton.addActionListener(e -> changePassword(newPasswordField.getText()));
        backButton.addActionListener(e -> cardLayout.show(cards, "MainMenu"));

        return panel;
    }

    private void changePassword(String newPassword) {
        writer.println("ChangePassword:" + newPassword);
        try {
            String response = reader.readLine();
            JOptionPane.showMessageDialog(null, response);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error changing password.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void run() {
        try {
            int port = 1111;
            Socket socket = new Socket("localhost", port);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            if (socket.isConnected()) {
                JOptionPane.showMessageDialog(null, "Connection established.");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to connect to server :[", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Client());
    }
}
