package org.vk;

import com.codeborne.selenide.SelenideElement;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;


public class LoginPage {


    private String enterIcon = "//*[@id=\"tabpanel-login-4717594832\"]/form/div[4]/input";
    private String loginIcon = "//*[@id=\"tabpanel-login-4717594832\"]/form/div[1]/span/label";
    private String passwordIcon = "//*[@id=\"tabpanel-login-4717594832\"]/form/div[2]/span";
    private String loginField = "//*[@id=\"field_email\"]";
    private String passwordField = "//*[@id=\"field_password\"]";
    private String feed = "//*[@id=\"hook_Loader_5231597046\"]/div[1]/div[4]/div";
    private String errorNoLogin = "//*[@id=\"tabpanel-login-6577160363\"]/form/div[3]/div";



    public void openPage() {
        Selenide.open("/");
    }

    private SelenideElement getIconObject(String xpath) {
        SelenideElement icon = $(xpath);
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
        getIconObject(enterIcon).shouldHave(Condition.exactText(expectedText));
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

    public LoginPage checkIsItFeed() {
        getIconObject(feed).shouldBe(Condition.visible);
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