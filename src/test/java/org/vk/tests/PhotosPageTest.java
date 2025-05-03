package org.vk.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import org.vk.utilityClasses.TestBot;
import org.vk.pages.LoginPage;
import org.vk.pages.photosPages.ExistingSharedAlbumsPage;
import org.vk.pages.photosPages.PhotosPage;
import org.vk.pages.photosPages.SharedAlbumsCreationPage;
import org.vk.wrappers.AlbumsTab;
import org.vk.wrappers.AllPhotosTab;
import org.vk.wrappers.SharedAlbumsTab;

import static org.junit.jupiter.api.Assertions.*;

@Tag("PhotosPage tests")
public class PhotosPageTest {
    private static TestBot testBot = new TestBot("technopol48", "technopolisPassword", "587712302410");


    @BeforeAll
    public static void setup() {
        Configuration.baseUrl = "https://ok.ru";
        Configuration.timeout = 5000;
        Configuration.browser = "chrome";
        Selenide.open("/");
        LoginPage loginPage = new LoginPage();
        loginPage.enterUserData(testBot)
                .clickEnterButton();
    }

    @BeforeEach
    public void getUserFeedPage() {
        Selenide.open("/profile/" + testBot.getID() + "/photos");
    }

    @Test
    @DisplayName("Test to check SharedAlbumsTab")
    @Tag("functionality")
    public void testSharedAlbumsTab() {
        PhotosPage photosPage = (PhotosPage) new PhotosPage();
        photosPage.createSharedAlbums();
        Selenide.open("/profile/" + testBot.getID() + "/photos");
        ExistingSharedAlbumsPage promisePage = photosPage.openSharedAlbumsTab().goToExistingSharedAlbumsPage();
        assertAll(
                () -> assertTrue(promisePage.checkPhotoAlbumCardExisting(), "No one shared album exists"),
                () -> assertEquals(SharedAlbumsTab.sharedAlbumsSectionHeader, promisePage.checkNameHeaderVisibility(),
                        "SharedAlbums page header isn't visible")
        );
    }

    @Test
    @DisplayName("Test to check shared albums creation")
    @Tag("functionality")
    public void testSharedAlbumCreation() {
        PhotosPage photosPage = (PhotosPage) new PhotosPage();
        photosPage.cleanSharedAlbumsTab();
        SharedAlbumsCreationPage promisePage = photosPage.openSharedAlbumsTab().goToSharedAlbumCreationPage();
        assertAll(
                () -> assertTrue(promisePage.checkCreateSharedAlbumButton(), "Create shared album button doesn't work properly"),
                () -> assertEquals(SharedAlbumsTab.sharedAlbumsSectionHeader, promisePage.checkNameHeaderVisibility(),
                        "SharedAlbums page header isn't visible"),
                () -> assertEquals(SharedAlbumsCreationPage.noSharedAlbumsHeader, promisePage.checkNoAlbumHeaderVisibility(),
                        "\"Общих альбомов пока нет\" header isn't visible")
        );
    }

    @Test
    @DisplayName("Test to check AlbumsTab")
    @Tag("functionality")
    public void testAlbumsTab() {
        PhotosPage photosPage = (PhotosPage) new PhotosPage();
        AlbumsTab albumsTab = photosPage.openAlbumsTab();
        assertEquals(AlbumsTab.albumsSectionHeader, albumsTab.checkNameHeaderVisibility(),"Header isn't right in AlbumsTab");
    }

    @Test
    @DisplayName("Test to check AllPhotosTab")
    @Tag("functionality")
    public void testAllPhotosTab() {
        PhotosPage photosPage = (PhotosPage) new PhotosPage();
        AllPhotosTab allPhotosTab = photosPage.openAllPhotosTab();
        assertEquals(AllPhotosTab.allPhotosSectionHeader, allPhotosTab.checkNameHeaderVisibility(),"Header isn't right in AllPhotosTab");
    }

    @AfterAll
    public static void exit() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }
}
