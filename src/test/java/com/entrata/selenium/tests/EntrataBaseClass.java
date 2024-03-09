package com.entrata.selenium.tests;

import com.entrata.selenium.driverManager.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;


public class EntrataBaseClass extends WebDriverManager {

    public static WebDriver driver;

    @BeforeClass
    public static void setUpWebDriver() {
        // Set up WebDriver
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.entrata.com/");
    }

    @AfterClass
    public static void tearDownWebDriver() {
        // Close the browser after all tests
        if (driver != null) {
            driver.quit();
        }
    }

}
