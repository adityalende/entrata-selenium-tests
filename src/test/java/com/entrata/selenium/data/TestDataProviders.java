package com.entrata.selenium.data;

import org.testng.annotations.DataProvider;

import java.util.HashMap;
import java.util.Map;

public class TestDataProviders {

    @DataProvider(name = "homePageTestData")
    public Object[][] getHomePageTestData() {
        return new Object[][] {
                { "Property Management Software | Entrata" }
        };
    }

    @DataProvider(name = "demoFormData")
    public Object[][] getDemoFormData() {
        Map<String, String> formData1 = new HashMap<>();
        formData1.put("FirstName", "Fname1");
        formData1.put("LastName", "Lname1");
        formData1.put("Email", "xyz1@example.com");
        formData1.put("Company Name", "ABC Company1");
        formData1.put("Phone Number", "12345678901");
        formData1.put("UnitCount", "1 - 10");
        formData1.put("Job Title", "Software Engineer1");
        formData1.put("I am:", "a Resident");

        Map<String, String> formData2 = new HashMap<>();
        formData2.put("FirstName", "Fname2");
        formData2.put("LastName", "Lname2");
        formData2.put("Email", "xyz2@example.com");
        formData2.put("Company Name", "ABC Company2");
        formData2.put("Phone Number", "12345678902");
        formData2.put("UnitCount", "11 - 100");
        formData2.put("Job Title", "Software Engineer2");
        formData2.put("I am:", "a Resident");

        return new Object[][]{{formData1}, {formData2}};
    }
}
