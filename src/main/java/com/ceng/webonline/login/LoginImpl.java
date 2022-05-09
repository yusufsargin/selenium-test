package com.ceng.webonline.login;

import com.ceng.shared.ICommon;
import com.ceng.shared.Property;
import com.ceng.webonline.homepage.HomePageImpl;
import com.ceng.webonline.redirectLoginPage.RedirectLoginPageImpl;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginImpl extends ICommon<LoginImpl> implements ILogin {
    // Elements

    private WebElement username;
    private WebElement password;
    private WebDriver submit;

    Property property = new Property();
    private WebDriver driver;

    private final String PAGE_URL = property.getProperty("webonline.pageurl");

    public LoginImpl(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Override
    protected void load() {
        driver.get(PAGE_URL);
    }

    @Override
    protected void isLoaded() throws Error {
        String currentUrl = driver.getCurrentUrl();

        Assert.assertEquals(currentUrl, PAGE_URL, "Should url matched");
    }

    @Override
    public void submit() {
        driver.findElement(By.id("submit")).click();
    }

    @Override
    public HomePageImpl login(String username, String password) {
        try {
            this.clearAndType(this.username, username);
            this.clearAndType(this.password, password);

            new WebDriverWait(driver, Duration.ofSeconds(20));
            submit();

            return new HomePageImpl(this.driver);
        } catch (NoSuchElementException exception) {
            return null;
        }
    }

    @Override
    public RedirectLoginPageImpl loginWithWrong(String username, String password) {
        try {
            this.clearAndType(this.username, username);
            this.clearAndType(this.password, password);

            new WebDriverWait(driver, Duration.ofSeconds(20));
            submit();

            return new RedirectLoginPageImpl(driver);
        } catch (NoSuchElementException exception) {
            return null;
        }
    }

    @Override
    public Boolean isLoggedIn() {
        try {
            driver.findElement(By.className("welcome_userpicture"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public HomePageImpl logout() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(200));

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[role=\"menuitem\"]")));

            driver.findElement(By.cssSelector("[role=\"menuitem\"]")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-title='logout,moodle']")));

            driver.findElement(By.cssSelector("[data-title='logout,moodle']")).click();

            return new HomePageImpl(this.driver);
        } catch (NoSuchElementException | ElementNotInteractableException e) {
            return null;
        }
    }
}
