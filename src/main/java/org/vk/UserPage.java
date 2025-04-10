package org.vk;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$$;

public class UserPage implements MicroservicesNavigationToolbar{
    private final By FEED = By.xpath("//div/div[@class='feed js-video-scope __header-redesign h-mod']");
    private final By USER_NAME = By.xpath("//div/a/div[@class='tico ellip']");
//    private final By PROFILE_PHOTO_MENU = By.xpath("//div/div[@class='sc-menu lcTc_actions __l __left sc-menu__hidden']");
    private final By PROFILE_PHOTO_BLOCK = By.xpath("//div/div[@class='card_wrp-dailyphoto-wrapper']");
    private final By PROFILE_PHOTO_SOURCE_PAGE_LOCATOR = By.xpath("//div/div/div[@class='image-layer_img_w image-wrap__qsj1g']");

    private String profilePhotoBlockWidth = "200px";
    private String profilePhotoBlockHeight = "200px";


    public UserPage checkIsItFeed() {
        $(FEED).shouldBe(Condition.visible);
        return this;
    }

    public boolean checkUserNameVisibility() {
        $(USER_NAME).shouldBe(visible);
        return true;
    }

    public boolean checkUserNameClickability() {
        $(USER_NAME).shouldBe(clickable);
        return true;
    }

    public String getUserName() {
        String name = $(USER_NAME).text();
        return name;
    }

    public UserPage checkToolbarRowVisibility() {
        ($(TOOLBAR_ROW)).shouldBe(visible);
        return this;
    }

    public UserPage checkToolbarRowSize() {
        ($(TOOLBAR_ROW)).shouldHave(cssValue("width",toolbarRowWidth));
        ($(TOOLBAR_ROW)).shouldHave(cssValue("height",toolbarRowHeight));
        return this;
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

/*    public List<SelenideElement> getToolbarIconsInList() {
        ElementsCollection allIcons = getAllIconsOnToolbar();
        List<SelenideElement> toolbarIconsInList = allIcons.stream().collect(Collectors.toList());
        return toolbarIconsInList;
    }*/

    public UserPage checkToolbarIconVisibility(SelenideElement icon) {
        icon.shouldBe(visible);
        return this;
    }

    public UserPage checkToolbarIconClass(SelenideElement icon) {
        /*icon.should(Condition.match("Icon @class contains 'toolbar_nav_i'.",
                el -> el.getAttribute("class").contains("toolbar_nav_i")
        ));*/
        icon.shouldHave(attributeMatching("class", ".*toolbar_nav_i.*"));
        return this;
    }

    public UserPage checkToolbarIconsclickability(SelenideElement icon) {
        icon.shouldBe(clickable);
        return this;
    }

    public UserPage checkProfilePhotoDownloadedAndHasCorrectSize(String photoLink) {
        $(PROFILE_PHOTO_BLOCK).$("img").shouldBe(visible)
                .shouldHave(attribute("src", photoLink))
                .shouldHave(cssValue("width", profilePhotoBlockWidth))
                .shouldHave(cssValue("height", profilePhotoBlockHeight));
        return this;
    }

    public UserPage checkProfilePhotoSwitching(String photoLink) {
        $(PROFILE_PHOTO_BLOCK).click();
        $(PROFILE_PHOTO_SOURCE_PAGE_LOCATOR).shouldBe(visible)
                .$("img").shouldHave(attribute("src", photoLink));
        return this;
    }

}
