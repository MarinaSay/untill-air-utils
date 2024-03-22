package com.untillairutils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.testng.AssertJUnit.assertFalse;

public class TestLoginPage extends TestBase{
    @Test
    void logIn() {
        Helpers.waitId(driver, "normal_login_login");
        Helpers.inputById(driver, "normal_login_login", getRequiredEnv(ENV_LOGIN));
        Helpers.inputById(driver, "normal_login_password", getRequiredEnv(ENV_PASSWORD));
        assertFalse(driver.findElements(By.id("normal_login_login")).isEmpty());
        assertFalse(driver.findElements(By.id("normal_login_password")).isEmpty());
    }

    @Test
    void checkPageElement() {
        Helpers.waitVisibleByXpath(driver, "//div[text()= 'Hello!']");
        Helpers.clickByXpath(driver, "//span[text()='Sign up']");
        assertFalse(driver.findElements(By.xpath("//div[text()='Create new account']")).isEmpty());
        Helpers.clickByXpath(driver, "//span[text()='Sign in']");
        Helpers.waitVisibleByXpath(driver, "//div[text()= 'Hello!']");
        assertFalse(driver.findElements(By.xpath("//div[text()='Hello!']")).isEmpty());
    }

    @Test
    void checkForgotPassword() {
        Helpers.waitVisibleByXpath(driver, "//div[text()= 'Hello!']");
        assertFalse(driver.findElements(By.xpath("//span[text()='Forgot password?']")).isEmpty());
        Helpers.clickByXpath(driver, "//span[text()='Forgot password?']");
        assertFalse(driver.findElements(By.id("forgot_email")).isEmpty());
        Helpers.inputById(driver, "forgot_email", "LOGIN");
        Helpers.clickByXpath(driver, "//span[text()='Send']");
        assertFalse(driver.findElements(By.xpath("//div[text()='The input is not a valid email']")).isEmpty());
        Helpers.clickByXpath(driver, "//span[text()='Back to Login']");
    }

    @Test
    void checkEnglish() {
        Helpers.waitVisibleByXpath(driver, "//div[text()= 'Hello!']");
        assertFalse(driver.findElements(By.xpath("//span[text()='English']")).isEmpty());
        Helpers.selectDropDownItemByXpath(driver, "//span[text()='English']", "English");
        assertFalse(driver.findElements(By.xpath("//div[text()= 'Hello!']")).isEmpty());
    }

    @Test
    void checkFrench() {
        Helpers.waitVisibleByXpath(driver, "//div[text()= 'Hello!']");
        assertFalse(driver.findElements(By.xpath("//span[text()='English']")).isEmpty());
        Helpers.selectDropDownItemByXpath(driver, "//span[text()='English']", "French");
        assertFalse(driver.findElements(By.xpath("//div[text()= 'Salut !']")).isEmpty());
    }

    @Test
    void checkDutch() {
        Helpers.waitVisibleByXpath(driver, "//div[text()= 'Hello!']");
        assertFalse(driver.findElements(By.xpath("//span[text()='English']")).isEmpty());
        Helpers.selectDropDownItemByXpath(driver, "//span[text()='English']", "Dutch");
        assertFalse(driver.findElements(By.xpath("//div[text()= 'Hallo']")).isEmpty());
    }
}
