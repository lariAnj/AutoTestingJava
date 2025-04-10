package org.vk;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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
        loginPage.enterUserData(emailEx, passwordEx);
        loginPage.clickEnterButton();
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
                () -> assertEquals(userName, userPage.getUserName()),
                () -> assertTrue(userPage.checkUserNameVisibility()),
                () -> assertTrue(userPage.checkUserNameClickability()),
                () -> userPage.checkIsItFeed()
        );
    }

    @Test
    @DisplayName("Test to check toolbar visibility")
    @Tag("UI")
    public void testToolbarRow() {
        UserPage userPage = new UserPage();
        userPage.checkToolbarRowVisibility();
        userPage.checkToolbarRowSize();
    }

    @ParameterizedTest(name="{index}-icon: {2}")
    @MethodSource("getToolbarIcons")
    @Tag("UI")
    @DisplayName("Test to check toolbar icon")
    void testAllToolbarIconsOnUserPage(SelenideElement icon, UserPage userPage, String iconDescription) {
        userPage.checkToolbarIconVisibility(icon);
        userPage.checkToolbarIconClass(icon);
        userPage.checkToolbarIconsclickability(icon);
    }

    private static Stream<Arguments> getToolbarIcons() {
        UserPage userPage = new UserPage();
        Stream<SelenideElement> allIconsStream = userPage.getStreamFromToolbarIcons();
        return allIconsStream.map(icon -> Arguments.of(icon, userPage, icon.getAttribute("data-l")));
    }

    @Disabled("Test is disabled until profile photo wasn't downloaded.")
    @DisplayName("Test to check profile photo switches to page with this photo")
    @Tag("UI")
    @Tag("functionality")
    @Test
    public void testProfilePhoto() {
        UserPage userPage = new UserPage();
        userPage.checkProfilePhotoDownloadedAndHasCorrectSize(profilePhotoLink);
        userPage.checkProfilePhotoSwitching(profilePhotoSourcePageLink);
    }

    @AfterAll
    public static void exit() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }
}
