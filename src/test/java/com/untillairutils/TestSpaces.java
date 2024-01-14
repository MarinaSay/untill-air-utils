package com.untillairutils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.testng.AssertJUnit.assertFalse;

public class TestSpaces extends TestBase{


    @BeforeEach
    void setUp() {
       loginAtLocation();
       setEnglish();
       closeWelcomePopUp();
    }

     @AfterEach
     void tearDown() {
       logout();
      }

    @Test
    void testSpaces() {
        Helpers.clickByXpath(driver, "//span[text()='Spaces']");
        Helpers.waitByXpath(driver, "//span[text()='Add new space']");
        Helpers.clickByXpathWithAttempts(driver, "//span[text()='Add new space']", 10);
        Helpers.inputById(driver, "name", "Lounge");
        Helpers.inputByXpath(driver, "//input[contains(@class, 'ant-input-number-input')]", "5");
        Helpers.clickByXpath(driver, "//span[text()='Continue']");
        Helpers.waitByXpath(driver, "//div[text()='Lounge']");
        assertFalse(driver.findElements(By.xpath("//div[text()='Lounge']")).isEmpty());
        assertFalse(driver.findElements(By.xpath("//div[text()='5 tables']")).isEmpty());
        Helpers.waitVisibleByXpath(driver, "//i[@class='air-bo-2-trash-can']");
        Helpers.clickByXpathWithAttempts(driver, "//i[@class='air-bo-2-trash-can']", 5);
        Helpers.waitVisibleByXpath(driver, "//div[text()='Are you sure?']");
        Helpers.clickByXpath(driver, "//span[text()='Yes']");

    }

}
