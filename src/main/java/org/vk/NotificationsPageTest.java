package org.vk;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("NotificationsPage tests")
public class NotificationsPageTest {
    private static String emailEx = "technopol48";
    private static String passwordEx = "technopolisPassword";

    @BeforeAll
    public static void setup() {
        Configuration.baseUrl = "https://ok.ru";
        Configuration.timeout = 5000;
        Configuration.browser = "chrome";
        Selenide.open("/");
        LoginPage loginPage = new LoginPage();
        loginPage.enterUserData(emailEx, passwordEx);
        loginPage.clickEnterButton();
    }


    @Nested
    @Tag("gift notifications")
    @DisplayName("Gifts notifications section tests")
    @Timeout(value = 2, unit = TimeUnit.SECONDS)
    class GiftsNotificationsSectionTests {

        NotificationsPage notificationsPage;

        @BeforeEach
        public void getNotifsPage() {
            Selenide.open("/feed");
            UserPage userPage = new UserPage();
            notificationsPage = userPage.getNotifsPageFromUser(); //return new page
            notificationsPage.clickGiftSection();
        }

        @Test
        @Tag("UI")
        @DisplayName("Check header")
        public void testHeader() {
            notificationsPage.checkNameHeaderVisibility();
        }

        @Test
        @Tag("functionality")
        @DisplayName("Check close notif button")
        public void testCloseButton() {
            assertTrue(notificationsPage.checkConcreteNotifExists());
            notificationsPage.checkNotifCloseButtonVisibility();
        }

    }

}
