package com.ceng.webonline.homepage;

import com.ceng.shared.Property;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

public class HomePageImpl extends LoadableComponent<HomePageImpl> implements IHomePage {
    WebDriver driver;

    Property property = new Property();
    private final String PAGE_URL = property.getProperty("webonline.pageurl");

    public HomePageImpl(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    protected void load() {
        driver.get(PAGE_URL);
    }

    @Override
    protected void isLoaded() throws Error {
        System.out.println(driver.getCurrentUrl());
        Assert.assertEquals(driver.getCurrentUrl(), PAGE_URL, "Should match");
    }

    public Boolean checkIsLoggedIn() {
        try {
            WebElement ppElement = driver.findElement(By.className("welcome_userpicture"));

            return ppElement.isDisplayed();
        } catch (NoSuchElementException noSuchElementException) {
            return false;
        }
    }
}
