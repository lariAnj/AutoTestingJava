package org.vk;

import com.codeborne.selenide.SelenideElement;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;


public class LoginPage {

    private By enterIcon = By.xpath("//div/input[@class='button-pro __wide']");
    private By loginIcon = By.xpath("//div/span/label[@for='field_email']");
    private By passwordIcon = By.xpath("//div/span/label[@for='field_password']");
    private By loginField = By.xpath("//div/div/input[@name='st.email']");
    private By passwordField = By.xpath("//div/input[@name='st.password']");
    private By errorNoLogin = By.xpath("//div/div[@class='input-e login_error']");

    public static String enterButtonText = "Войти в Одноклассники";
    public static String emptyLoginErrorText = "Введите логин";


    public LoginPage checkEnterIconIsVisible() {
        $(enterIcon).shouldBe(Condition.visible.because("The enter to OK icon is not visible"));
        return this;
    }

    public LoginPage checkLoginIconIsVisible() {
        $(loginIcon).shouldBe(Condition.visible.because("The login icon is not visible"));
        return this;
    }

    public LoginPage checkPasswordIconIsVisible() {
        $(passwordIcon).shouldBe(Condition.visible.because("The password icon is not visible"));
        return this;
    }

    public LoginPage checkEnterTitleText(String expectedText) {
        $(enterIcon).shouldHave(Condition.exactValue(expectedText).because("The text on enter button is not \"Войти в Одноклассники\""));
        return this;
    }

    public LoginPage enterUserData(String email, String password) {
        $(loginField).setValue(email);
        $(passwordField).setValue(password);
        return this;
    }

    public LoginPage clickEnterButton() {
        $(enterIcon).click();
        return this;
    }

    public LoginPage enterEmptyLogin(String password) {
        $(loginField).setValue("");
        $(passwordField).setValue(password);
        return this;
    }

    public LoginPage checkEmptyLoginError(String expectedError) {
        $(errorNoLogin).shouldBe(Condition.visible.because("The message \"Enter login\" does not appear"));
        $(errorNoLogin).shouldHave(Condition.text(expectedError).because("The message of no login error does not have expected text"));
        return this;
    }

}