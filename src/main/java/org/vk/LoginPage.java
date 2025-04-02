package org.vk;

import com.codeborne.selenide.SelenideElement;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;


public class LoginPage {

    private String enterIcon = "//div/input[@class='button-pro __wide']";
    private String loginIcon = "//div/span/label[@for='field_email']";
    private String passwordIcon = "//div/span/label[@for='field_password']";
    private String loginField = "//div/div/input[@name='st.email']";
    private String passwordField = "//div/input[@name='st.password']";
    private String errorNoLogin = "//div/div[@class='input-e login_error']";


    private SelenideElement getIconObject(String xpath) {
        SelenideElement icon = $(By.xpath(xpath));
        return icon;
    }

    public LoginPage checkEnterIconIsVisible() {
        getIconObject(enterIcon).shouldBe(Condition.visible);
        return this;
    }

    public LoginPage checkLoginIconIsVisible() {
        getIconObject(loginIcon).shouldBe(Condition.visible);
        return this;
    }

    public LoginPage checkPasswordIconIsVisible() {
        getIconObject(passwordIcon).shouldBe(Condition.visible);
        return this;
    }

    public LoginPage checkEnterTitleText(String expectedText) {
        getIconObject(enterIcon).shouldHave(Condition.exactValue(expectedText));
        return this;
    }

    public LoginPage enterUserData(String email, String password) {
        getIconObject(loginField).setValue(email);
        getIconObject(passwordField).setValue(password);
        return this;
    }

    public LoginPage clickEnterButton() {
        getIconObject(enterIcon).click();
        return this;
    }

    public LoginPage enterEmptyLogin(String password) {
        getIconObject(loginField).setValue("");
        getIconObject(passwordField).setValue(password);
        return this;
    }

    public LoginPage clickEnterButtonWithNoLogin() {
        getIconObject(enterIcon).click();
        return this;
    }

    public LoginPage checkEmptyLoginError(String expectedError) {
        getIconObject(errorNoLogin).shouldBe(Condition.visible);
        getIconObject(errorNoLogin).shouldHave(Condition.text(expectedError));
        return this;
    }

}