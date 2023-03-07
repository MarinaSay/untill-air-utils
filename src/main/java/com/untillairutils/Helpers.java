package com.untillairutils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Helpers {
	
	public static void inputById(WebDriver driver, String id, String text) {
		waitId(driver,id);
		WebElement element = driver.findElement(By.id(id));
		element.click();
		element.sendKeys(text);

	}
	
	public  static void inputByXpath(WebDriver driver, String xPath, String text) {
		waitByXpath(driver,xPath);
		WebElement element = driver.findElement(By.xpath(xPath));

		element.sendKeys(Keys.chord(Keys.COMMAND, "a"), text);

	}

	public  static void clickByXpath(WebDriver driver, String xPath) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
		driver.findElement(By.xpath(xPath)).click();
	}

	public  static void waitVisibleByXpath(WebDriver driver, String xPath) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
	}


	public  static void waitInvisibleByXpath(WebDriver driver, String xPath) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xPath)));
	}

	public  static void waitId(WebDriver driver, String id) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
	}

	public  static void waitByXpath(WebDriver driver, String xp) {
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
	
	public static void selectDropDownItem(WebDriver driver, By by, String item) {
		WebElement dropDown = driver.findElement(by);
		dropDown.click();
		Helpers.waitVisibleByXpath(driver, "//div[@class='rc-virtual-list']");
		String xp = String.format("//div[@class='ant-select-item-option-content' and text()='%s']", item);
		for (int i=0;i<100;i++) {
			List<WebElement>list=driver.findElements(By.xpath(xp));
			if (list.size()!=0) {
				Actions actions = new Actions(driver);
				actions.moveToElement(list.get(0));
				try {
					actions.perform();					
				} catch (MoveTargetOutOfBoundsException e) {
					// do nothing
				}
				clickByXpath(driver, xp);
				return;
			} 
			dropDown.sendKeys(Keys.DOWN);
			try {
				Thread.sleep(30);
			} catch(InterruptedException e) {
				throw new RuntimeException(e);
			} 
		}
		throw new RuntimeException("Item not found: "+item);
	}
}
