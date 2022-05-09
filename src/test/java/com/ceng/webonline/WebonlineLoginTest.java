package com.ceng.webonline;

import com.ceng.shared.PreTest;
import com.ceng.shared.Property;
import com.ceng.webonline.homepage.HomePageImpl;
import com.ceng.webonline.login.LoginImpl;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class WebonlineLoginTest extends PreTest {
    protected HomePageImpl homePage;
    protected LoginImpl login;

    @DataProvider(name = "user-info-provider")
    public Object[][] dataProviderMethod() {
        return new Object[][] { { "c123","123" }, { "c456","234123" }, {"c5924123","1421312321312"}, {"131231","sadasdıoajdjak"} };
    }

    @Test(description = "Login Webonline",priority = 0)
    public void webOnlineLoginTest(){
        this.login = new LoginImpl(this.driver).get();
        homePage = login.login(property.getProperty("webonline.username"),property.getProperty("webonline.password"));
    }



    @Test(description = "After login logout test",priority = 1)
    public void webOnlineLogoutTest(){
        Assert.assertTrue(this.login.isLoggedIn(), "Should be logged in");

        try{
            this.homePage = login.logout();

            new WebDriverWait(this.driver, Duration.ofSeconds(200));

            Assert.assertFalse(this.login.isLoggedIn(), "Should logged out");
        }catch (NoSuchElementException noSuchElementException){
            Assert.fail(noSuchElementException.getMessage());
        }
    }

    @Test(description = "Login with wrong info", priority = 2, dataProvider = "user-info-provider")
    public void webOnlineLoginWithWrongInfo(String username,String password){
        this.login = new LoginImpl(this.driver).get();
        this.login.loginWithWrong(username,password);
    }

    @AfterClass
    public void destructor(){
        this.driver.close();
    }
}
