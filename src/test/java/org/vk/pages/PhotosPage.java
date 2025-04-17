package org.vk.pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.vk.utilityClasses.AlbumsTab;
import org.vk.utilityClasses.AllPhotosTab;
import org.vk.utilityClasses.SharedAlbumCreationPromise;
import org.vk.utilityClasses.SharedAlbumsTab;

import static com.codeborne.selenide.Selenide.$;

public class PhotosPage extends AllPhotosTab {
    public final By ALL_PHOTOS_TAB = By.xpath(".//*[@data-l='t,albums']");
    public final By ALBUMS_TAB = By.xpath(".//*[@data-l='t,albums_new']");
    public final By SHARED_ALBUMS_TAB = By.xpath(".//*[@data-l='t,shared-albums']");
    private String userID;

    public PhotosPage(String userID) {
        super(userID);
    }

    public SharedAlbumCreationPromise createSharedAlbumPromise(SharedAlbumsTab sharedAlbumsTab) {
        return sharedAlbumsTab.createSharedAlbum(userID);
    }

    public AllPhotosTab openAllPhotosTab() {
        $(ALL_PHOTOS_TAB).shouldBe(Condition.clickable.because("All photos tab isn't clickable")).click();
        return this;
    }

    public AlbumsTab openAlbumsTab() {
        $(ALBUMS_TAB).shouldBe(Condition.clickable.because("Albums tab isn't clickable")).click();
        return new AlbumsTab(userID).get();
    }

    public SharedAlbumsTab openSharedAlbumsTab() {
        $(SHARED_ALBUMS_TAB).click();
        return new SharedAlbumsTab(userID).get();
    }
}
