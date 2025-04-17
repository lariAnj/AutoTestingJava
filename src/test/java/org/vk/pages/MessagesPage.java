package org.vk.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class MessagesPage extends LoadableComponent<MessagesPage> {
    private final By MESSAGE_BLOCK = By.xpath(".//*[contains(@class, 'msg-theme-root')]");

    @Override
    protected void load() {
        Selenide.open("/feed/messages");
    }

    @Override
    protected void isLoaded() throws Error {
        $(MESSAGE_BLOCK).should(Condition.exist.because("Messages page wasn't loaded"));
    }

    public boolean checkIsItMessagesPage() {
        return $(MESSAGE_BLOCK).is(Condition.exist.because("Messages page wasn't been opened after clicking message icon"), Duration.ofSeconds(5));
    }




}
