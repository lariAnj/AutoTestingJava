package org.vk.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.vk.TestBot;

import static com.codeborne.selenide.Selenide.$;


public class LoginPage extends LoadableComponent<LoginPage> {

    private final By ENTER_ICON = By.xpath(".//*[@class='button-pro __wide']");
    private final By LOGIN_ICON = By.xpath(".//*[@for='field_email']");
    private final By PASSWORD_ICON = By.xpath(".//*[@for='field_password']");
    private final By LOGIN_FIELD = By.xpath(".//*[@name='st.email']");
    private final By PASSWORD_FIELD = By.xpath(".//*[@name='st.password']");
    private final By ERROR_NO_LOGIN = By.xpath(".//*[@class='input-e login_error']");
    private final By USER_PAGE_INDICATOR = By.xpath(".//*[contains(@class,'feed-list')]");

    public static final String ENTER_BUTTON_TEXT = "Войти в Одноклассники";
    public static final String EMPTY_LOGIN_ERROR_TEXT = "Введите логин";

    @Override
    protected void load() {
        Selenide.open("/");
    }

    @Override
    protected void isLoaded() throws Error {
        $(LOGIN_ICON).shouldBe(Condition.visible.because("The login field wasn't loaded"));
        $(PASSWORD_ICON).shouldBe(Condition.visible.because("The password field wasn't loaded"));
        $(ENTER_ICON).shouldBe(Condition.visible.because("The enter icon wasn't loaded"));
    }

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
        $(LOGIN_FIELD).should(Condition.exist).setValue(email);
        $(PASSWORD_FIELD).should(Condition.exist).setValue(password);
        return this;
    }

    public LoginPage enterUserData(TestBot testBot) {
        $(LOGIN_FIELD).should(Condition.exist).setValue(testBot.getLogin());
        $(PASSWORD_FIELD).should(Condition.exist).setValue(testBot.getPassword());
        return this;
    }

    public LoadableComponent<?> clickEnterButton() {
        $(ENTER_ICON).should(Condition.clickable).click();
        if ($(USER_PAGE_INDICATOR).is(Condition.exist)) {
            return new UserPage().get();
        } else {
            return this;
        }
    }

    public String checkEmptyLoginError() {
        return $(ERROR_NO_LOGIN).shouldBe(Condition.visible.because("The message \"Enter login\" does not appear"))
                .getText();
        }

}