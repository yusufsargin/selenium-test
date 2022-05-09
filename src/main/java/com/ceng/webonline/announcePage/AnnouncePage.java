package com.ceng.webonline.announcePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;

public class AnnouncePage extends LoadableComponent<AnnouncePage> {
    private final WebDriver driver;
    private String url="";
    private String announceButtonSelector="";

    public AnnouncePage(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    protected void load() {

    }

    @Override
    protected void isLoaded() throws Error {

    }
}
