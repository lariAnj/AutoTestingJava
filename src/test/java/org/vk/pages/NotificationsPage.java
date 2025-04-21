package org.vk.pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class NotificationsPage  {

    private final By NOTIFICATIONS_BLOCK = By.xpath(".//*[contains(@class, 'toolbar-layer __notifs')]");
    private final By GIFT_SECTION_MENU_NAME = By.xpath(".//*[@id='ntf_layer_menu_link_Presents']");
    private final By CONCRETE_NOTIF_CLOSE_BUTTON = By.xpath(".//*[@title='Закрыть' and @type='button' and @data-l='t,cross_close']");

    private final By NOTIFS_HEADER = By.cssSelector(".notifs_header");
    private final By NOTIFICATIONS_TITLE_BLOCK = By.id("hook_Block_NotificationsLayerTitle");
    private final By PORTLET_HEADER = By.cssSelector(".portlet_h");
    private final By PORTLET_NAME_TITLE = By.cssSelector("h3.portlet_h_name_t");
    private final By CONCRETE_NOTIFICATION_BLOCK = By.cssSelector(".h-mod");

    public static String giftsSectionHeader = "Подарки";



    public boolean checkIsItNotificationsPage() {
        return $(NOTIFICATIONS_BLOCK).is(Condition.exist.because("Notifications page wasn't been opened after clicking notification icon"), Duration.ofSeconds(5));
    }

    public NotificationsPage clickGiftSection() {
        $(GIFT_SECTION_MENU_NAME).shouldBe(Condition.clickable.because("Gift section button should be clickable"))
                .click();
        return this;
    }

    public String checkNameHeaderVisibility() {
        return $(NOTIFS_HEADER)
                .$(NOTIFICATIONS_TITLE_BLOCK)
                .$(PORTLET_HEADER)
                .$(PORTLET_NAME_TITLE)
                .shouldBe(Condition.visible).getText();
    }

    public boolean checkConcreteNotifExists() {
        return $(CONCRETE_NOTIFICATION_BLOCK).exists();
    }

    public boolean checkNotifCloseButtonVisibilityAndClickability() {
        return $(CONCRETE_NOTIF_CLOSE_BUTTON).hover().shouldBe(Condition.visible).is(Condition.clickable
                .because("Notification close button should be clickable"));
    }

}
