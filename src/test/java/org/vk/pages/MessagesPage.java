package org.vk.pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class MessagesPage {
    private final By MESSAGE_BLOCK = By.xpath(".//*[contains(@class, 'msg-theme-root')]");

    public boolean checkIsItMessagesPage() {
        return $(MESSAGE_BLOCK).is(Condition.exist.because("Messages page wasn't been opened after clicking message icon"), Duration.ofSeconds(5));
    }




}
