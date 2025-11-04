package org.vk.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.vk.utilityClasses.LoadableComponent;
import org.vk.wrappers.MicroservicesNavigationToolbar;

import java.util.Objects;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class UserPage extends MicroservicesNavigationToolbar implements LoadableComponent {
    public final MicroservicesNavigationToolbar toolbar;

    private final By FEED = By.xpath(".//*[contains(@class,'feed-list')]");
    private final By USER_NAME = By.xpath(".//*[@data-l='t,userPage']");
    private final By PROFILE_PHOTO_BLOCK = By.xpath(".//*[@class='card_wrp-dailyphoto-wrapper']");
    private final By PROFILE_PHOTO_SOURCE_PAGE_LOCATOR = By.xpath(".//*[contains(@class, 'image-layer_img_w image-wrap')]");
    private final By PHOTO_IMAGE = By.cssSelector("img");

    private String profilePhotoBlockWidth = "200px";
    private String profilePhotoBlockHeight = "200px";

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
        this.toolbar = new MicroservicesNavigationToolbar();
    }

    public boolean checkIsItFeed() {
        return $(FEED).is(visible);
    }

    public boolean checkUserNameVisibility() {
        return $(USER_NAME).is(visible);
    }

    public boolean checkUserNameClickability() {
        return $(USER_NAME).is(clickable);
    }

    public String getUserName() {
        return $(USER_NAME).text();
    }

    public boolean checkProfilePhotoDownloadedAndHasCorrectSize(String photoLink) {
        SelenideElement photoImg = $(PROFILE_PHOTO_BLOCK).$("img");
        return photoImg.shouldBe(visible.because("Profile photo should load and be visible to users"))
                .has(match("src contains photoLink",
                        el -> Objects.requireNonNull(el.getDomAttribute("src")).contains(photoLink))) &&
                photoImg.has(cssValue("width", profilePhotoBlockWidth)) &&
                photoImg.has(cssValue("height", profilePhotoBlockHeight));
    }

    public String checkProfilePhotoSwitching() {
        $(PROFILE_PHOTO_BLOCK).shouldBe(clickable.because("Profile photo should be clickable")).click();
        return $(PROFILE_PHOTO_SOURCE_PAGE_LOCATOR).shouldBe(visible.because("Profile photo on a source page should be visible"))
                .$(PHOTO_IMAGE).getAttribute("src");
    }

}