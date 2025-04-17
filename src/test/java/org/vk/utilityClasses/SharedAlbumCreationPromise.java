package org.vk.utilityClasses;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.vk.pages.LoadableComponent;

import static com.codeborne.selenide.Selenide.$;

public class SharedAlbumCreationPromise extends LoadableComponent<SharedAlbumCreationPromise> {

    public final By CREATION_BLOCK = By.xpath(".//*[@class='photo-vitrine_stub_content']");
    private String userID;


    public SharedAlbumCreationPromise(String userID) {
        this.userID = userID;
    }

    @Override
    protected void load() {
        Selenide.open("/profile/" + userID + "/shared");
    }

    @Override
    protected void isLoaded() throws Error {
        $(CREATION_BLOCK).should(Condition.exist.because("The album creation block wasn't loaded"));
    }
}
