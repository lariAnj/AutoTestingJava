package org.vk;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.conditions.Not;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class NotificationsPage {

    private final By NOTIFS_ICON = By.xpath("//nav/ul/li[@data-l='t,notifications']");
    private final By NOTIFICATIONS_BLOCK = By.xpath("//div/div[@class='toolbar-layer __notifs __with-inner-config-link']");
    private final By ALL_SECTION_NEW_NOTIFS_HEADER = By.xpath("//div/div/div[@class='portlet_h']");
    private final By ALL_SECTION_VIEWED_NOTIFS_HEADER = By.xpath("//div/div[@class='portlet_h_name_t']");
    private final By GIFT_SECTION_MENU_NAME = By.xpath("//div/div/a[@id='ntf_layer_menu_link_Presents']");
    private final By CONCRETE_NOTIF_CLOSE_BUTTON = By.xpath("//button[@title='Закрыть' and @type='button' and @data-l='t,cross_close']");

    public NotificationsPage checkIsItNotificationsPage() {
        $(NOTIFICATIONS_BLOCK).should(Condition.exist.because("Notifications page wasn't been opened after clicking notification icon"));
        return this;
    }

    public NotificationsPage clickGiftSection() {
        $(GIFT_SECTION_MENU_NAME).click();
        return this;
    }

    public NotificationsPage checkNameHeaderVisibility() {
        $(".notifs_header").$("#hook_Block_NotificationsLayerTitle").$(".portlet_h").$("h3.portlet_h_name_t").shouldHave(Condition.text("Подарки"));
        return this;
    }

    public boolean checkConcreteNotifExists() {
        return $(".h-mod").exists();
    }

    public NotificationsPage checkNotifCloseButtonVisibility() {
        $(CONCRETE_NOTIF_CLOSE_BUTTON).hover().shouldBe(Condition.clickable);
        return this;
    }

}
