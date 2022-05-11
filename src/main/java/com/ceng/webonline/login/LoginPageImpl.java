package com.ceng.webonline.login;

import com.ceng.shared.ICommon;
import com.ceng.shared.Login;
import com.ceng.webonline.homepage.HomePageImpl;
import com.ceng.webonline.redirectLoginPage.RedirectLoginPageImpl;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginPageImpl extends ICommon<LoginPageImpl> implements Login<HomePageImpl> {
    // Elements
    private WebElement username;
    private WebElement password;
    private WebElement submit;
    private String logOutButtonSelector = "[data-title='logout,moodle']";

    // Driver
    private final WebDriver driver;

    // Constants
    private final String PAGE_URL = property.getProperty("webonline.pageurl");

    public LoginPageImpl(WebDriver driver) {
        super();
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HomePageImpl loginWithCorrectInfo(String username, String password) {
        try {
            this.login(username, password);

            return new HomePageImpl(this.driver);
        } catch (NoSuchElementException noSuchElementException) {
            return null;
        }
    }

    public RedirectLoginPageImpl loginWithWrongInfo(String username, String password) {
        try {
            this.login(username, password);

            return new RedirectLoginPageImpl(driver);
        } catch (NoSuchElementException e) {
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

            driver.findElement(By.cssSelector(this.logOutButtonSelector)).click();

            return new HomePageImpl(this.driver);
        } catch (NoSuchElementException | ElementNotInteractableException e) {
            return null;
        }
    }

    @Override
    public void login(String username, String password) {
        try {
            this.clearAndType(this.username, username);
            this.clearAndType(this.password, password);

            submit.click();
        } catch (NoSuchElementException noSuchElementException) {
            System.out.println(noSuchElementException.getMessage());
        }
    }

    @Override
    protected void load() {
        driver.get(this.PAGE_URL);
    }

    @Override
    protected void isLoaded() throws Error {
        String currentUrl = this.driver.getCurrentUrl();

        Assert.assertEquals(currentUrl,this.PAGE_URL, "Page Url and Current url should be equal");
    }
}
