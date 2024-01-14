package com.untillairutils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public abstract class TestBase {
    static String ENV_URL = "UNTILLAIR_URL";
    static String ENV_LOGIN = "UNTILLAIR_LOGIN";
    static String ENV_PASSWORD = "UNTILLAIR_PASSWORD";
    static String ENV_LOCATION = "UNTILLAIR_LOCATION";
    WebDriver driver;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    public TestBase() {
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeEach
    void setUpBase() {
        driver.get(getRequiredEnv(ENV_URL));
    }

    @AfterEach
    void tearDownBase() {
        //driver.close();
    }

    String getRequiredEnv(String name) {
        if (System.getenv(name) == null) {
            throw new RuntimeException("Env " + name + " not defined");
        }
        return System.getenv(name);
    }

    void loginAtLocation() {
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
    void logout() {
        Helpers.clickByXpathWithAttempts(driver, "//span[contains(@class, 'ant-avatar-lg')]", 10);
        Helpers.clickByXpathWithAttempts(driver, "//span[text()='Logout']", 10);
    }
    void setEnglish() {
        Helpers.waitVisibleByXpath(driver, "//div[@class='ant-space-item']"); //TODO: id needed
        Helpers.clickByXpath(driver, "//div[@class='ant-space-item']");
        Helpers.clickByXpath(driver, "//span[text()='English']");

    }
    void closeWelcomePopUp() {
        Helpers.clickByXpathWithAttempts(driver, "//i[@class='air-bo-2-cross']", 10);
        Helpers.waitInvisibleByXpath(driver, "//div[text()='Welcome to unTill Air']");
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
}
