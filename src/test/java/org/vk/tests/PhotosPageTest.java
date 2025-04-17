package org.vk.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import org.vk.TestBot;
import org.vk.pages.LoginPage;
import org.vk.pages.PhotosPage;
import org.vk.utilityClasses.AlbumsTab;
import org.vk.utilityClasses.AllPhotosTab;
import org.vk.utilityClasses.SharedAlbumsTab;

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
        LoginPage loginPage = new LoginPage().get();
        loginPage.enterUserData(testBot)
                .clickEnterButton();
    }

    @BeforeEach
    public void getUserFeedPage() {
        Selenide.open("/profile/" + testBot.getID() + "/photos");
    }

    @Test
    @DisplayName("Test to check shared albums creation")
    @Tag("functionality")
    public void testSharedAlbumCreation() {
        PhotosPage photosPage = (PhotosPage) new PhotosPage(testBot.getID()).get();
        photosPage.createSharedAlbumPromise(photosPage.openSharedAlbumsTab());

    }

    @Test
    @DisplayName("Test to check AlbumsTab creation")
    @Tag("functionality")
    public void testAlbumsTab() {
        PhotosPage photosPage = (PhotosPage) new PhotosPage(testBot.getID()).get();
        AlbumsTab albumsTab = photosPage.openAlbumsTab();
        assertEquals(AlbumsTab.albumsSectionHeader, albumsTab.checkNameHeaderVisibility(),"Header isn't right in AlbumsTab");
    }

    @Test
    @DisplayName("Test to check SharedAlbumsTab creation")
    @Tag("functionality")
    public void testSharedAlbumsTab() {
        PhotosPage photosPage = (PhotosPage) new PhotosPage(testBot.getID()).get();
        SharedAlbumsTab sharedAlbumsTab = photosPage.openSharedAlbumsTab();
        assertEquals(SharedAlbumsTab.sharedAlbumsSectionHeader, sharedAlbumsTab.checkNameHeaderVisibility(),"Header isn't right in SharedAlbumsTab");
    }

    @Test
    @DisplayName("Test to check AllPhotosTab creation")
    @Tag("functionality")
    public void testAllPhotosTab() {
        PhotosPage photosPage = (PhotosPage) new PhotosPage(testBot.getID()).get();
        AllPhotosTab allPhotosTab = photosPage.openAllPhotosTab();
        assertEquals(AllPhotosTab.allPhotosSectionHeader, allPhotosTab.checkNameHeaderVisibility(),"Header isn't right in AllPhotosTab");
    }

    @AfterAll
    public static void exit() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }
}
