package org.vk.utilityClasses;

import org.openqa.selenium.By;
import org.vk.pages.LoginPage;
import org.vk.pages.UserPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoggingPromise {
    private LoginPage curLoginPage;
    private final By USER_PAGE_INDICATOR = By.xpath(".//*[contains(@class,'feed-list')]");


    public LoggingPromise(LoginPage currentLoginPage) {
        this.curLoginPage = currentLoginPage;
    }

    public LoginPage goToLoginPage() {
        if (!($(USER_PAGE_INDICATOR).is(visible))) {
            return curLoginPage;
        }
        throw new IllegalStateException("Expected LoginPage but got UserPage");
    }

    public UserPage goToUserPage() {
        if ($(USER_PAGE_INDICATOR).is(visible)) {
            return new UserPage();
        }
        throw new IllegalStateException("Expected UserPage but got LoginPage");
    }
}