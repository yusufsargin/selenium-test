package com.ceng.webonline.coursepage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

public class CoursePageImpl extends LoadableComponent<CoursePageImpl> implements ICoursePage {
    WebDriver driver;

    int courseId;

    public CoursePageImpl(WebDriver driver, int courseId) {
        this.driver = driver;
        this.courseId = courseId;
    }

    @Override
    protected void load() {
        String baseUrl = driver.getCurrentUrl();

        driver.get(baseUrl + "course/view.php?id=" + this.courseId);
    }

    @Override
    protected void isLoaded() throws Error {
        Assert.assertTrue(driver.findElement(By.className("course-content")).isDisplayed(), "Shold display weeks");
    }
}
