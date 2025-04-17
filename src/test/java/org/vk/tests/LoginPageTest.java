package org.vk.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import org.vk.TestBot;
import org.vk.pages.LoginPage;
import org.vk.pages.UserPage;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Tag("LoginPage tests")
public class LoginPageTest {

    private String emailEx = "technopol48";
    private String passwordEx = "technopolisPassword";
    private TestBot testBot1 = new TestBot("technopol48", "technopolisPassword");
    private TestBot testBotWithEmptyLogin = new TestBot("", "\"technopolisPassword\"");

    @BeforeEach
    public void setup() {
        Configuration.baseUrl = "https://ok.ru";
        Configuration.timeout = 5000;
        Configuration.browser = "chrome";
        Selenide.open("/");
    }

    @DisplayName("Test to check elements visibility")
    @Tag("UI")
    @Test
    public void testUserCanSeeIconsOnLoginPage() {
        LoginPage loginPage = new LoginPage().get();
        assertAll("Check elements on login page: user should see login, password, enter icon and text on enter button",
                () -> assertTrue(loginPage.checkLoginIconIsVisible(), "Login icon isn't visible"),
                () -> assertTrue(loginPage.checkEnterIconIsVisible(), "Enter icon isn't visible"),
                () -> assertTrue(loginPage.checkPasswordIconIsVisible(), "Password icon isn't visible"),
                () -> assertEquals(LoginPage.ENTER_BUTTON_TEXT, loginPage.checkEnterTitleText(),"Enter button has invalid text")
        );
    }

    @Test
    @DisplayName("Test to check logging in with no login")
    @Tag("functionality")
    @Tag("log in")
    public void testLoginWasNotEntered() {
        LoginPage loginPage = new LoginPage();
        loginPage.enterUserData("", passwordEx)
                .clickEnterButton();
        assertEquals(LoginPage.EMPTY_LOGIN_ERROR_TEXT, loginPage.checkEmptyLoginError(), "Empty login error wasn't shown");

    }

    @Test
    @DisplayName("Test to check logging in using TestBot with no login")
    @Tag("functionality")
    @Tag("log in")
    public void testLoginWasNotEnteredWithBot() {
        LoginPage loginPage = new LoginPage();
        loginPage.enterUserData(testBotWithEmptyLogin)
                .clickEnterButton();
        assertEquals(LoginPage.EMPTY_LOGIN_ERROR_TEXT, loginPage.checkEmptyLoginError(), "Empty login error wasn't shown");

    }

    @Test
    @DisplayName("Test to check logging in using TestBot with correct data")
    @Tag("functionality")
    @Tag("log in")
    public void testLogIn() {
        LoginPage loginPage = new LoginPage();
        loginPage.enterUserData(testBot1);
        UserPage userPage  = (UserPage) loginPage.clickEnterButton();
        assertTrue(userPage.checkIsItFeed(), "It's not a feed - We aren't on user (feed) page");
    }

    @Test
    @DisplayName("Test to check logging in with correct data")
    @Tag("functionality")
    @Tag("log in")
    public void testLogInWithBot() {
        LoginPage loginPage = new LoginPage();
        loginPage.enterUserData(emailEx, passwordEx);
        UserPage userPage  = (UserPage) loginPage.clickEnterButton();
        assertTrue(userPage.checkIsItFeed(), "It's not a feed - We aren't on user (feed) page");
    }

    @AfterEach
    public void exit() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }


}
