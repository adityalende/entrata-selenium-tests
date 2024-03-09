package com.entrata.selenium.driverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverManager {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            // Set up WebDriver
            System.setProperty("webdriver.chrome.driver", "path_to_chromedriver");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}

