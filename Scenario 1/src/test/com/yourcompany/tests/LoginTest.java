package com.yourcompany.tests;

import com.yourcompany.pages.BasePage;
import com.yourcompany.utils.ExcelReader;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@CucumberOptions(features = "src/test/resources", glue = {"com.yourcompany.tests"})
public class LoginTest extends BasePage {
    private final String baseUrl = "https://www.saucedemo.com/";

    public LoginTest(WebDriver driver) {
        super(driver);
    }

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password, String expectedTitle) {
        driver.get(baseUrl);

        // Your implementation of login steps here

        // Sample assertion, update it based on your actual scenario
        assertEquals(driver.getTitle(), expectedTitle);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @DataProvider(name = "loginData")
    public Object[][] getData() {
        return ExcelReader.readTestData("login");
    }
}
