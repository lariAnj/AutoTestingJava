package org.vk.pages;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;
import org.vk.decorators.MicroservicesNavigationToolbar;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class UserPage implements LoadableComponent {
    public final MicroservicesNavigationToolbar toolbar;

    private final By FEED = By.xpath(".//*[contains(@class,'feed-list')]");
    private final By USER_NAME = By.xpath(".//*[@data-l='t,userPage']/div[@class='tico ellip']");
    private final By PROFILE_PHOTO_BLOCK = By.xpath(".//*[@class='card_wrp-dailyphoto-wrapper']");
    private final By PROFILE_PHOTO_SOURCE_PAGE_LOCATOR = By.xpath(".//*[contains(@class, 'image-layer_img_w image-wrap')]");
    private final By PHOTO_IMAGE = By.cssSelector("img");

    private String profilePhotoBlockWidth = "200px";
    private String profilePhotoBlockHeight = "200px";

    private static final String USER_PAGE_URL = "/feed";

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

/*    public static UserPage openUserPage() {
        Selenide.open(USER_PAGE_URL);
        return new UserPage();
    }*/

    public boolean checkUserNameVisibility() {
        return $(USER_NAME).is(visible.because("Username should be displayed in profile header"));
    }

    public boolean checkUserNameClickability() {
        return $(USER_NAME).is(clickable.because("Username should be clickable to open profile"));
    }

    public String getUserName() {
        return $(USER_NAME).text();
    }

    /*public boolean checkToolbarRowVisibility() {
        return ($(TOOLBAR_ROW)).is(visible.because("Toolbar row should be visible"));
    }

    public boolean checkToolbarRowSize() {
        try {
            $(TOOLBAR_ROW).shouldHave(cssValue("width", toolbarRowWidth)
                    .because("Toolbar width should be " + toolbarRowWidth))
                    .shouldHave(cssValue("height", toolbarRowHeight)
                            .because("Toolbar height should be " + toolbarRowHeight));
            return true;
        } catch (AssertionError e) {
            return false;
        }
    }

    public Stream<SelenideElement> getStreamFromToolbarIcons() {
        Stream<SelenideElement> allIconsStream = Stream.of(
                $(MESSAGE_ICON),
                $(DISCUSSIONS_ICON),
                $(NOTIFICATIONS_ICON),
                $(GUESTS_ICON),
                $(MARKS_ICON),
                $(VIDEO_ICON),
                $(MUSIC_ICON),
                $(TODAY_ICON)
        );
        return allIconsStream;
    }

    public boolean checkToolbarIconVisibility(SelenideElement icon) {
        return icon.is(visible.because("Toolbar icon should be visible to users"));
    }

    public UserPage checkToolbarIconClass(SelenideElement icon) {
        icon.shouldHave(attributeMatching("class", ".*toolbar_nav_i.*"));
        return this;
    }

    public boolean checkToolbarIconClickability(SelenideElement icon) {
        return icon.is(clickable.because("Toolbar icon should be clickable"));
    }*/

    /*public MessagesPage clickMessageIcon() {
        $(MESSAGE_ICON).shouldBe(clickable).click();
        return new MessagesPage();
    }

    public NotificationsPage clickNotificationIcon() {
        $(NOTIFICATIONS_ICON).shouldBe(clickable).click();
        return new NotificationsPage();
    }*/

    public boolean checkProfilePhotoDownloadedAndHasCorrectSize(String photoLink) {
        try {
            $(PROFILE_PHOTO_BLOCK).$("img").shouldBe(visible.because("Profile photo should load and be visible to users"))
                    .shouldHave(attribute("src", photoLink)
                            .because("Profile photo should match expected URL: " + photoLink))
                    .shouldHave(cssValue("width", profilePhotoBlockWidth)
                            .because("Profile photo width should be " + profilePhotoBlockWidth))
                    .shouldHave(cssValue("height", profilePhotoBlockHeight)
                            .because("Profile photo height should be " + profilePhotoBlockHeight));
            return true;
        } catch (AssertionError e) {
            return false;
        }
    }

    public String checkProfilePhotoSwitching() {
        $(PROFILE_PHOTO_BLOCK).click();
        return $(PROFILE_PHOTO_SOURCE_PAGE_LOCATOR).shouldBe(visible)
                .$(PHOTO_IMAGE).getAttribute("src");
        }

    public NotificationsPage getNotifsPageFromUser() {
        $(toolbar.NOTIFICATIONS_ICON).shouldBe(clickable.because("Notifs icon should be clickable")).click();
        return new NotificationsPage();
    }

}
