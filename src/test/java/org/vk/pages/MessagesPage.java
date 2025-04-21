package org.vk.pages;

import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MessagesPage implements LoadableComponent {
    private final By MESSAGE_BLOCK = By.xpath(".//*[contains(@data-l, 't,msg')]");
    private final By MESSAGE_BLOCK_TOOLBAR = By.cssSelector("msg-info-sheet msg-toolbar");

    public final String MESSAGES_BLOCK_HEADER = "Сообщения";

    public boolean isLoaded() throws Error {
        if (!($(MESSAGE_BLOCK).is(visible, Duration.ofSeconds(5)))) {
            throw new Error("Messages page wasn't loaded properly");
        }
        else {
            return true;
        }
    }

    public MessagesPage() {
        isLoaded();
    }

    public String getMessagesPageHeader() {
        return $(MESSAGE_BLOCK).$(MESSAGE_BLOCK_TOOLBAR).shouldBe(visible.because("Messages block header isn't visible")).getText();
    }




}
