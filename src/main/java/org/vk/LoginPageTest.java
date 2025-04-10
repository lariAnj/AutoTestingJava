package org.vk;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;


@Tag("LoginPage tests")
public class LoginPageTest {

    private String emailEx = "technopol48";
    private String passwordEx = "technopolisPassword";

    @BeforeEach
    public void setup() {
//        Selenide.closeWebDriver();
        Configuration.baseUrl = "https://ok.ru";
        Configuration.timeout = 5000;
        Configuration.browser = "chrome";
        Selenide.open("/");
    }

    @DisplayName("Test to check elements visibility")
    @Tag("UI")
    @Test
    public void testUserCanSeeIconsOnLoginPage() {
        LoginPage loginPage = new LoginPage();
        loginPage.checkLoginIconIsVisible();
        loginPage.checkEnterIconIsVisible();
        loginPage.checkPasswordIconIsVisible();
        loginPage.checkEnterTitleText(LoginPage.enterButtonText);
    }

    @Test
    @DisplayName("Test to check logging in with no login")
    @Tag("functionality")
    @Tag("log in")
    public void testLoginWasNotEntered() {
        LoginPage loginPage = new LoginPage();
        loginPage.enterEmptyLogin(passwordEx);
        loginPage.clickEnterButton();
        loginPage.checkEmptyLoginError(LoginPage.emptyLoginErrorText);

    }

    @Test
    @DisplayName("Test to check logging in with correct data")
    @Tag("functionality")
    @Tag("log in")
    public void testLogIn() {
        LoginPage loginPage = new LoginPage();
        UserPage userPage = new UserPage();
        loginPage.enterUserData(emailEx, passwordEx);
        loginPage.clickEnterButton();
        userPage.checkIsItFeed();
    }

    @AfterEach
    public void exit() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

}
