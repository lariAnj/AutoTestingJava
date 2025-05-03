package org.vk.wrappers;

import org.openqa.selenium.By;
import org.vk.utilityClasses.LoadableComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * Class that contains common part of SharedAlbumsCreationPage and ExistingSharedAlbumsPage
 * */

public class SharedAlbumsTab implements LoadableComponent {

    private final By PORTLET_NAME_TITLE = By.cssSelector("h3.portlet_h_name_t");
    public final By SHARED_ALBUMS_BLOCK = By.xpath(".//*[@data-l='t,photo-albums']");
    private final By CONCRETE_ALBUM_BLOCK = By.xpath(".//*[contains(@id, 'SharedAlbumPhotosBlock')]");
    private final By CONCRETE_ALBUM_EDIT_BLOCK = By.xpath(".//*[contains(@id, 'UserAlbumEditBlock')]");
    private final By CONCRETE_ALBUM_CONFIRM_BLOCK = By.xpath(".//*[contains(@class, 'modal-new_center')]");

    public final By CONCRETE_ALBUMS_RAW = By.xpath(".//*[contains(@id, 'PhotoVitrineAlbumsBlock')]");
    private final By ALBUM_CARD = By.xpath(".//*[contains(@class, 'photo-album-card')]");
    private final By EDIT_ALBUM = By.xpath(".//*[@data-l='t,editAlbum']");
    private final By DELETE_ALBUM = By.xpath(".//*[@data-l='t,.r']");
    private final By DELETE_ALBUM_CONFIRMATION = By.xpath(".//*[@data-l='t,confirm']");
    public final By CREATION_BLOCK = By.xpath(".//*[@class='photo-vitrine_stub_content']");
    public final By CREATION_BUTTON = By.xpath(".//*[@class='photo-vitrine_stub_controls']");

    private final By ALBUM_NAME_ENTER_FIELD = By.xpath(".//*[@class='text-field_editor']");
    private final By NAMED_ALBUM_CREATION_BUTTON = By.xpath(".//*[@name='button_album_create']");
    private final By DOWNLOAD_PHOTOS_IN_ALBUM = By.xpath(".//*[@class='photo-album_stub-uploader']");
    private final By DOWNLOAD_FILE_ELEMENT = By.xpath(".//*[@type='file']");

    public static String sharedAlbumsSectionHeader = "Общие альбомы";
    private String albumName = "N-album";

    public boolean isLoaded() throws Error {
        if (!($(SHARED_ALBUMS_BLOCK).is(visible, Duration.ofSeconds(10)))) {
            throw new Error("SharedAlbumsTab page wasn't loaded properly");
        }
        else {
            return true;
        }
    }

    public SharedAlbumsTab() {
        isLoaded();
    }

    public void cleanExistingAlbums() {
        while ($(SHARED_ALBUMS_BLOCK).$(ALBUM_CARD).is(visible)) {
            $(SHARED_ALBUMS_BLOCK).$(CONCRETE_ALBUMS_RAW)
                    .$(ALBUM_CARD).shouldBe(clickable.because("Album card isn't clickable")).click();
                     $(CONCRETE_ALBUM_BLOCK).$(EDIT_ALBUM).shouldBe(clickable.because("Edit album button isn't clickable")).click();
            $(CONCRETE_ALBUM_EDIT_BLOCK).$(DELETE_ALBUM).shouldBe(clickable.because("Delete album isn't clickable")).click();
                     $(DELETE_ALBUM_CONFIRMATION).shouldBe(clickable.because("Confirm album deletion isn't clickable")).click();
        }
    }

    public void createSharedAlbum() {
        $(SHARED_ALBUMS_BLOCK).$(CREATION_BLOCK).$(CREATION_BUTTON).click();
        $(CONCRETE_ALBUM_CONFIRM_BLOCK).$(ALBUM_NAME_ENTER_FIELD).val(albumName);
        $(NAMED_ALBUM_CREATION_BUTTON).click();
        $(CONCRETE_ALBUM_BLOCK).$(DOWNLOAD_PHOTOS_IN_ALBUM).$(DOWNLOAD_FILE_ELEMENT).uploadFromClasspath("photo_for_album.png");
        sleep(3000);
    }

    public String checkNameHeaderVisibility() {
        return $(SHARED_ALBUMS_BLOCK).$(PORTLET_NAME_TITLE)
                .shouldBe(visible.because("SharedAlbumsTab header isn't visible"), Duration.ofSeconds(5)).getText();
    }

    public boolean checkAnySharedAlbumExisting() {
        return $(SHARED_ALBUMS_BLOCK).$(ALBUM_CARD).is(visible);
    }
}
