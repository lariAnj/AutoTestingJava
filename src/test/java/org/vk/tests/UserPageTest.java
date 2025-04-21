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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

@Tag("UserPage tests")
public class UserPageTest {
    private static String emailEx = "technopol48";
    private static String passwordEx = "technopolisPassword";
    private static String userPageRelUrl = "/feed";
    private String userName = "technopol48 technopol48";
    private String profilePhotoLinkPart = "https://vki9.okcdn.ru/";

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
        Selenide.open(userPageRelUrl);
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
                () -> assertTrue(userPage.toolbar.checkToolbarRowVisibility(), "Toolbar row isn't visible"),
                () ->  assertTrue(userPage.toolbar.checkToolbarRowSize(), "Tollbar row has invalid size")
        );
    }

    @ParameterizedTest(name="{index}")
    @MethodSource("getToolbarIcons")
    @Tag("UI")
    @DisplayName("Test to check toolbar icon")
    void testAllToolbarIconsOnUserPage(SelenideElement icon, UserPage userPage) {
        userPage.toolbar.checkToolbarIconClass(icon);
        assertAll(
                () -> assertTrue(userPage.toolbar.checkToolbarIconVisibility(icon), "Toolbar icon isn't visible"),
                () -> assertTrue(userPage.toolbar.checkToolbarIconClickability(icon), "Toolbar icon isn't clickable")
        );
    }

    private static Stream<Arguments> getToolbarIcons() {
        UserPage userPage = new UserPage();
        Stream<SelenideElement> allIconsStream = userPage.toolbar.getStreamFromToolbarIcons();
        return allIconsStream.map(icon -> Arguments.of(icon, userPage));
    }

    @TestFactory
    @Timeout(value = 5)
    @DisplayName("Dynamic tests with different user actions with toolbar icons")
    Stream<DynamicTest> testUserToolbarIconsActions() {
        return Stream.of(
                DynamicTest.dynamicTest("Click and check messages icon", () -> {
                    Selenide.open(userPageRelUrl);
                    UserPage userPage = new UserPage();
                    MessagesPage messagesPage = userPage.toolbar.clickMessageIcon();
                    assertEquals(messagesPage.MESSAGES_BLOCK_HEADER, messagesPage.getMessagesPageHeader(),
                            "Header isn't \"Сообщения\" - It's not messages page");
                }),

                DynamicTest.dynamicTest("Click and check notification icon", () -> {
                    Selenide.open(userPageRelUrl);
                    UserPage userPage = new UserPage();;
                    NotificationsPage notificationsPage = userPage.toolbar.clickNotificationIcon();
                    assertTrue(notificationsPage.checkIsItNotificationsPage(), "It's not notifications page");
                })
        );
    }


    @DisplayName("Test to check profile photo switches to page with this photo")
    @Tag("UI")
    @Tag("functionality")
    @Test
    public void testProfilePhoto() {
        UserPage userPage = new UserPage();
        assertAll(
                () -> assertTrue(userPage.checkProfilePhotoDownloadedAndHasCorrectSize(profilePhotoLinkPart), "Problem with profile photo displaying"),
                () -> assertThat(userPage.checkProfilePhotoSwitching(), containsString(profilePhotoLinkPart))
        );
    }

    @AfterAll
    public static void exit() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

}
