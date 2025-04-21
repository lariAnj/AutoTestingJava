package org.vk.pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;


public class LoginPage {

    private final By ENTER_ICON = By.xpath(".//*[@class='button-pro __wide']");
    private final By LOGIN_ICON = By.xpath(".//*[@for='field_email']");
    private final By PASSWORD_ICON = By.xpath(".//*[@for='field_password']");
    private final By LOGIN_FIELD = By.xpath(".//*[@name='st.email']");
    private final By PASSWORD_FIELD = By.xpath(".//*[@name='st.password']");
    private final By ERROR_NO_LOGIN = By.xpath(".//*[@class='input-e login_error']");

    public static final String ENTER_BUTTON_TEXT = "Войти в Одноклассники";
    public static final String EMPTY_LOGIN_ERROR_TEXT = "Введите логин";


    public boolean checkEnterIconIsVisible() {
        return $(ENTER_ICON).is(Condition.visible.because("The enter to OK icon is not visible"));
    }

    public boolean checkLoginIconIsVisible() {
        return $(LOGIN_ICON).is(Condition.visible.because("The login icon is not visible"));
    }

    public boolean checkPasswordIconIsVisible() {
        return $(PASSWORD_ICON).is(Condition.visible.because("The password icon is not visible"));
    }

    public String checkEnterTitleText() {
        return $(ENTER_ICON).getValue();
    }

    public LoginPage enterUserData(String email, String password) {
        $(LOGIN_FIELD).setValue(email);
        $(PASSWORD_FIELD).setValue(password);
        return this;
    }

    public UserPage clickEnterButton() {
        $(ENTER_ICON).click();
        return new UserPage();
    }

    public String checkEmptyLoginError() {
        return $(ERROR_NO_LOGIN).shouldBe(Condition.visible.because("The message \"Enter login\" does not appear"))
                .getText();
        }

}