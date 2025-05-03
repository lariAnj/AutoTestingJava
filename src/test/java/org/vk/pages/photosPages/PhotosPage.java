package org.vk.pages.photosPages;

import org.openqa.selenium.By;
import org.vk.wrappers.AlbumsTab;
import org.vk.wrappers.AllPhotosTab;
import org.vk.utilityClasses.SharedAlbumCreationPromise;
import org.vk.wrappers.SharedAlbumsTab;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Selenide.$;

public class PhotosPage extends AllPhotosTab {
    public final By ALL_PHOTOS_TAB = By.xpath(".//*[@data-l='t,albums']");
    public final By ALBUMS_TAB = By.xpath(".//*[@data-l='t,albums_new']");
    public final By SHARED_ALBUMS_TAB = By.xpath(".//*[@data-l='t,shared-albums']");

    public AllPhotosTab openAllPhotosTab() {
        $(ALL_PHOTOS_TAB).shouldBe(clickable.because("All photos tab isn't clickable")).click();
        return this;
    }

    public AlbumsTab openAlbumsTab() {
        $(ALBUMS_TAB).shouldBe(clickable.because("Albums tab isn't clickable")).click();
        return new AlbumsTab();
    }

    public SharedAlbumCreationPromise openSharedAlbumsTab() {
        $(SHARED_ALBUMS_TAB).shouldBe(clickable.because("SharedAlbumsTab button isn't clickable")).click();
        return new SharedAlbumCreationPromise();
    }

    public void cleanSharedAlbumsTab() {
        getSharedAlbumsPage();
        SharedAlbumsTab sharedAlbumsTab = new SharedAlbumsTab();
        sharedAlbumsTab.cleanExistingAlbums();
    }

    public void getSharedAlbumsPage() {
        $(SHARED_ALBUMS_TAB).shouldBe(clickable.because("SharedAlbumsTab button isn't clickable")).click();
    }

    public void createSharedAlbums() {
        getSharedAlbumsPage();
        SharedAlbumsTab sharedAlbumsTab = new SharedAlbumsTab();
        if (!sharedAlbumsTab.checkAnySharedAlbumExisting()) {
            sharedAlbumsTab.createSharedAlbum();
        }
    }
}
