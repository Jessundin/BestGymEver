import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Path inputFilePath = Paths.get("membersFile");
        Path outputFilePath = Paths.get("membersTracker");
        Scanner scanner = new Scanner(System.in);

        try {
            SubscriptionManager subscriptionManager = new SubscriptionManager(inputFilePath, outputFilePath);

            while (true) {
                System.out.println("Enter first- and last name or social security number (or type 'exit' to Exit)");
                String userInput = scanner.nextLine();

                if (userInput.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting...");
                    break;
                }

                subscriptionManager.checkMember(userInput);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}