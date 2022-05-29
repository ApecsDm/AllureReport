package ru.netology.delivery.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {
    private final DataGenerator.UserInfo validUser = DataGenerator.Registration.generateUser();
    private final int daysToAddForFirstMeeting = 4;
    private final String firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);

    @BeforeEach
    void setup() {
        open("http://localhost:9999/");
    }

    @Test
    @DisplayName("Should successful plan meeting")
    void shouldSuccessfulPlanMeeting() {
        $("[data-test-id=city] input").setValue(validUser.getCity());
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(firstMeetingDate);
        $("[data-test-id=name] input").setValue(validUser.getName());
        $("[data-test-id=phone] input").setValue(validUser.getPhone());
        $("[data-test-id=agreement").click();
        $(byText("Запланировать")).click();
        $(withText("Успешно!")).shouldBe(visible);;
        $("[data-test-id=success-notification] div.notification__content").shouldHave(exactText("Встреча успешно запланирована на " + firstMeetingDate));
    }

    @Test
    @DisplayName("Should successful replan meeting")
    void shouldSuccessfulReplanMeeting() {
        $("[data-test-id=city] input").setValue(validUser.getCity());
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(firstMeetingDate);
        $("[data-test-id=name] input").setValue(validUser.getName());
        $("[data-test-id=phone] input").setValue(validUser.getPhone());
        $("[data-test-id=agreement").click();
        $(byText("Запланировать")).click();
        $("[data-test-id=success-notification] div.notification__title").shouldHave(exactText("Успешно!"));
        $("[data-test-id=success-notification] div.notification__content").shouldHave(exactText("Встреча успешно запланирована на " + firstMeetingDate));
        $(byText("Запланировать")).click();
        $(byText("У вас уже запланирована встреча на другую дату. Перепланировать?")).shouldBe(visible);
        $(byText("Перепланировать")).click();
        $("[data-test-id=success-notification] div.notification__title").shouldHave(exactText("Успешно!"));
        $("[data-test-id=success-notification] div.notification__content").shouldHave(exactText("Встреча успешно запланирована на " + firstMeetingDate));
    }
}
