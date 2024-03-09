package com.entrata.selenium.tests;

import com.entrata.selenium.data.TestDataProviders;
import com.entrata.selenium.utils.CommonUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.Assert.assertEquals;

;

public class EntrataTests extends EntrataBaseClass {


    @Test(dataProvider = "homePageTestData", dataProviderClass = TestDataProviders.class)
    public void testHomePageTitle(String expectedTitle) {
        // Verify homepage title
        if(driver.findElement(By.id("rcc-confirm-button")).isDisplayed())
            driver.findElement(By.id("rcc-confirm-button")).click();
        assertEquals(expectedTitle, driver.getTitle());
    }

    @Test(dataProvider = "demoFormData", dataProviderClass = TestDataProviders.class)
    public void testWatchDemoFormValidationForPage(Map<String, String> formData) {
        // Click on Watch demo link
        WebElement watchDemoLink = driver.findElement(By.linkText("Watch Demo"));
        watchDemoLink.click();

        // Verify navigation to the Products page
        CommonUtils.fillForm(driver,formData);
        // Navigate back to the home page
        driver.navigate().back();

    }

    @Test
    public void testInvalidLogin() {
        // Click on Sign In button
        WebElement signInButton = driver.findElement(By.linkText("Sign In"));
        signInButton.click();

        // Scroll down to Property Manager Login button
        WebElement propertyManagerLoginButton = driver.findElement(By.linkText("Property Manager Login"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", propertyManagerLoginButton);

        // Click on Property Manager Login button
        propertyManagerLoginButton.click();

        // Enter invalid username and password
        WebElement usernameField = driver.findElement(By.id("entrata-username"));
        usernameField.sendKeys("xyzabc");

        WebElement passwordField = driver.findElement(By.id("entrata-password"));
        passwordField.sendKeys("xyzabc");

        // Click on Sign In button
        WebElement signInButton2 = driver.findElement(By.xpath("//button[text()='Sign In']"));
        signInButton2.click();

        // Validate error message
        WebElement errorMessage = driver.findElement(By.id("statusMessage"));
        String actualMessage = errorMessage.getText();
        String expectedMessage = "The username and password provided are not valid. Please try again.";
        assert actualMessage.equals(expectedMessage) : "Error message validation failed";
    }

    @Test
    public void testHoverMenuNavigation() {
        if(driver.findElement(By.id("rcc-confirm-button")).isDisplayed())
            driver.findElement(By.id("rcc-confirm-button")).click();

        // Locate the Products, Solutions, and Resources elements
        WebElement productsMenu = driver.findElement(By.xpath("//div[text()='Products']"));
        WebElement solutionsMenu = driver.findElement(By.xpath("(//div[text()='Solutions'])[1]"));
        WebElement resourcesMenu = driver.findElement(By.xpath("//div[text()='Resources']"));

        // Create Actions object
        Actions actions = new Actions(driver);

        // Hover over Products menu
        actions.moveToElement(productsMenu).perform();

        // Verify that the navigation group for Products is open
        WebElement productsNavigationGroup = driver.findElement(By.xpath("(//div[@class='main-nav-link'])[1]"));
        Assert.assertTrue(productsNavigationGroup.isDisplayed(), "Products navigation group is not displayed");

        // Hover over Solutions menu
        actions.moveToElement(solutionsMenu).perform();

        // Verify that the navigation group for Solutions is open
        WebElement solutionsNavigationGroup = driver.findElement(By.xpath("(//div[@class='header-drop-nav'])[1]"));
        Assert.assertTrue(solutionsNavigationGroup.isDisplayed(), "Solutions navigation group is not displayed");

        // Hover over Resources menu
        actions.moveToElement(resourcesMenu).perform();

        // Verify that the navigation group for Resources is open
        WebElement resourcesNavigationGroup = driver.findElement(By.xpath("(//div[@class='header-drop-nav'])[2]"));
        Assert.assertTrue(resourcesNavigationGroup.isDisplayed(), "Resources navigation group is not displayed");
    }
}
