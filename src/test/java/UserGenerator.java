import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.util.Locale;

public class UserGenerator {
    private final Faker faker;

    public UserGenerator() {
        this.faker = new Faker(new Locale("ru"));
    }

    public User generateUser() {
        String city = faker.address().city();
        LocalDate date = LocalDate.now();
        String name = faker.name().fullName();
        String phone = faker.phoneNumber().cellPhone();

        return new User();
    }
}