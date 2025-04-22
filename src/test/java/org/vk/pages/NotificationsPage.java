package org.vk.pages;

import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class NotificationsPage implements LoadableComponent {

    private final By NOTIFICATIONS_BLOCK = By.xpath(".//*[contains(@data-l,'t,notificationsLayer')]");
    private final By GIFT_SECTION_MENU_NAME = By.xpath(".//*[@id='ntf_layer_menu_link_Presents']");
    private final By NOTIFS_BLOCK_CLOSE_BUTTON = By.xpath(".//*[@data-l='t,close-control']");
    private final By CONCRETE_NOTIF_CLOSE_BUTTON = By.xpath(".//*[@data-l='t,cross_close']");

    private final By GIFTS_ICON_LOCATOR = By.cssSelector("svg");
    private final By NOTIFS_HEADER = By.cssSelector(".notifs_header");
    private final By NOTIFICATIONS_TITLE_BLOCK = By.id("hook_Block_NotificationsLayerTitle");
    private final By PORTLET_HEADER = By.cssSelector(".portlet_h");
    private final By PORTLET_NAME_TITLE = By.cssSelector("h3.portlet_h_name_t");
    private final By CONCRETE_NOTIFICATION_BLOCK = By.cssSelector(".h-mod");

    public static String giftsSectionHeader = "Подарки";
    private String activatedIconIndicator = ".*colorfilled.*";

    public boolean isLoaded() throws Error {
        if (!($(NOTIFICATIONS_BLOCK).is(visible, Duration.ofSeconds(5)))) {
            throw new Error("Notifications page wasn't loaded properly");
        }
        else {
            return true;
        }
    }

    public NotificationsPage() {
        isLoaded();
    }

    public boolean checkIsItNotificationsPage() {
        return $(NOTIFICATIONS_BLOCK).is(visible, Duration.ofSeconds(5));
    }

    public NotificationsPage clickGiftSection() {
        System.out.println("gift section trans");
        $(GIFT_SECTION_MENU_NAME).shouldBe(clickable.because("Gift section button should be clickable"))
                .click();
        return this;
    }

    public boolean checkGiftsIconActivation() {
        return $(GIFT_SECTION_MENU_NAME).$(GIFTS_ICON_LOCATOR).shouldHave(attributeMatching("class", activatedIconIndicator))
                .attr("class").contains("colorfilled");
    }

    public String checkNameHeaderVisibility() {
        return $(NOTIFS_HEADER)
                .$(NOTIFICATIONS_TITLE_BLOCK)
                .$(PORTLET_HEADER)
                .$(PORTLET_NAME_TITLE)
                .shouldBe(visible.because("Notifications block header isn't visible")).getText();
    }

    public boolean checkNotifsBlockCloseButtonClickabilityAndVisibility() {
        return $(NOTIFS_BLOCK_CLOSE_BUTTON).shouldBe(visible.
                because("Notifs block close button isn't visible")).is(clickable);
    }


    public boolean checkNotifsBlockCloseButtonSize() {
        return $(NOTIFS_BLOCK_CLOSE_BUTTON).has(cssValue("width", "16px")) &&
                $(NOTIFS_BLOCK_CLOSE_BUTTON).has(cssValue("height", "16px"));
    }

    public boolean checkConcreteNotifExists() {
        return $(CONCRETE_NOTIFICATION_BLOCK).exists();
    }

    public boolean checkNotifCloseButtonVisibilityAndClickability() {
        return $(CONCRETE_NOTIF_CLOSE_BUTTON).hover().shouldBe(visible.
                because("Concrete notif close button isn't visible")).is(clickable);
    }

}
