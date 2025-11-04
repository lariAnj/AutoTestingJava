package org.vk.utilityClasses;

import org.openqa.selenium.By;
import org.vk.pages.photosPages.ExistingSharedAlbumsPage;
import org.vk.pages.photosPages.SharedAlbumsCreationPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class SharedAlbumCreationPromise {

    public final By CREATION_BLOCK = By.xpath(".//*[@class='photo-vitrine_stub_content']");
    public final By CONCRETE_ALBUMS_RAW = By.xpath(".//*[contains(@id, 'PhotoVitrineAlbumsBlock')]");

    public SharedAlbumsCreationPage goToSharedAlbumCreationPage() {
        if ($(CREATION_BLOCK).is(visible)) {
            return new SharedAlbumsCreationPage();
        }
        throw new IllegalStateException("Expected SharedAlbums Creation page but got page with existing shared albums");
    }

    public ExistingSharedAlbumsPage goToExistingSharedAlbumsPage() {
        if ($(CONCRETE_ALBUMS_RAW).is(visible)) {
            return new ExistingSharedAlbumsPage();
        }
        throw new IllegalStateException("Expected ExistingSharedAlbumsPage but got page for crating shared albums");
    }
}

