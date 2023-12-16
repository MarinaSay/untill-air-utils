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

public class TestSpaces {

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

   // @AfterEach
    //void tearDown() {
     //   Auth.logout(driver);
     //   driver.close();
  //  }

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
