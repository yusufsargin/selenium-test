package com.ceng.webonline;

import org.testng.annotations.Factory;

public class WebOnlineTestFactory {
    @Factory
    public Object[] factoryMethod() {
        return new Object[] { new WebonlineLoginTest() };
    }
}
