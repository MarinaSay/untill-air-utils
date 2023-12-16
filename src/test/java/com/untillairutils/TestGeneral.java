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

public class TestGeneral {

    WebDriver driver;


    static String ENV_URL = "UNTILLAIR_URL";

    static String ENV_LOGIN = "UNTILLAIR_LOGIN";

    static String ENV_PASSWORD = "UNTILLAIR_PASSWORD";

    static String ENV_LOCATION = "UNTILLAIR_LOCATION";

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    String getRequiredEnv(String name) {
        if (System.getenv(name) == null) {
            throw new RuntimeException("Env " + name + " not defined");
        }
        return System.getenv(name);
    }

    @BeforeEach
    void setUp() {

        this.driver = new ChromeDriver();
        driver.get(getRequiredEnv(ENV_URL));
        driver.manage().window().maximize();
        Auth.login(driver, getRequiredEnv(ENV_LOGIN), getRequiredEnv(ENV_PASSWORD));

        String location = System.getenv(ENV_LOCATION);
        if (location != null) {

            Helpers.waitVisibleByXpath(driver, "//header");
            String locationXPATH = "//header//div[contains(@class, 'ant-select')]";
            List<WebElement> chooseLocation = driver.findElements(By.xpath(locationXPATH));

            if (!chooseLocation.isEmpty()) {
                Helpers.selectDropDownItemByXpath(driver, locationXPATH, location);
            }
        }

    }

    @BeforeEach
    void setEnglish() {
        Helpers.waitVisibleByXpath(driver, "//div[@class='ant-space-item']"); //TODO: id needed
        Helpers.clickByXpath(driver, "//div[@class='ant-space-item']");
        Helpers.clickByXpath(driver, "//span[text()='English']");

    }

    @BeforeEach
    void closeWindow() {
        Helpers.clickByXpathWithAttempts(driver, "//i[@class='air-bo-2-cross']", 10);
        Helpers.waitInvisibleByXpath(driver, "//div[text()='Welcome to unTill Air']");
    }

    @AfterEach
    void tearDown() {
        Auth.logout(driver);
        driver.close();
    }

    public void deleteFirstItem() {
        String del = "//div[@class='antd-table-row-actions']";
        String trash = "//i[@class='air-bo-2-trash-can']";
        String question = "//div[text()='Are you sure you want to delete this item?']";
        String confirm = "//span[text()='Yes']";
        List<WebElement> menuItem = driver.findElements(By.xpath(del));
        Actions mouse = new Actions(driver);
        mouse.moveToElement(menuItem.get(0)).build().perform();
        Helpers.waitVisibleByXpath(driver, trash);
        Helpers.clickByXpath(driver, trash);
        Helpers.waitVisibleByXpath(driver, question);
        Helpers.clickByXpath(driver, confirm);
    }

    public void confirmContinue() {
        String continueXP = "//span[text()='Continue']";
        Helpers.clickByXpath(driver, continueXP);
        Helpers.waitInvisibleByXpath(driver, continueXP);
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
        Helpers.scrollAndClickByXpath(driver,"//span[text()='All items']");
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
