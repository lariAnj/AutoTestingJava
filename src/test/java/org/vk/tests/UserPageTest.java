package org.vk.tests;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.vk.pages.LoginPage;
import org.vk.pages.MessagesPage;
import org.vk.pages.NotificationsPage;
import org.vk.pages.UserPage;

import static org.junit.jupiter.api.Assertions.*;

@Tag("UserPage tests")
public class UserPageTest {
    private static String emailEx = "technopol48";
    private static String passwordEx = "technopolisPassword";
    private String userName = "technopol48 technopol48";
    private String profilePhotoLink = "https://vki9.okcdn.ru/i?r=B1pAm_VFBkioSGBqh1Inn50X1kxoF36lMVmwm2eMKoc23ztLF9KnYgwobRIHmLoj_ZsnPPebiSaRi8Ts3YdZKolm-diC_SYlrhbZZG5YQSGlvW4EC43UVCOi7wAAACk";
    private String profilePhotoSourcePageLink = "https://vki9.okcdn.ru/i?r=B1JAm_VFBkioSGBqh1JaAbc1uHc0-JVPTgWdoY9dw0OuCTobmpKqzujxiYtteh05QWThAwUV3DtM-nO4bK1gJ-kOdvEB9krXEwcOwkMOBQtspFgAAAAp";

    @BeforeAll
    public static void setup() {
        Configuration.baseUrl = "https://ok.ru";
        Configuration.timeout = 5000;
        Configuration.browser = "chrome";
        Selenide.open("/");
        LoginPage loginPage = new LoginPage();
        loginPage.enterUserData(emailEx, passwordEx)
                .clickEnterButton();
    }

    @BeforeEach
    public void getUserFeedPage() {
        Selenide.open("/feed");
    }

    @Test
    @DisplayName("Test to check are we on feed page")
    @Tag("location")
    public void testIsItFeedPage() {
        UserPage userPage = new UserPage();
        assertAll("Check user (feed) page: we should have user name and feed",
                () -> assertEquals(userName, userPage.getUserName(), "User name isn't right"),
                () -> assertTrue(userPage.checkUserNameVisibility(), "User name isn't visible"),
                () -> assertTrue(userPage.checkUserNameClickability(), "User name isn't clickable"),
                () -> assertTrue(userPage.checkIsItFeed(), "It's not a feed - We aren't on user (feed) page")
        );
    }

    @Test
    @DisplayName("Test to check toolbar visibility")
    @Tag("UI")
    public void testToolbarRow() {
        UserPage userPage = new UserPage();
        assertAll(
                () -> assertTrue(userPage.checkToolbarRowVisibility(), "Toolbar row isn't visible"),
                () ->  assertTrue(userPage.checkToolbarRowSize(), "Tollbar row has invalid size")
        );
    }

    @ParameterizedTest(name="{index}")
    @MethodSource("getToolbarIcons")
    @Tag("UI")
    @DisplayName("Test to check toolbar icon")
    void testAllToolbarIconsOnUserPage(SelenideElement icon, UserPage userPage) {
        userPage.checkToolbarIconClass(icon);
        assertAll(
                () -> assertTrue(userPage.checkToolbarIconVisibility(icon), "Toolbar icon isn't visible"),
                () -> assertTrue(userPage.checkToolbarIconClickability(icon), "Toolbar icon isn't clickable")
        );
    }

    private static Stream<Arguments> getToolbarIcons() {
        UserPage userPage = new UserPage();
        Stream<SelenideElement> allIconsStream = userPage.getStreamFromToolbarIcons();
        return allIconsStream.map(icon -> Arguments.of(icon, userPage));
    }

    @TestFactory
    @Timeout(value = 5)
    @DisplayName("Dynamic tests with different user actions with toolbar icons")
    Stream<DynamicTest> testUserToolbarIconsActions() {
        return Stream.of(
                DynamicTest.dynamicTest("Click and check messages icon", () -> {
                    UserPage userPage = UserPage.openUserPage();
                    MessagesPage messagesPage = userPage.clickMessageIcon();
                    assertTrue(messagesPage.checkIsItMessagesPage(), "It's not messages page");
                }),

                DynamicTest.dynamicTest("Click and check notification icon", () -> {
                    UserPage userPage = UserPage.openUserPage();
                    NotificationsPage notificationsPage = userPage.clickNotificationIcon();
                    assertTrue(notificationsPage.checkIsItNotificationsPage(), "It's not notifications page");
                })
        );
    }

    @Disabled("Test is disabled until profile photo wasn't downloaded.")
    @DisplayName("Test to check profile photo switches to page with this photo")
    @Tag("UI")
    @Tag("functionality")
    @Test
    public void testProfilePhoto() {
        UserPage userPage = new UserPage();
        assertAll(
                () -> assertTrue(userPage.checkProfilePhotoDownloadedAndHasCorrectSize(profilePhotoLink), "Problem with profile photo displaying"),
                () -> assertEquals(profilePhotoSourcePageLink, userPage.checkProfilePhotoSwitching())
        );
    }

    @AfterAll
    public static void exit() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

}
