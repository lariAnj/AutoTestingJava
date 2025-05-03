package org.vk.pages.photosPages;

import org.openqa.selenium.By;
import org.vk.utilityClasses.LoadableComponent;
import org.vk.wrappers.SharedAlbumsTab;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Class that contains the part that special for page with existing shared albums
 * */

public class ExistingSharedAlbumsPage extends SharedAlbumsTab implements LoadableComponent {
    private final By SHARED_ALBUMS_BLOCK = By.xpath(".//*[@data-l='t,photo-albums']");
    private final By ALBUM_CARD = By.xpath(".//*[contains(@class, 'photo-album-card')]");

    @Override
    public boolean isLoaded() throws Error {
        if (!($(super.CONCRETE_ALBUMS_RAW).is(visible, Duration.ofSeconds(10)))) {
            throw new Error("ExistingSharedAlbums page wasn't loaded properly");
        }
        else {
            return true;
        }
    }

    public ExistingSharedAlbumsPage() {
        super();
        isLoaded();
    }

    public boolean checkPhotoAlbumCardExisting() {
       return  $(SHARED_ALBUMS_BLOCK).$(ALBUM_CARD).is(visible);
    }

}
