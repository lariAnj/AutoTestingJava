package org.vk.wrappers;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.vk.utilityClasses.LoadableComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class AllPhotosTab implements LoadableComponent {
    public final By ALL_PHOTOS_PANEL = By.xpath(".//*[@class='portlet photo-stream']");
    public final By PHOTO_VITRINE_PANEL = By.xpath(".//*[contains(@class, 'tech-albums')]");
    public final By ALBUMS_PANEL = By.xpath(".//*[@id='tabpanel-all-photos']");
    private final By ALL_PHOTOS_HEADER_LOCATION = By.xpath(".//*[contains(@class,'stream-name')]");

    public static String allPhotosSectionHeader = "Все фото";

    public boolean isLoaded() throws Error {
        Duration timeout = Duration.ofSeconds(10);
        if (!($(ALL_PHOTOS_PANEL).is(visible, timeout) &&
                $(PHOTO_VITRINE_PANEL).is(visible, timeout)) &&
                $(ALBUMS_PANEL).is(visible, timeout)) {
            throw new Error("AllPhotosTab page wasn't loaded properly");
        }
        else {
            return true;
        }
    }

    public AllPhotosTab() {
        isLoaded();
    }

    public String checkNameHeaderVisibility() {
        return $(ALL_PHOTOS_HEADER_LOCATION)
                .shouldBe(visible.because("All photos tab header isn't visible"), Duration.ofSeconds(5)).getText();
    }

}
