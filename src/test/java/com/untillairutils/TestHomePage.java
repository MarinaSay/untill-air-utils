package com.untillairutils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

public class TestHomePage {

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



    // @AfterEach
    // void tearDown() {
    //   Auth.logout(driver);
    //    driver.close();
    //  }

    @Test
    void checkBlackWindow(){
        assertFalse(driver.findElements(By.xpath("//div[text()='Welcome to unTill Air']")).isEmpty());
        assertFalse(driver.findElements(By.xpath("//div[text()='Add articles']")).isEmpty());
        assertFalse(driver.findElements(By.xpath("//div[text()='Add users']")).isEmpty());
        assertFalse(driver.findElements(By.xpath("//div[text()='Setup equipment']")).isEmpty());
        Helpers.clickByXpath(driver, "//i[@class='air-bo-2-cross']");
        assertTrue(driver.findElements(By.xpath("//div[text()='Welcome to unTill Air']")).isEmpty());

    }
@Test
    void checkLanguages(){
    Helpers.waitVisibleByXpath(driver, "//div[@class='ant-space-item']");
    Helpers.clickByXpath(driver, "//div[@class='ant-space-item']");
    Helpers.clickByXpath(driver, "//span[text()='English']");
    assertFalse(driver.findElements(By.xpath("//div[contains(text(),'Hello')]")).isEmpty());
    assertFalse(driver.findElements(By.xpath("//div[text()='Discounts']")).isEmpty());
    assertFalse(driver.findElements(By.xpath("//div[text()='Voids']")).isEmpty());
    assertFalse(driver.findElements(By.xpath("//div[text()='Bills average']")).isEmpty());
    assertFalse(driver.findElements(By.xpath("//div[text()='Orders']")).isEmpty());
    assertFalse(driver.findElements(By.xpath("//div[text()='Sales per hour']")).isEmpty());
    assertFalse(driver.findElements(By.xpath("//span[contains(text(),'Today')]")).isEmpty());
    Helpers.clickByXpath(driver, "//div[@class='ant-space-item']");
    Helpers.scrollAndClickByXpath(driver, "//span[text()='Nederlands']");
    assertFalse(driver.findElements(By.xpath("//div[contains(text(),'Hallo')]")).isEmpty());
    assertFalse(driver.findElements(By.xpath("//div[text()='Kortingen']")).isEmpty());
    assertFalse(driver.findElements(By.xpath("//div[text()='Correcties']")).isEmpty());
    assertFalse(driver.findElements(By.xpath("//div[text()='Rekeningen-gemiddelde']")).isEmpty());
    assertFalse(driver.findElements(By.xpath("//div[text()='Bestellingen']")).isEmpty());
    assertFalse(driver.findElements(By.xpath("//div[text()='Betalingen per uur']")).isEmpty());
    assertFalse(driver.findElements(By.xpath("//span[contains(text(),'Vandaag')]")).isEmpty());
    Helpers.clickByXpath(driver, "//div[@class='ant-space-item']");
    Helpers.scrollAndClickByXpath(driver, "//span[text()='Français']");
    assertFalse(driver.findElements(By.xpath("//div[contains(text(),'Salut')]")).isEmpty());
    assertFalse(driver.findElements(By.xpath("//div[text()='Remises']")).isEmpty());
    assertFalse(driver.findElements(By.xpath("//div[text()='Annulations']")).isEmpty());
    assertFalse(driver.findElements(By.xpath("//div[text()='Moyenne des factures']")).isEmpty());
    assertFalse(driver.findElements(By.xpath("//div[text()='Commandes']")).isEmpty());
    assertFalse(driver.findElements(By.xpath("//div[contains(text(),'Chiffre')]")).isEmpty());
    assertFalse(driver.findElements(By.xpath("//span[contains(text(),'Aujourd')]")).isEmpty());


}
@Test
    void checkSearch(){
    Helpers.waitVisibleByXpath(driver, "//div[@class='ant-space-item']");
    Helpers.clickByXpath(driver, "//i[@class='air-bo-2-loupe']");
    assertFalse(driver.findElements(By.xpath("//input[@placeholder='Search...']")).isEmpty());
    Helpers.inputByXpath(driver,"//input[@placeholder='Search...']","Hello");
    assertFalse(driver.findElements(By.xpath("//input[@value='Hello']")).isEmpty());
    Helpers.clickByXpath(driver,"//i[@class='air-bo-2-cross clickable']");
}

}
