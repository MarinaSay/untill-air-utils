package com.untillairutils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocationCleaner {
	String url;
	String login;
	String password;
	WebDriver driver;
	String location;

	public LocationCleaner(String url, String login, String password, String location) {
		this.url = url;
		this.login = login;
		this.password = password;
		this.driver = new ChromeDriver();
		this.location = location;

	}

	public static void main(String[] args) {
		if (args.length != 5) {
			System.out
					.print("Syntax: LocationCleaner <path_to_chromedriver> <url> <username> <password> <locationname>");
			System.exit(1);
		}
		System.setProperty("webdriver.chrome.driver", args[0]);

		LocationCleaner loader = new LocationCleaner(args[1], args[2], args[3], args[4]);

		loader.cleanLocation();

	}

	public void cleanLocation() {
		driver.get(url);
		driver.manage().window().maximize();
		Auth.login(driver, login, password);

		Helpers.clickByXpathWithAttempts(driver, "//i[@class='air-bo-2-cross']", 10);

		Helpers.selectDropDownItemByXpath(driver, "//header/div[1]/span[2]/div", location);

		Helpers.clickByXpath(driver, "//span[text()='Products']");
		cleanArticle();
		cleanDepartments();
		cleanCourses();
		cleanFoodGroups();
		cleanCategories();
	}

	private void cleanArticle() {
		Helpers.clickByXpath(driver, "//span[text()='Articles']");

		WebDriverWait wait = new WebDriverWait(driver, 10);
		ExpectedCondition<WebElement> c1 = ExpectedConditions
				.elementToBeClickable(By.xpath("//span[text()='Add new article']"));
		ExpectedCondition<WebElement> c2 = ExpectedConditions
				.elementToBeClickable(By.xpath("//span[text()='Add your first article']"));
		ExpectedCondition<WebElement> c3 = ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@class='style_header__OgYnX']"));
		wait.until(ExpectedConditions.or(c1, c2, c3));

		List<WebElement> menu = driver.findElements(By.xpath("//div[@class='antd-table-row-actions']"));

		while (menu.size() != 0) {
			Actions build = new Actions(driver);
			build.moveToElement(menu.get(0)).build().perform();
			Helpers.waitVisibleByXpath(driver, "//i[@class='air-bo-2-trash-can']");
			Helpers.clickByXpath(driver, "//i[@class='air-bo-2-trash-can']");
			Helpers.waitVisibleByXpath(driver, "//button[@class='ant-btn ant-btn-primary ant-btn-sm']");
			Helpers.clickByXpath(driver, "//button[@class='ant-btn ant-btn-primary ant-btn-sm']");
			menu = driver.findElements(By.xpath("//div[@class='antd-table-row-actions']"));
		}

		Helpers.clickByXpath(driver, "//span[text()='Departments']");
	}

	private void cleanDepartments() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		ExpectedCondition<WebElement> c1 = ExpectedConditions
				.elementToBeClickable(By.xpath("//span[text()='Add new department']"));
		ExpectedCondition<WebElement> c2 = ExpectedConditions
				.elementToBeClickable(By.xpath("//span[text()='Add your first department']"));
		ExpectedCondition<WebElement> c3 = ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@class='style_header__OgYnX']"));
		wait.until(ExpectedConditions.or(c1, c2, c3));

		List<WebElement> menu = driver.findElements(By.xpath("//div[@class='antd-table-row-actions']"));

		while (menu.size() != 0) {
			Actions build = new Actions(driver);
			build.moveToElement(menu.get(0)).build().perform();
			Helpers.waitVisibleByXpath(driver, "//i[@class='air-bo-2-trash-can']");
			Helpers.clickByXpath(driver, "//i[@class='air-bo-2-trash-can']");
			Helpers.waitVisibleByXpath(driver, "//button[@class='ant-btn ant-btn-primary ant-btn-sm']");
			Helpers.clickByXpath(driver, "//button[@class='ant-btn ant-btn-primary ant-btn-sm']");
			menu = driver.findElements(By.xpath("//div[@class='antd-table-row-actions']"));
		}
		Helpers.clickByXpath(driver, "//span[text()='Courses']");
	}

	private void cleanCourses() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		ExpectedCondition<WebElement> c1 = ExpectedConditions
				.elementToBeClickable(By.xpath("//span[text()='Add new course']"));
		ExpectedCondition<WebElement> c2 = ExpectedConditions
				.elementToBeClickable(By.xpath("//span[text()='Add your first course']"));
		ExpectedCondition<WebElement> c3 = ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@class='style_header__OgYnX']"));
		wait.until(ExpectedConditions.or(c1, c2, c3));

		List<WebElement> menu = driver.findElements(By.xpath("//div[@class='antd-table-row-actions']"));

		while (menu.size() != 0) {
			Actions build = new Actions(driver);
			build.moveToElement(menu.get(0)).build().perform();
			Helpers.waitVisibleByXpath(driver, "//i[@class='air-bo-2-trash-can']");
			Helpers.clickByXpath(driver, "//i[@class='air-bo-2-trash-can']");
			Helpers.waitVisibleByXpath(driver, "//button[@class='ant-btn ant-btn-primary ant-btn-sm']");
			Helpers.clickByXpath(driver, "//button[@class='ant-btn ant-btn-primary ant-btn-sm']");
			menu = driver.findElements(By.xpath("//div[@class='antd-table-row-actions']"));
		}
		Helpers.clickByXpath(driver, "//span[text()='Groups']");
	}

	private void cleanFoodGroups() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		ExpectedCondition<WebElement> c1 = ExpectedConditions
				.elementToBeClickable(By.xpath("//span[text()='Add new group']"));
		ExpectedCondition<WebElement> c2 = ExpectedConditions
				.elementToBeClickable(By.xpath("//span[text()='Add your first group']"));
		ExpectedCondition<WebElement> c3 = ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@class='style_header__OgYnX']"));
		wait.until(ExpectedConditions.or(c1, c2, c3));

		List<WebElement> menu = driver.findElements(By.xpath("//div[@class='antd-table-row-actions']"));

		while (menu.size() != 0) {
			Actions build = new Actions(driver);
			build.moveToElement(menu.get(0)).build().perform();
			Helpers.waitVisibleByXpath(driver, "//i[@class='air-bo-2-trash-can']");
			Helpers.clickByXpath(driver, "//i[@class='air-bo-2-trash-can']");
			Helpers.waitVisibleByXpath(driver, "//button[@class='ant-btn ant-btn-primary ant-btn-sm']");
			Helpers.clickByXpath(driver, "//button[@class='ant-btn ant-btn-primary ant-btn-sm']");
			menu = driver.findElements(By.xpath("//div[@class='antd-table-row-actions']"));
		}
		Helpers.clickByXpath(driver, "//span[text()='Categories']");
	}

	private void cleanCategories() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		ExpectedCondition<WebElement> c1 = ExpectedConditions
				.elementToBeClickable(By.xpath("//span[text()='Add new category']"));
		ExpectedCondition<WebElement> c2 = ExpectedConditions
				.elementToBeClickable(By.xpath("//span[text()='Add your first category']"));
		ExpectedCondition<WebElement> c3 = ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@class='style_header__OgYnX']"));
		wait.until(ExpectedConditions.or(c1, c2, c3));

		List<WebElement> menu = driver.findElements(By.xpath("//div[@class='antd-table-row-actions']"));

		while (menu.size() != 0) {
			Actions build = new Actions(driver);
			build.moveToElement(menu.get(0)).build().perform();
			Helpers.waitVisibleByXpath(driver, "//i[@class='air-bo-2-trash-can']");
			Helpers.clickByXpath(driver, "//i[@class='air-bo-2-trash-can']");
			Helpers.waitVisibleByXpath(driver, "//button[@class='ant-btn ant-btn-primary ant-btn-sm']");
			Helpers.clickByXpath(driver, "//button[@class='ant-btn ant-btn-primary ant-btn-sm']");
			menu = driver.findElements(By.xpath("//div[@class='antd-table-row-actions']"));
		}

	}
}
