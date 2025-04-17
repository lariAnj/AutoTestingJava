package org.vk.utilityClasses;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.vk.pages.LoadableComponent;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class AlbumsTab extends LoadableComponent<AlbumsTab> {
    private final By PORTLET_NAME_TITLE = By.cssSelector("h3.portlet_h_name_t");
    public final By ALBUMS_BLOCK = By.xpath(".//*[contains(@class, 'photo-vitrine photo-albums') and @id='tabpanel-albums']");
    private String userID;
    public static String albumsSectionHeader = "Альбомы";


    public AlbumsTab(String userID) {
        this.userID = userID;
    }

    @Override
    protected void load() {
        Selenide.open("/profile/" + userID + "/shared");
    }

    @Override
    protected void isLoaded() throws Error {
        $(ALBUMS_BLOCK).should(Condition.exist.because("The albums block wasn't loaded"));
    }

    public String checkNameHeaderVisibility() {
        return $(PORTLET_NAME_TITLE)
                .shouldBe(Condition.visible, Duration.ofSeconds(5)).getText();
    }

}
