package org.vk;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginPageTest {

//    LoginPage loginPage = new LoginPage();
//    UserPage userPage = new UserPage();
    private String emailEx = "technopol48";
    private String passwordEx = "technopolisPassword";

    @BeforeEach
    public void setup() {
        Selenide.closeWebDriver();
        Configuration.baseUrl = "https://ok.ru";
        Configuration.timeout = 5000;
        Configuration.browser = "chrome";
        Selenide.open("/");
//        Selenide.clearBrowserCookies();
//        Selenide.clearBrowserLocalStorage();
    }

    @Test
    @Order(1)
    public void testUserCanSeeIconsOnLoginPage() {
        LoginPage loginPage = new LoginPage();
        loginPage.checkLoginIconIsVisible();
        loginPage.checkEnterIconIsVisible();
        loginPage.checkPasswordIconIsVisible();
        loginPage.checkEnterTitleText(LoginPage.enterButtonText);
    }

    @Test
    @Order(3)
    public void testLoginWasNotEntered() {
        LoginPage loginPage = new LoginPage();
        loginPage.enterEmptyLogin(passwordEx);
        loginPage.clickEnterButton();
        loginPage.checkEmptyLoginError(LoginPage.emptyLoginErrorText);

    }

    @Test
    @Order(2)
    public void testLogIn() {
        LoginPage loginPage = new LoginPage();
        UserPage userPage = new UserPage();
        loginPage.enterUserData(emailEx, passwordEx);
        loginPage.clickEnterButton();
        userPage.checkIsItFeed();
    }
}
