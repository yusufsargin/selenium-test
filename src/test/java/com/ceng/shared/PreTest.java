package com.ceng.shared;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class PreTest {
    public WebDriver driver;
    public Property property;

    @BeforeClass
    @Parameters("browser-type")
    public void setup(String browserType) {
        if (browserType.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            this.driver = new FirefoxDriver();
        } else {
            WebDriverManager.chromedriver().setup();
            this.driver = new ChromeDriver();
        }

        this.property = new Property();
    }
}
