package org.vk.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import org.vk.pages.LoginPage;
import org.vk.pages.NotificationsPage;
import org.vk.pages.UserPage;


import static org.junit.jupiter.api.Assertions.*;

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
        LoginPage loginPage = new LoginPage().get();
        loginPage.enterUserData(emailEx, passwordEx)
                .clickEnterButton();
    }


    @Nested
    @Tag("gift notifications")
    @DisplayName("Gifts notifications section tests")
    @Timeout(value = 8)
    class GiftsNotificationsSectionTests {

        NotificationsPage notificationsPage;

        @BeforeEach
        public void getNotifsPage() {
            Selenide.open("/feed");
            UserPage userPage = new UserPage().get();
            //return new page
            notificationsPage = userPage.getNotifsPageFromUser()
                    .clickGiftSection();
        }

        @Test
        @Tag("UI")
        @DisplayName("Check header")
        public void testHeader() {
            assertEquals(NotificationsPage.giftsSectionHeader,
                    notificationsPage.checkNameHeaderVisibility(),
                    "Header isn't \"Подарки\"");
        }

        @Test
        @Tag("functionality")
        @DisplayName("Check close notif button")
        public void testCloseButton() {
            assertAll(
                    () -> assertTrue(notificationsPage.checkConcreteNotifExists(), "No one notification exists"),
                    () -> assertTrue(notificationsPage.checkNotifCloseButtonVisibilityAndClickability(), "Close button for notification isn't visible")
            );
        }

    }

    @AfterAll
    public static void closeBrowser() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

}
