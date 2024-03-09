package com.entrata.selenium.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;

public class CommonUtils {
    static JSONObject jsonObject = readJsonFile("src/main/resources/testIdCommons.json");
    public static JSONObject readJsonFile(String filePath) {
        JSONObject jsonObject = null;
        try {
            JSONParser jsonParser = new JSONParser();
            jsonObject = (JSONObject) jsonParser.parse(new FileReader(filePath));
        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public static void fillForm(WebDriver driver, Map<String, String> formData) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Wait for the form elements to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(jsonObject.get("FirstName").toString())));

        // Fill the form with the provided data
        for (Map.Entry<String, String> entry : formData.entrySet()) {
            String fieldName = entry.getKey();
            String fieldValue = entry.getValue();
            WebElement field = driver.findElement(By.id(jsonObject.get(fieldName).toString()));
            if (field.getTagName().equalsIgnoreCase("select")) {
                Select select = new Select(field);
                select.selectByVisibleText(fieldValue);
            } else {
                field.sendKeys(fieldValue);
            }
        }
    }
}
