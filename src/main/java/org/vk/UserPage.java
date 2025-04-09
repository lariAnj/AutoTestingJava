package org.vk;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class UserPage implements MicroservicesNavigationToolbar{
    private final By FEED = By.xpath("//div/div[@class='feed js-video-scope __header-redesign h-mod']");
    private final By USER_NAME = By.xpath("//div/a/div[@class='tico ellip']");

    public UserPage checkIsItFeed() {
        $(FEED).shouldBe(Condition.visible);
        return this;
    }

    public boolean checkUserNameVisability() {
        $(USER_NAME).shouldBe(visible);
        return true;
    }

    public boolean checkUserNameClickability() {
        $(USER_NAME).shouldBe(clickable);
        return true;
    }

    public String getUserName() {
        String name = $(USER_NAME).text();
        return name;
    }

    public UserPage checkToolbarRowVisability() {
        ($(TOOLBAR_ROW)).shouldBe(visible);
        return this;
    }

    public UserPage checkToolbarRowSize() {
        ($(TOOLBAR_ROW)).shouldHave(cssValue("width",toolbarRowWidth));
        ($(TOOLBAR_ROW)).shouldHave(cssValue("height",toolbarRowHeight));
        return this;
    }

/*    public ElementsCollection getAllIconsOnToolbar() {
        ElementsCollection servicesIconsCollection = $("#topPanel").$$(".toolbar_nav_i");


        //$$("#list li").filterBy(cssClass("enabled")).findBy(exactText("foo")).find(".remove").click();
// вместо
        //$(By.xpath("//*[@id='list']//li[@class='enabled' and .//text()='foo']//*[@class='remove']")).click()

        servicesIconsCollection.forEach(element -> {
            System.out.println(
                    "Data-l: " + element.getAttribute("data-l")
            );
        });
        return servicesIconsCollection;
    }*/

    public Stream<SelenideElement> getStreamFromToolbarIcons() {
        Stream<SelenideElement> allIconsStream = Stream.of(
                $(MESSAGE_ICON),
                $(DISCUSSIONS_ICON),
                $(NOTIFICATIONS_ICON),
                $(GUESTS_ICON),
                $(MARKS_ICON),
                $(VIDEO_ICON),
                $(MUSIC_ICON),
                $(TODAY_ICON)
        );
        return allIconsStream;
    }

/*    public List<SelenideElement> getToolbarIconsInList() {
        ElementsCollection allIcons = getAllIconsOnToolbar();
        List<SelenideElement> toolbarIconsInList = allIcons.stream().collect(Collectors.toList());
        return toolbarIconsInList;
    }*/

    public UserPage checkToolbarIconVisability(SelenideElement icon) {
        icon.shouldBe(visible);
        return this;
    }

    public UserPage checkToolbarIconClass(SelenideElement icon) {
        /*icon.should(Condition.match("Icon @class contains 'toolbar_nav_i'.",
                el -> el.getAttribute("class").contains("toolbar_nav_i")
        ));*/
        icon.shouldHave(attributeMatching("class", ".*toolbar_nav_i.*"));
        return this;
    }

}
