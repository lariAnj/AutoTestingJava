package org.vk.wrappers;

import org.openqa.selenium.By;
import org.vk.utilityClasses.LoadableComponent;

import java.time.Duration;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class AlbumsTab implements LoadableComponent {
    private final By PORTLET_NAME_TITLE = By.cssSelector("h3.portlet_h_name_t");
    public final By ALBUMS_BLOCK = By.xpath(".//*[@id='tabpanel-albums']");
    public static String albumsSectionHeader = "Альбомы";


    public boolean isLoaded() throws Error {
        if (!($(ALBUMS_BLOCK).is(visible, Duration.ofSeconds(10)))) {
            throw new Error("AlbumsTab page wasn't loaded properly");
        }
        else {
            return true;
        }
    }

    public AlbumsTab() {
        isLoaded();
    }

    public String checkNameHeaderVisibility() {
        return $(PORTLET_NAME_TITLE)
                .shouldBe(visible.because("Albums tab header isn't visible"), Duration.ofSeconds(5)).getText();
    }

}
