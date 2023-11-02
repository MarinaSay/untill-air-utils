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

class BasicTest {
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

    @Test
    void testUserLogIn() {

        Helpers.clickByXpathWithAttempts(driver, "//span[contains(@class, 'ant-avatar-lg')]", 10);
        Helpers.clickByXpathWithAttempts(driver, "//span[text()='My profile']", 10);
        Helpers.waitVisibleByXpath(driver, "//li[text()='Account']");
        assertFalse(driver.findElements(By.id("user_name")).isEmpty());
        assertFalse(driver.findElements(By.id("user_email")).isEmpty());
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
    void testProduct() {
        Helpers.clickByXpath(driver, "//span[text()='Products']");
        Helpers.clickByXpath(driver, "//span[text()='Categories']");
        Helpers.clickByXpath(driver, "//span[text()='Add new category']");
        Helpers.inputById(driver, "names_0", "Food");
        confirmContinue();
        assertFalse(driver.findElements(By.xpath("//span[text()='Food']")).isEmpty());
        Helpers.clickByXpath(driver, "//span[text()='Groups']");
        Helpers.waitVisibleByXpath(driver, "//span[text()='Add new group']");
        Helpers.clickByXpath(driver, "//span[text()='Add new group']");
        Helpers.clickByXpath(driver, "//input[@id='name']");
        Helpers.inputById(driver, "name", "Hot");
        driver.findElement(By.id("id_category")).click();
        String foodXp = String.format("//div[@class='ant-select-item-option-content' and text()='%s']", "Food");
        Helpers.clickByXpath(driver, foodXp);
        driver.findElement(By.id("id_vat")).click();
        String vatXp = String.format("//div[@class='ant-select-item-option-content' and text()='%s']", "Standard");
        Helpers.clickByXpath(driver, vatXp);
        confirmContinue();
        assertFalse(driver.findElements(By.xpath("//span[text()='Hot']")).isEmpty());
        Helpers.clickByXpath(driver, "//span[text()='Departments']");
        Helpers.waitVisibleByXpath(driver, "//span[text()='Add new department']");
        Helpers.clickByXpath(driver, "//span[text()='Add new department']");
        Helpers.clickByXpath(driver, "//input[@id='name']");
        Helpers.inputById(driver, "name", "Pizza");
        driver.findElement(By.id("id_food_group")).click();
        String group = String.format("//div[@class='ant-select-item-option-content' and text()='%s']", "Hot");
        Helpers.clickByXpath(driver, group);
        String saveXp = "//span[text()='Save']";
        Helpers.clickByXpath(driver, saveXp);
        Helpers.waitInvisibleByXpath(driver, saveXp);
        assertFalse(driver.findElements(By.xpath("//span[text()='Pizza']")).isEmpty());
        Helpers.clickByXpath(driver, "//span[text()='Articles']");
        Helpers.waitVisibleByXpath(driver, "//span[text()='Add new article']");
        Helpers.clickByXpath(driver, "//span[text()='Add new article']");
        Helpers.clickByXpath(driver, "//input[@id='name']");
        Helpers.inputById(driver, "name", "Margarita");
        String inputPrice = "//input[contains(@class, 'ant-input-number-input')]";
        Helpers.inputByXpath(driver, inputPrice, formatDbl(10));
        Helpers.selectDropDownItemById(driver, "id_departament", "Pizza");
        Helpers.selectDropDownItemById(driver, "id_courses", "No course");
        Helpers.clickByXpath(driver, saveXp);
        Helpers.waitInvisibleByXpath(driver, saveXp);
        assertFalse(driver.findElements(By.xpath("//span[text()='Margarita']")).isEmpty());
        deleteFirstItem();
        assertTrue(driver.findElements(By.xpath("//span[text()='Margarita']")).isEmpty());
        Helpers.clickByXpath(driver, "//span[text()='Departments']");
        deleteFirstItem();
        assertTrue(driver.findElements(By.xpath("//span[text()='Pizza']")).isEmpty());
        Helpers.clickByXpath(driver, "//span[text()='Groups']");
        deleteFirstItem();
        assertTrue(driver.findElements(By.xpath("//span[text()='Hot']")).isEmpty());
        Helpers.clickByXpath(driver, "//span[text()='Categories']");
        deleteFirstItem();
        assertTrue(driver.findElements(By.xpath("//span[text()='Food']")).isEmpty());
        Helpers.waitInvisibleByXpath(driver, "//div[contains(text(),'is deleted')]");
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
        Helpers.selectDropDownItemById(driver, "check_type", "All items");
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