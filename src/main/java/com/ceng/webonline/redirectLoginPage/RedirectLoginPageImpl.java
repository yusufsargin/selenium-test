package com.ceng.webonline.redirectLoginPage;

import com.ceng.shared.Property;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

public class RedirectLoginPageImpl extends LoadableComponent<RedirectLoginPageImpl> implements IRedirectLoginPage {
    WebDriver driver;
    Property property = new Property();
    String PAGEURL = property.getProperty("webonline.pageurl") + "/login/index.php";

    public RedirectLoginPageImpl(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    protected void load() {
        this.driver.get(PAGEURL);
    }

    @Override
    protected void isLoaded() throws Error {
        String currentUrl = driver.getCurrentUrl();

        Assert.assertEquals(currentUrl,PAGEURL, "Should load successfully");
    }
}
