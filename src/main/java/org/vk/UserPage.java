package org.vk;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class UserPage {
    private String feed = "//*[@id=\"hook_Loader_5231597046\"]/div[1]/div[4]/div";

    private SelenideElement getIconObject(String xpath) {
        SelenideElement icon = $(By.xpath(xpath));
        return icon;
    }

    public UserPage checkIsItFeed() {
        getIconObject(feed).shouldBe(Condition.visible);
        return this;
    }
}
