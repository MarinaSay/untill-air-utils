package com.untillairutils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

public abstract class Helpers {

    public static void inputById(WebDriver driver, String id, String text) {
        waitId(driver, id);
        WebElement element = driver.findElement(By.id(id));
        element.click();
        element.sendKeys(text);

    }

    public static String selectChord() {
        String osName = System.getProperty("os.name");
        if (osName.startsWith("Mac OS"))
            return Keys.chord(Keys.COMMAND, "a");
        return Keys.chord(Keys.CONTROL, "a");
    }

    public static void inputByXpath(WebDriver driver, String xPath, String text) {
        waitByXpath(driver, xPath);
        WebElement element = driver.findElement(By.xpath(xPath));

        element.sendKeys(selectChord(), text);

    }

    public static void clickByXpath(WebDriver driver, String xPath) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
        driver.findElement(By.xpath(xPath)).click();
    }

    public static void clickByXpathWithAttempts(WebDriver driver, String xPath, int attempts) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
        for (int i = 0; i < attempts; i++) {
            try {
                driver.findElement(By.xpath(xPath)).click();
                return;
            } catch (ElementClickInterceptedException e) {
                continue;
            }
        }
        throw new ElementClickInterceptedException("unable to click " + xPath);
    }

    public static void waitVisibleByXpath(WebDriver driver, String xPath) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
    }

    public static void waitInvisibleByXpath(WebDriver driver, String xPath) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xPath)));
    }

    public static void waitId(WebDriver driver, String id) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
    }

    public static void waitByXpath(WebDriver driver, String xp) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xp)));
    }

    public static void scrollAndClickByXpath(WebDriver driver, String xPath) {
        WebElement item = driver.findElement(By.xpath(xPath));
        Actions actions = new Actions(driver);
        actions.moveToElement(item);
        actions.perform();
        item.click();
    }

    public static void selectDropDownItemById(WebDriver driver, String id, String item) {
        WebElement dropDown = driver.findElement(By.id(id));
        dropDown.click();
        String popup = String.format("//div[@id=\"%s_list\"]/following-sibling::div[@class=\"rc-virtual-list\"]", id);
        Helpers.waitVisibleByXpath(driver, popup);
        String xp = String.format("//div[@class='ant-select-item-option-content' and text()='%s']", item);
        for (int i = 0; i < 100; i++) {
            List<WebElement> list = driver.findElements(By.xpath(xp));

            try {
                if (list.size() != 0) {

                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", list.get(0));
                    Thread.sleep(20);

                    clickByXpath(driver, xp);
                    Helpers.waitInvisibleByXpath(driver, xp);
                    return;
                }
                dropDown.sendKeys(Keys.DOWN);

                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        throw new RuntimeException("Item not found: " + item);
    }

    public static void selectDropDownItemByXpath(WebDriver driver, String xpath, String item) {
        WebElement dropDown = driver.findElement(By.xpath(xpath));
        dropDown.click();
        Helpers.waitVisibleByXpath(driver, "//div[@class=\"rc-virtual-list-holder\"]");
        String xp = String.format("//div[@class='ant-select-item-option-content' and text()='%s']", item);
        for (int i = 0; i < 100; i++) {
            List<WebElement> list = driver.findElements(By.xpath(xp));

            try {
                if (list.size() != 0) {

                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", list.get(0));
                    Thread.sleep(20);

                    clickByXpath(driver, xp);
                    return;
                }
                dropDown.sendKeys(Keys.DOWN);

                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        throw new RuntimeException("Item not found: " + item);
    }


    public static String formatDbl(double num) {
        DecimalFormatSymbols decimalSymbols = DecimalFormatSymbols.getInstance();
        decimalSymbols.setDecimalSeparator('.');
        DecimalFormat fmt = new DecimalFormat("0.00", decimalSymbols);

        return fmt.format(num);
    }


}
