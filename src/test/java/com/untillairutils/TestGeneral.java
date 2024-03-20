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

   // @AfterEach
   // void tearDown() {
    //    logout();
    //}


    @Test
    void testMessages(){
        Helpers.clickByXpath(driver, "//span[text()='General']");
        Helpers.clickByXpath(driver, "//span[text()='Article messages']");
        Helpers.waitByXpath(driver, "//span[text()='Add new message']");
        Helpers.clickByXpath(driver, "//span[text()='Add new message']");
        Helpers.inputById(driver, "name", "Roasting");
        Helpers.inputById(driver, "text", "Medium");
        confirmContinue();
        Helpers.inputByXpath(driver,"//input[contains(@placeholder,'What are you looking ')]","Roasting");
        deleteFirstItem();
        Helpers.waitInvisibleByXpath(driver,"//span[text()='Roasting']" );
        Helpers.waitInvisibleByXpath(driver, "//div[contains(text(),'is deleted')]");

    }

    @Test
    void testDiscount() {
        Helpers.clickByXpath(driver, "//span[text()='General']");
        Helpers.clickByXpath(driver, "//span[text()='Discounts']");
        Helpers.waitByXpath(driver, "//span[text()='Add new discount']");
        Helpers.clickByXpath(driver, "//span[text()='Add new discount']");
        Helpers.inputById(driver, "name", "Weekend");
        Helpers.selectDropDownItemById(driver, "value_type", "Percent");
        String inputPrice = "//input[contains(@class, 'ant-input-number-input')]";
        Helpers.inputByXpath(driver, inputPrice, formatDbl(10));
        Helpers.selectDropDownItemById(driver, "discount_type", "Selected items");
        Helpers.clickByXpath(driver, "//span[text()='Save']");
        Helpers.waitVisibleByXpath(driver, "//span[text()='Weekend']");
        Helpers.inputByXpath(driver,"//input[contains(@placeholder,'What are you looking ')]","Weekend");
        deleteFirstItem();
        Helpers.waitInvisibleByXpath(driver,"//span[text()='Weekend']" );
        Helpers.waitInvisibleByXpath(driver, "//div[contains(text(),'is deleted')]");
    }
    @Test
    void testPeriods(){
        Helpers.clickByXpath(driver, "//span[text()='General']");
        Helpers.clickByXpath(driver, "//span[text()='Periods']");
        Helpers.waitByXpath(driver, "//span[text()='Add new period']");
        Helpers.clickByXpath(driver, "//span[text()='Add new period']");
        Helpers.inputById(driver, "name", "Morning");
        Helpers.clickByXpath(driver, "//span[text()='Save']");
        Helpers.waitVisibleByXpath(driver, "//span[text()='Morning']");
        Helpers.inputByXpath(driver,"//input[contains(@placeholder,'What are you looking ')]","Morning");
        deleteFirstItem();
        Helpers.waitInvisibleByXpath(driver,"//span[text()='Morning']" );
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
        Helpers.clickByXpathWithAttempts(driver, "//span[text()='Ok']", 10);
        deleteFirstItem();
        Helpers.waitInvisibleByXpath(driver,"//span[text()='Kitchen']");
        Helpers.waitInvisibleByXpath(driver, "//div[contains(text(),'is deleted')]");

    }

    @Test
    void testNewTerminal() {
        Helpers.clickByXpath(driver, "//span[text()='General']");
        Helpers.clickByXpath(driver, "//span[text()='Equipment']");
        Helpers.waitByXpath(driver, "//span[text()='Add new equipment']");
        Helpers.clickByXpath(driver, "//span[text()='Add new equipment']");
        Helpers.waitByXpath(driver, "//span[text()='Terminal']");
        Helpers.clickByXpath(driver, "//span[text()='Terminal']");
        String continueXP = "//span[text()='Continue']";
        Helpers.clickByXpath(driver, continueXP);
        Helpers.waitVisibleByXpath(driver,"//div[text()='Add terminal']" );
        Helpers.inputById(driver, "name", "Android");
        Helpers.inputById(driver, "poiid", "111");
        Helpers.clickByXpath(driver, "//span[text()='Continue']");
        Helpers.clickByXpath(driver, "//span[text()='Ok']");
        Helpers.waitVisibleByXpath(driver, "//span[text()='Android']");
        deleteFirstItem();
        Helpers.waitInvisibleByXpath(driver,"//span[text()='Android']");
        Helpers.waitInvisibleByXpath(driver, "//div[contains(text(),'is deleted')]");

    }
}
