package org.vk;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

public class UserPageTest {
    private static String emailEx = "technopol48";
    private static String passwordEx = "technopolisPassword";
    private String userName = "technopol48 technopol48";

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

    @BeforeEach
    public void getUserFeedPage() {
        Selenide.open("/feed");
    }

    @Test
    public void testIsItFeedPage() {
        UserPage userPage = new UserPage();
        assertAll("Check user (feed) page: we should have user name and feed",
                () -> assertEquals(userName, userPage.getUserName()),
                () -> assertTrue(userPage.checkUserNameVisability()),
                () -> assertTrue(userPage.checkUserNameClickability()),
                () -> userPage.checkIsItFeed()
        );
    }

    @Test
    public void testToolbarRow() {
        UserPage userPage = new UserPage();
        userPage.checkToolbarRowVisability();
        userPage.checkToolbarRowSize();
    }

    @ParameterizedTest
    @MethodSource("getToolbarIcons")
    void testAllToolbarIconsOnUserPage(SelenideElement icon, UserPage userPage) {
        userPage.checkToolbarIconVisability(icon);
        userPage.checkToolbarIconClass(icon);
    }

    private static Stream<Arguments> getToolbarIcons() {
        UserPage userPage = new UserPage();
        Stream<SelenideElement> allIconsStream = userPage.getStreamFromToolbarIcons();
        return allIconsStream.map(icon -> Arguments.of(icon, userPage));
    }

    @AfterAll
    public static void exit() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }
}
