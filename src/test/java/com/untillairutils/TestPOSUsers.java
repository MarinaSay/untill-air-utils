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
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

public class TestPOSUsers extends TestBase{

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
    void testPosUser() {
        Helpers.clickByXpath(driver, "//span[text()='POS Users']");
        Helpers.waitByXpath(driver, "//span[text()='Add new user']");
        Helpers.clickByXpath(driver, "//span[text()='Add new user']");
        Helpers.inputById(driver, "name", "Mary");
        Helpers.inputById(driver, "firstname", "Maria");
        Helpers.inputById(driver, "lastname", "Lopez");
        Helpers.selectDropDownItemById(driver, "language", "English");
        Helpers.inputById(driver, "user_poscode", "111");
        Helpers.clickByXpath(driver, "//span[text()='Save']");
        Helpers.waitVisibleByXpath(driver, "//span[text()='Mary']");
        assertFalse(driver.findElements(By.xpath("//span[text()='Mary']")).isEmpty());
        deleteFirstItem();
        assertTrue(driver.findElements(By.xpath("//span[text()='Mary']")).isEmpty());
        Helpers.waitInvisibleByXpath(driver, "//div[contains(text(),'is deleted')]");
    }
}
