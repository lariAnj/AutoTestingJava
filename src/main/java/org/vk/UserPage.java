package org.vk;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class UserPage {
    private final By FEED = By.xpath("//div/div[@class='feed js-video-scope __header-redesign h-mod']");

    public UserPage checkIsItFeed() {
        $(FEED).shouldBe(Condition.visible);
        return this;
    }
}
