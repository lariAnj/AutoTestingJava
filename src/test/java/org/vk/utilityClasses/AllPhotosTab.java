package org.vk.utilityClasses;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.vk.pages.LoadableComponent;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class AllPhotosTab extends LoadableComponent<AllPhotosTab> {
    public final By ALL_PHOTOS_PANEL = By.xpath(".//*[@class='portlet photo-stream']");
    public final By PHOTO_VITRINE_PANEL = By.xpath(".//*[contains(@class, 'photo-vitrine tech-albums')]");
    public final By ALBUMS_PANEL = By.xpath(".//*[@id='tabpanel-all-photos']");
    private final By ALL_PHOTOS_HEADER_LOCATION = By.xpath(".//*[contains(@class,'heading__h3')]");

    public static String allPhotosSectionHeader = "Все фото";
    private String userID;


    public AllPhotosTab(String userID) {
        this.userID = userID;
    }

    @Override
    protected void load() {
        Selenide.open("/profile/" + userID + "/photos");
    }

    @Override
    protected void isLoaded() throws Error {
        $(ALL_PHOTOS_PANEL).should(Condition.exist.because("The all photos block wasn't loaded"));
        $(PHOTO_VITRINE_PANEL).should(Condition.exist.because("The photo vitrine wasn't loaded"));
        $(ALBUMS_PANEL).should(Condition.exist.because("The albums block wasn't loaded"));
    }

    public String checkNameHeaderVisibility() {
        return $(ALL_PHOTOS_HEADER_LOCATION)
                .shouldBe(Condition.visible, Duration.ofSeconds(5)).getText();
    }

}
