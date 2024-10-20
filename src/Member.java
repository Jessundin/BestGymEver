import java.time.LocalDate;
import java.time.Period;

public class Member {

    private final String name;
    private final String socialSecurityNumber;
    private final LocalDate subscriptionDate;

    public Member(String name, String socialSecurityNumber, LocalDate subscriptionDate) {
        this.name = name;
        this.socialSecurityNumber = socialSecurityNumber;
        this.subscriptionDate = subscriptionDate;
    }

    public String getName() {
        return name;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public boolean hasActiveSubscription() {
        return Period.between(subscriptionDate, LocalDate.now()).getYears() == 0;
    }

    @Override
    public String toString() {
        LocalDate today = LocalDate.now();
        return name + " " + socialSecurityNumber + " " + today;
    }
}
