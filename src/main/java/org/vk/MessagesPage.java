package org.vk;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class MessagesPage {
    private final By MESSAGE_BLOCK = By.xpath("//msg-app/div[@class='js-overlays-host js-msg-theme-root __theming-enabled-okmsg']");

    public MessagesPage checkIsItMessagesPage() {
        $(MESSAGE_BLOCK).should(Condition.exist.because("Messages page wasn't been opened after clicking message icon"));
        return this;
    }




}
