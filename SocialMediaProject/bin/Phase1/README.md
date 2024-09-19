1. Instructions on how to compile and run the project:
    To compile the java files, run the command "javac SocialMediaProject/Phase1/*.java". This will compile all the files
    in the phase 1 package and generates the corresponding class files. To run the project, execute the main method of
    the test class.
2. Who submitted which part of the assignment on Brightspace and Vocareum:
     Anuraag Kolli
3. Description of each class, including functionality, testing, and relationship to other classes:
    Profile:
         The Profile class contains and manages profile data such as username, email, friends, and blocked users. Each
         instance of the Profile class represents a user within the System. This class also contains methods used to add
         friends, block users, and manage the users messages. Additionally, it implements functional methods to compare 
         users and finally contains a toString method which returns a string representation of a Profile object. 
         UserProfile is the interface for the Profile class.
    
    Message:
        The Message class is a representation of users sending messages to other users within the social media system. 
        Each instance of the Message class represents a single message containing attributes such as content, time sent,
        sender's username, receiver's username, and file location of an image. The class has multiple constructors to
        messages with or without images. The methods in the class are used to access and modify messages. There are
        getters and setters for retrieving and updating message content, time sent, sender, receiver, and image. The
        equals() method allows you to compare two messages, consider their content, sender, and receiver. The toString
        method generates a String representation of the message. Messaging is the interface for the Message class.

    PhotoMessage:
        The PhotoMessage class represents a message containing a photo. It is a subclass of the Message class, therefore
        it inherits the properties and behavior of the Message class. The string photoFileName stores the filename
        associated with the message. The class contains a constructor for a photo message and getters and setters for
        the photo message. The toString method provides a string representation of the PhotoMessage object and returns
        a string containing the photo filename, sender username, receiver username, and timestamp, separated by "##".
        Photos is the interface for the PhotoMessage class.

    UserDatabase:
        UserDatabase is a database that manages storing, accessing, and manipulating data related to user profiles and
        messaging. The class has a constructor that takes filenames as parameters to specify the locations of profile
        and message data files. The deleteProfile and updateProfile which removes a profile from a list of users and 
        updates a profile's email address based on username, respectively. The sendMessage method allows for the
        creation and sending of messages between profiles by creating a new Message object and adding the message to a
        list of messages in the sender's message list. The viewFriends and viewBlocked methods allow users to view their
        friends and blocked users. These methods iterate over the user's friends or blocked list and constructs a string
        representation of the usernames. Methods like readProfile, readMessages, writeProfiles, and writeMessages handle
        reading from and writing to files containing profile and message data. They parse the data from the objects back
        to the files. Finally, exception handling is used for file operations to catch and handle FileNotFoundException
        and IOException. 
        