package org.vk.pages;

import org.openqa.selenium.By;
import org.vk.utilityClasses.TestBot;
import org.vk.utilityClasses.LoadableComponent;
import org.vk.utilityClasses.LoggingPromise;

import java.time.Duration;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;


public class LoginPage implements LoadableComponent {

    private final By ENTER_ICON = By.xpath(".//*[@class='button-pro __wide']");
    private final By LOGIN_ICON = By.xpath(".//*[@for='field_email']");
    private final By PASSWORD_ICON = By.xpath(".//*[@for='field_password']");
    private final By LOGIN_FIELD = By.xpath(".//*[@name='st.email']");
    private final By PASSWORD_FIELD = By.xpath(".//*[@name='st.password']");
    private final By ERROR_NO_LOGIN = By.xpath(".//*[@class='input-e login_error']");

    public static final String ENTER_BUTTON_TEXT = "Войти в Одноклассники";
    public static final String EMPTY_LOGIN_ERROR_TEXT = "Введите логин";

    public boolean isLoaded() throws Error {
        Duration timeout = Duration.ofSeconds(10);
        if (!($(LOGIN_FIELD).is(visible, timeout) &&
                $(PASSWORD_FIELD).is(visible, timeout) &&
                $(ENTER_ICON).is(visible, timeout))) {
            throw new Error("Login page wasn't loaded properly");
        }
        else {
            return true;
        }
    }

    public LoginPage() {
        isLoaded();
    }

    public boolean checkEnterIconIsVisible() {
        return $(ENTER_ICON).is(visible);
    }

    public boolean checkLoginIconIsVisible() {
        return $(LOGIN_ICON).is(visible);
    }

    public boolean checkPasswordIconIsVisible() {
        return $(PASSWORD_ICON).is(visible);
    }

    public String checkEnterTitleText() {
        return $(ENTER_ICON).shouldBe(visible.because("Enter icon isn't visible")).getValue();
    }

    public LoginPage enterUserData(String email, String password) {
        $(LOGIN_FIELD).shouldBe(visible.because("Login field should be visible")).setValue(email);
        $(PASSWORD_FIELD).shouldBe(visible.because("Password field should be visible")).setValue(password);
        return this;
    }

    public LoginPage enterUserData(TestBot testBot) {
        $(LOGIN_FIELD).shouldBe(visible.because("Login field should be visible")).setValue(testBot.getLogin());
        $(PASSWORD_FIELD).shouldBe(visible.because("Password field should be visible")).setValue(testBot.getPassword());
        return this;
    }

    public LoggingPromise clickEnterButton() {
        $(ENTER_ICON).shouldBe(clickable.because("Enter button isn't clickable")).click();
        return new LoggingPromise(this);
    }

    public String checkEmptyLoginError() {
        return $(ERROR_NO_LOGIN).shouldBe(visible.because("The message \"Enter login\" does not appear"))
                .getText();
    }

}