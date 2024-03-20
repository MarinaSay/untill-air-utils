package com.untillairutils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

public class TestHomePage extends TestBase {
    @BeforeEach
    void setUp() {
        loginAtLocation();
        setEnglish();
    }
    @AfterEach
    void tearDown() {
        logout();
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
    void checkWelcomePopUp() {
        assertFalse(driver.findElements(By.xpath("//div[text()='Welcome to unTill Air']")).isEmpty());
        assertFalse(driver.findElements(By.xpath("//div[text()='Add articles']")).isEmpty());
        assertFalse(driver.findElements(By.xpath("//div[text()='Add users']")).isEmpty());
        assertFalse(driver.findElements(By.xpath("//div[text()='Setup equipment']")).isEmpty());
        Helpers.clickByXpath(driver, "//i[@class='air-bo-2-cross']");
        assertTrue(driver.findElements(By.xpath("//div[text()='Welcome to unTill Air']")).isEmpty());
    }

    @Test
    void checkLanguages() {
        Helpers.waitVisibleByXpath(driver, "//div[@class='ant-space-item']");
        Helpers.clickByXpath(driver, "//div[@class='ant-space-item']");
        Helpers.clickByXpath(driver, "//span[text()='English']");
        assertFalse(driver.findElements(By.xpath("//div[contains(text(),'Hello')]")).isEmpty());
        assertFalse(driver.findElements(By.xpath("//div[text()='Discounts']")).isEmpty());
        assertFalse(driver.findElements(By.xpath("//div[text()='Voids']")).isEmpty());
        assertFalse(driver.findElements(By.xpath("//div[text()='Bills average']")).isEmpty());
        assertFalse(driver.findElements(By.xpath("//div[text()='Sales']")).isEmpty());
        assertFalse(driver.findElements(By.xpath("//div[text()='Sales per hour']")).isEmpty());
        assertFalse(driver.findElements(By.xpath("//span[contains(text(),'Today')]")).isEmpty());
        Helpers.clickByXpath(driver, "//div[@class='ant-space-item']");
        Helpers.scrollAndClickByXpath(driver, "//span[text()='Nederlands']");
        assertFalse(driver.findElements(By.xpath("//div[contains(text(),'Hallo')]")).isEmpty());
        assertFalse(driver.findElements(By.xpath("//div[text()='Kortingen']")).isEmpty());
        assertFalse(driver.findElements(By.xpath("//div[text()='Correcties']")).isEmpty());
        assertFalse(driver.findElements(By.xpath("//div[text()='Rekeningen-gemiddelde']")).isEmpty());
        assertFalse(driver.findElements(By.xpath("//div[text()='Betalingen']")).isEmpty());
        assertFalse(driver.findElements(By.xpath("//div[text()='Betalingen per uur']")).isEmpty());
        assertFalse(driver.findElements(By.xpath("//span[contains(text(),'Vandaag')]")).isEmpty());
        Helpers.clickByXpath(driver, "//div[@class='ant-space-item']");
        Helpers.scrollAndClickByXpath(driver, "//span[text()='Français']");
        assertFalse(driver.findElements(By.xpath("//div[contains(text(),'Salut')]")).isEmpty());
        assertFalse(driver.findElements(By.xpath("//div[text()='Remises']")).isEmpty());
        assertFalse(driver.findElements(By.xpath("//div[text()='Annulations']")).isEmpty());
        assertFalse(driver.findElements(By.xpath("//div[text()='Moyenne des factures']")).isEmpty());
       // assertFalse(driver.findElements(By.xpath("//div[contains(text(),'Chiffre']")).isEmpty());
        assertFalse(driver.findElements(By.xpath("//div[contains(text(),'par heure')]")).isEmpty());
        assertFalse(driver.findElements(By.xpath("//span[contains(text(),'Aujourd')]")).isEmpty());
        Helpers.waitVisibleByXpath(driver, "//div[@class='ant-space-item']");
        Helpers.clickByXpath(driver, "//div[@class='ant-space-item']");
        Helpers.clickByXpath(driver, "//span[text()='English']");
    }
    @Test
    void checkSearch() {
        Helpers.waitVisibleByXpath(driver, "//div[@class='ant-space-item']");
        Helpers.clickByXpath(driver, "//i[@class='air-bo-2-loupe']");
        assertFalse(driver.findElements(By.xpath("//input[@placeholder='Search...']")).isEmpty());
        Helpers.inputByXpath(driver, "//input[@placeholder='Search...']", "Hello");
        assertFalse(driver.findElements(By.xpath("//input[@value='Hello']")).isEmpty());
        Helpers.clickByXpath(driver, "//i[@class='air-bo-2-cross clickable']");
    }
}
