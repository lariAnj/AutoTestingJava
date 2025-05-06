package org.vk.pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class UserPage implements LoadableComponent {
    private final By FEED = By.xpath(".//*[contains(@class,'feed-list')]");

    public boolean isLoaded() throws Error {
        if (!($(FEED).is(visible))) {
                throw new Error("User page wasn't loaded properly");
        }
        else {
            return true;
        }
    }

    public UserPage() {
        isLoaded();
    }

    public boolean checkIsItFeed() {
        return $(FEED).is(visible);
    }
}
