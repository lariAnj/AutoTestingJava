package org.vk.decorators;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.vk.pages.MessagesPage;
import org.vk.pages.NotificationsPage;
import org.vk.pages.UserPage;


public interface MicroservicesNavigationToolbar {

    By TOOLBAR_ROW = By.xpath(".//*[@class='toolbar_c' and @data-l='t,navigationToolbar']");
    By MESSAGE_ICON = By.xpath(".//*[@data-l='t,messages']");
    By DISCUSSIONS_ICON = By.xpath(".//*[@data-l='t,discussions']");
    By NOTIFICATIONS_ICON = By.xpath(".//*[@data-l='t,notifications']");
    By GUESTS_ICON = By.xpath(".//*[@data-l='t,guests']");
    By MARKS_ICON = By.xpath(".//*[@data-l='t,marks']");
    By VIDEO_ICON = By.xpath(".//*[@data-l='t,video']");
    By MUSIC_ICON = By.xpath(".//*[@data-l='t,music']");
    By TODAY_ICON = By.xpath(".//*[@data-l='t,today']");

    String toolbarRowWidth = "996px";
    String toolbarRowHeight = "48px";


    boolean checkToolbarRowVisibility();
    boolean checkToolbarRowSize();
    boolean checkToolbarIconVisibility(SelenideElement icon);
    UserPage checkToolbarIconClass(SelenideElement icon);
    boolean checkToolbarIconClickability(SelenideElement icon);

    MessagesPage clickMessageIcon();
    NotificationsPage clickNotificationIcon();

}
