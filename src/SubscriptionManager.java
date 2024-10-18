import java.io.*;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionManager {

    private final List<Member> members;
    private final Path outputFilePath;

    public SubscriptionManager(Path inputFilePath, Path outputFilePath) {
        this.members = new ArrayList<>();
        this.outputFilePath = outputFilePath;
        readMembers(inputFilePath);
    }

    private void readMembers(Path inputFilePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath.toFile()))) {
            String memberLine;
            while ((memberLine = br.readLine()) != null) {
                String[] memberData = memberLine.split(",");
                String socialSecurityNumber = memberData[0].trim();
                String memberName = memberData[1].trim();
                LocalDate subscriptionDate = LocalDate.parse(br.readLine().trim());
                members.add(new Member(socialSecurityNumber, memberName, subscriptionDate));
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void checkMember(String userInput) {
        for (Member member : members) {
            if (userInput.equalsIgnoreCase(member.getSocialSecurityNumber()) || userInput.equalsIgnoreCase(member.getName())) {
                if (member.hasActiveSubscription()) {
                    System.out.println("Member has an active subscription" + System.lineSeparator());
                    logMember(member);
                } else {
                    System.out.println("Member has no active subscription" + System.lineSeparator());
                } return;
            }
        }
        System.out.println(userInput + " is not a member" + System.lineSeparator());
    }


    private void logMember (Member member) {
        try (BufferedWriter outputWriter = new BufferedWriter(new FileWriter(outputFilePath.toFile(), true))) {
            outputWriter.write(member.toString());
            outputWriter.newLine();
        } catch (
                IOException e) {
            System.err.println("Error while logging member: " + e.getMessage());
        }
    }
}

