package org.vk.decorators;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.vk.pages.MessagesPage;
import org.vk.pages.NotificationsPage;
import org.vk.pages.UserPage;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Selenide.$;


public class MicroservicesNavigationToolbar {

    private final By TOOLBAR_ROW = By.xpath(".//*[@class='toolbar_c' and @data-l='t,navigationToolbar']");
    private final By MESSAGE_ICON = By.xpath(".//*[@data-l='t,messages']");
    private final By DISCUSSIONS_ICON = By.xpath(".//*[@data-l='t,discussions']");
    public final By NOTIFICATIONS_ICON = By.xpath(".//*[@data-l='t,notifications']");
    private final By GUESTS_ICON = By.xpath(".//*[@data-l='t,guests']");
    private final By MARKS_ICON = By.xpath(".//*[@data-l='t,marks']");
    private final By VIDEO_ICON = By.xpath(".//*[@data-l='t,video']");
    private final By MUSIC_ICON = By.xpath(".//*[@data-l='t,music']");
    private final By TODAY_ICON = By.xpath(".//*[@data-l='t,today']");

    private String toolbarRowWidth = "996px";
    private String toolbarRowHeight = "48px";


    /*boolean checkToolbarRowVisibility();
    boolean checkToolbarRowSize();
    boolean checkToolbarIconVisibility(SelenideElement icon);
    UserPage checkToolbarIconClass(SelenideElement icon);
    boolean checkToolbarIconClickability(SelenideElement icon);
*/
    public boolean checkToolbarRowVisibility() {
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

    public MicroservicesNavigationToolbar checkToolbarIconClass(SelenideElement icon) {
        icon.shouldHave(attributeMatching("class", ".*toolbar_nav_i.*"));
        return this;
    }

    public boolean checkToolbarIconClickability(SelenideElement icon) {
        return icon.is(clickable.because("Toolbar icon should be clickable"));
    }


/*    MessagesPage clickMessageIcon();
    NotificationsPage clickNotificationIcon();*/

    public MessagesPage clickMessageIcon() {
        $(MESSAGE_ICON).shouldBe(clickable).click();
        return new MessagesPage();
    }

    public NotificationsPage clickNotificationIcon() {
        $(NOTIFICATIONS_ICON).shouldBe(clickable).click();
        return new NotificationsPage();
    }

}
