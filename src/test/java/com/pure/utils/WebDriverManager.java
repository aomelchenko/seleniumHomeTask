package com.pure.utils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverManager {
    public static WebDriver driver;

    private WebDriverManager() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver =  new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1400, 1000));
    }

    public static WebDriver getWebDriver() {
        if (driver == null) {
            new WebDriverManager();
        }
        return driver;
    }

    public static void closeBrowser() {
        getWebDriver().close();
    }
}
