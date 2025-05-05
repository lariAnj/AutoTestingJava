package org.vk.pages;

public class LoggingPromise {

    public LoginPage goToLoginPage() {
        return new LoginPage();
    }

    public UserPage goToUserPage() {
        return new UserPage();
    }
}
