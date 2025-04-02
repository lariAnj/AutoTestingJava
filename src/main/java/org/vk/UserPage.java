package org.vk;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class UserPage {
    private String feed = "//div/div[@class='feed js-video-scope __header-redesign h-mod']";

    private SelenideElement getIconObject(String xpath) {
        SelenideElement icon = $(By.xpath(xpath));
        return icon;
    }

    public UserPage checkIsItFeed() {
        getIconObject(feed).shouldBe(Condition.visible);
        return this;
    }
}
