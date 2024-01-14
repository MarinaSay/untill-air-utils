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

import static com.untillairutils.Helpers.formatDbl;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

public class TestGeneral extends TestBase {
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
    void testDiscount() {
        Helpers.clickByXpath(driver, "//span[text()='General']");
        Helpers.clickByXpath(driver, "//span[text()='Discounts']");
        Helpers.waitByXpath(driver, "//span[text()='Add new discount']");
        Helpers.clickByXpath(driver, "//span[text()='Add new discount']");
        Helpers.inputById(driver, "name", "Monday");
        Helpers.selectDropDownItemById(driver, "value_type", "Percent");
        String inputPrice = "//input[contains(@class, 'ant-input-number-input')]";
        Helpers.inputByXpath(driver, inputPrice, formatDbl(10));
        Helpers.selectDropDownItemById(driver, "discount_type", "Whole bill");
        Helpers.scrollAndClickByXpath(driver, "//span[text()='All items']");
        Helpers.selectDropDownItemById(driver, "id_discount_reasons", "Free discount reason");
        Helpers.clickByXpath(driver, "//span[text()='Save']");
        Helpers.waitVisibleByXpath(driver, "//span[text()='Monday']");
        assertFalse(driver.findElements(By.xpath("//span[text()='Monday']")).isEmpty());
        deleteFirstItem();
        assertTrue(driver.findElements(By.xpath("//span[text()='Monday']")).isEmpty());
        Helpers.waitInvisibleByXpath(driver, "//div[contains(text(),'is deleted')]");
    }

    @Test
    void testNewReceiptPrinter() {
        Helpers.clickByXpath(driver, "//span[text()='General']");
        Helpers.clickByXpath(driver, "//span[text()='Equipment']");
        Helpers.waitByXpath(driver, "//span[text()='Add new equipment']");
        Helpers.clickByXpath(driver, "//span[text()='Add new equipment']");
        Helpers.waitByXpath(driver, "//span[text()='Printer']");
        Helpers.clickByXpath(driver, "//span[text()='Printer']");
        confirmContinue();
        Helpers.inputById(driver, "name", "Kitchen");
        Helpers.clickByXpath(driver, "//span[text()='Receipt']");
        Helpers.clickByXpath(driver, "//span[text()='Save']");
        Helpers.waitVisibleByXpath(driver, "//span[text()='Kitchen']");
        assertFalse(driver.findElements(By.xpath("//span[text()='Kitchen']")).isEmpty());
        Helpers.clickByXpathWithAttempts(driver, "//span[text()='Ok']", 10);
        deleteFirstItem();
        assertTrue(driver.findElements(By.xpath("//span[text()='Kitchen']")).isEmpty());
        Helpers.waitInvisibleByXpath(driver, "//div[contains(text(),'is deleted')]");

    }

}
