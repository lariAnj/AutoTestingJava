package org.vk.pages.photosPages;

import org.openqa.selenium.By;
import org.vk.utilityClasses.LoadableComponent;
import org.vk.wrappers.SharedAlbumsTab;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

/**
 * Class that contains the part that special for page without existing shared albums
 * */

public class SharedAlbumsCreationPage extends SharedAlbumsTab implements LoadableComponent {

    public final By NO_ALBUMS_HEADER = By.xpath(".//*[@class='photo-vitrine_stub_header']");
    public final By CREATION_BUTTON = By.xpath(".//*[@class='photo-vitrine_stub_controls']");

    public static String noSharedAlbumsHeader = "Общих альбомов пока нет";


    @Override
    public boolean isLoaded() throws Error {
        if (!($(super.SHARED_ALBUMS_BLOCK).is(visible, Duration.ofSeconds(10)) && $(super.CREATION_BLOCK).is(visible, Duration.ofSeconds(10)))) {
            throw new Error("SharedAlbumsCreation page wasn't loaded properly");
        }
        else {
            return true;
        }
    }

    public SharedAlbumsCreationPage() {
        super();
        isLoaded();
    }

    public boolean checkCreateSharedAlbumButton () {
        return $(CREATION_BUTTON).is(clickable) && $(CREATION_BUTTON).is(visible);
    }

    public String checkNoAlbumHeaderVisibility() {
        return $(SHARED_ALBUMS_BLOCK).$(NO_ALBUMS_HEADER)
                .shouldBe(visible.because("SharedAlbumsPage header isn't visible"), Duration.ofSeconds(5)).getText();
    }

}
