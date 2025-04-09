package org.vk;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;


public interface MicroservicesNavigationToolbar {

//    public By allToolbarIconsRowElement = By.xpath("//div/div[@class='topPanel' and @id='topPanel']");
    public final By TOOLBAR_ROW = By.xpath("//div/div/div[@class='toolbar_c' and @data-l='t,navigationToolbar']");
    public final By MESSAGE_ICON = By.xpath("//nav/ul/li[@data-l='t,messages']");
    public final By DISCUSSIONS_ICON = By.xpath("//nav/ul/li[@data-l='t,discussions']");
    public final By NOTIFICATIONS_ICON = By.xpath("//nav/ul/li[@data-l='t,notifications']");
    public final By GUESTS_ICON = By.xpath("//nav/ul/li[@data-l='t,guests']");
    public final By MARKS_ICON = By.xpath("//nav/ul/li[@data-l='t,marks']");
    public final By VIDEO_ICON = By.xpath("//nav/ul/li[@data-l='t,video']");
    public final By MUSIC_ICON = By.xpath("//nav/ul/li[@data-l='t,music']");
    public final By TODAY_ICON = By.xpath("//nav/ul/li[@data-l='t,today']");

    public String toolbarRowWidth = "996px";
    public String toolbarRowHeight = "48px";


    public UserPage checkToolbarRowVisability();
    public UserPage checkToolbarRowSize();
//    public Stream<SelenideElement> getStreamFromToolbarIcons();
    public UserPage checkToolbarIconVisability(SelenideElement icon);
    public UserPage checkToolbarIconClass(SelenideElement icon);

}
