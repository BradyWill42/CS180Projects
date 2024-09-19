import java.util.Scanner;

public class IntroduceMe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Enter your major: ");
        String major = scanner.nextLine();
        System.out.println("Enter the number of credits you are taking: ");
        int credits = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter your GPA: ");
        double gpa = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter your previous experience: ");
        String experience = scanner.nextLine();
        System.out.println("Enter your hobby: ");
        String hobby = scanner.nextLine();

        System.out.println("Hello! My name is " + name + ".");
        System.out.println("I am majoring in " + major + ".");
        System.out.println("I am currently taking " + credits + " credits.");
        System.out.printf("My GPA is %.2f \n", gpa);
        System.out.println("Before coming to Purdue, I was " + experience + ".");
        System.out.println("I like to spend my free time " + hobby + ".");
    }
}
