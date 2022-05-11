package com.ceng.shared;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.LoadableComponent;

public abstract class ICommon<T extends LoadableComponent<T>> extends LoadableComponent<T> {
    public Property property;

    public ICommon() {
        this.property = new Property();
    }

    public void clearAndType(@NotNull WebElement element, String type) {
        element.clear();
        element.sendKeys(type);
    }

}
