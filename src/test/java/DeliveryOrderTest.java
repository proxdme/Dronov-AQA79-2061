
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class DeliveryOrderTest {

    private final UserGenerator userGenerator = new UserGenerator();

    @Test
    void testDeliveryOrderForm() {
        open("http://localhost:9999/");
        User userData = userGenerator.generateUser();
        fillDeliveryOrderForm(userData);
        clickRescheduleButton();
        assertMeetingTimeChanged();
    }

    private void fillDeliveryOrderForm(User userData) {
        $("[data-test-id='city']").setValue(userData.getCity());
        $("[data-test-id='date']").setValue(userData.getDate());
        $("[data-test-id='name'] input").setValue(userData.getName());
        $("[data-test-id='phone'] input").setValue(userData.getPhone());
        $("[data-test-id='agreement']").click();
        $("span.button__content").click();
        $(By.cssSelector("div.notification__content")).shouldBe(visible, Duration.ofSeconds(15));
        $(By.cssSelector("[data-test-id='success-notification']")).shouldHave(exactText("Встреча успешно забронирована на 20.04.2024"));
    }

    private void clickRescheduleButton() {
        // Нажать кнопку "Перепланировать"
        // Например:
        $("[data-test-id='button__text']").click();
    }

    private void assertMeetingTimeChanged() {
        // Проверить, что время встречи было изменено
        // Например:
        $(By.cssSelector(".notification__content")).shouldHave(exactText("Встреча успешно запланирована на 27.04.2024"));
    }
}