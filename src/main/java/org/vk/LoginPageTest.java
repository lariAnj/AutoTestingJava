package org.vk;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class LoginPageTest {

    LoginPage loginPage = new LoginPage();
    private String emailEx = "nouser@mail.ru";
    private String passwordEx = "12345";

    @BeforeAll
    public static void setup() {
        Configuration.baseUrl = "https://ok.ru";
        Configuration.timeout = 10000;
        Configuration.browser = "chrome";
    }

    @Test
    public void userCanSeeIconsOnLoginPage() {
        loginPage.openPage();
        loginPage.checkLoginIconIsVisible();
        loginPage.checkEnterIconIsVisible();
        loginPage.checkPasswordIconIsVisible();
        loginPage.checkEnterTitleText("Войти в Одноклассники");
    }

    @Test
    public void testLoginWasNotEntered() {
        String password = passwordEx;
        loginPage.enterEmptyLogin(password);
        loginPage.clickEnterButtonWithNoLogin();
        loginPage.checkEmptyLoginError("Введите логин");

    }

    @Test
    public void testLogIn() {
        String email = emailEx;
        String password = passwordEx;
        loginPage.enterUserData(email, password);
        loginPage.clickEnterButton();
        loginPage.checkIsItFeed();
    }
}
