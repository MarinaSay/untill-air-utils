package com.untillairutils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocationLoader {

	String url;
	String login;
	String password;
	WebDriver driver;
	String location;

	public LocationLoader(String url, String login, String password, String location) {
		this.url = url;
		this.login = login;
		this.password = password;
		this.driver = new ChromeDriver();
		this.location = location;

	}

	public static void main(String[] args) {
		if (args.length != 5) {
			System.out
					.print("Syntax: LocationLoader <path_to_chromedriver> <url> <username> <password> <locationname>");
			System.exit(1);
		}
		System.setProperty("webdriver.chrome.driver", args[0]);

		LocationLoader loader = new LocationLoader(args[1], args[2], args[3], args[4]);

		loader.loadLocation();

	}

	private void loadCategories() {

		Helpers.clickByXpath(driver, "//span[text()='Categories']");

		WebDriverWait wait = new WebDriverWait(driver, 10);
		ExpectedCondition<WebElement> c1 = ExpectedConditions
				.elementToBeClickable(By.xpath("//span[text()='Add new category']"));
		ExpectedCondition<WebElement> c2 = ExpectedConditions
				.elementToBeClickable(By.xpath("//span[text()='Add your first category']"));
		wait.until(ExpectedConditions.or(c1, c2));

		Article[] articles = Article.getTestArticles();
		for (Article a : articles) {
			String xp = String.format("//span[text()='%s']", a.category);
			if (driver.findElements(By.xpath(xp)).size() == 0) {
				Helpers.clickByXpath(driver, "//span[text()='Add new category']");
				Helpers.inputById(driver, "names_0", a.category);

				// TODO: make like... String continueXP2 = String.format("//span[text()='%s']",
				// Labels.CONTINUE);

				String continueXP = "//span[text()='Continue']";
				Helpers.clickByXpath(driver, continueXP);
				Helpers.waitInvisibleByXpath(driver, continueXP);
				Helpers.waitVisibleByXpath(driver, xp);

			}
		}
	}

	private void loadFoodGroups() {
		Helpers.clickByXpath(driver, "//span[text()='Groups']");
		Helpers.waitByXpath(driver, "//span[text()='Add new group']");

		WebDriverWait wait = new WebDriverWait(driver, 10);
		ExpectedCondition<WebElement> c1 = ExpectedConditions
				.elementToBeClickable(By.xpath("//span[text()='Add new group']"));
		ExpectedCondition<WebElement> c2 = ExpectedConditions
				.elementToBeClickable(By.xpath("//span[text()='Add your first group']"));
		wait.until(ExpectedConditions.or(c1, c2));

		Map<String, String> foodGroups = new HashMap<>();
		Article[] articles = Article.getTestArticles();
		for (Article a : articles) {
			foodGroups.put(a.foodGroup, a.category);
		}

		for (Map.Entry<String, String> entry : foodGroups.entrySet()) {
			String foodGroup = entry.getKey();
			String category = entry.getValue();

			String searchFG = "//input[contains(@class, 'ant-input-borderless')]";
			if (driver.findElements(By.xpath(searchFG)).size() > 0) {
				Helpers.inputByXpath(driver, searchFG, foodGroup);
			}

			String xp = String.format("//span[text()='%s']", foodGroup);

			if (driver.findElements(By.xpath(xp)).size() == 0) {
				driver.findElement(By.xpath("//span[text()='Add new group']")).click();
				Helpers.inputById(driver, "name", foodGroup);
				driver.findElement(By.id("id_category")).click();
				xp = String.format("//div[@class='ant-select-item-option-content' and text()='%s']", category);
				Helpers.clickByXpath(driver, xp);

				driver.findElement(By.id("id_vat")).click();
				xp = String.format("//div[@class='ant-select-item-option-content' and text()='%s']", "Zero");
				Helpers.clickByXpath(driver, xp);

				String continueXp = "//span[text()='Continue']";
				Helpers.clickByXpath(driver, continueXp);
				Helpers.waitInvisibleByXpath(driver, continueXp);

			}

		}
		Helpers.inputByXpath(driver, "//input[contains(@class, 'ant-input-borderless')]", "");

	}

	private void loadDepartmens() {
		Helpers.clickByXpath(driver, "//span[text()='Departments']");
		Helpers.waitByXpath(driver, "//span[text()='Add new department']");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		ExpectedCondition<WebElement> c1 = ExpectedConditions
				.elementToBeClickable(By.xpath("//span[text()='Add new department']"));
		ExpectedCondition<WebElement> c2 = ExpectedConditions
				.elementToBeClickable(By.xpath("//span[text()='Add your first department']"));
		wait.until(ExpectedConditions.or(c1, c2));

		Map<String, String> departmens = new HashMap<>();
		Article[] articles = Article.getTestArticles();
		for (Article a : articles) {
			departmens.put(a.department, a.foodGroup);
		}
		for (Map.Entry<String, String> entry : departmens.entrySet()) {
			String department = entry.getKey();
			String foodGroup = entry.getValue();

			String searchDep = "//input[contains(@class, 'ant-input-borderless')]";
			if (driver.findElements(By.xpath(searchDep)).size() > 0) {
				Helpers.inputByXpath(driver, searchDep, department);
			}
			String xp = String.format("//span[text()='%s']", department);
			if (driver.findElements(By.xpath(xp)).size() == 0) {
				driver.findElement(By.xpath("//span[text()='Add new department']")).click();
				Helpers.inputById(driver, "name", department);

				Helpers.selectDropDownItemById(driver, "id_food_group", foodGroup);

				String saveXp = "//span[text()='Save']";
				Helpers.clickByXpath(driver, saveXp);
				Helpers.waitInvisibleByXpath(driver, saveXp);

			}
		}
	}

	private void loadCourses() {
		Helpers.clickByXpath(driver, "//span[text()='Courses']");
		Helpers.waitByXpath(driver, "//span[text()='Add new course']");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		ExpectedCondition<WebElement> c1 = ExpectedConditions
				.elementToBeClickable(By.xpath("//tbody[@Class='ant-table-tbody']"));
		ExpectedCondition<WebElement> c2 = ExpectedConditions
				.elementToBeClickable(By.xpath("//span[text()='Add your first course']"));
		wait.until(ExpectedConditions.or(c1, c2));

		String xp = String.format("//span[text()='%s']", "Default course");
		if (driver.findElements(By.xpath(xp)).size() == 0) {
			Helpers.clickByXpath(driver, "//span[text()='Add new course']");
			Helpers.inputById(driver, "name", "Default course");

			String continueXP = "//span[text()='Continue']";
			Helpers.clickByXpath(driver, continueXP);
			Helpers.waitInvisibleByXpath(driver, continueXP);
		}
	}

	private void loadArticles() {
		Helpers.clickByXpath(driver, "//span[text()='Articles']");
		Helpers.waitByXpath(driver, "//span[text()='Add new article']");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		ExpectedCondition<WebElement> c1 = ExpectedConditions
				.elementToBeClickable(By.xpath("//span[text()='Add new article']"));
		ExpectedCondition<WebElement> c2 = ExpectedConditions
				.elementToBeClickable(By.xpath("//span[text()='Add your first article']"));
		wait.until(ExpectedConditions.or(c1, c2));

		Article[] articles = Article.getTestArticles();

		for (Article a : articles) {

			String searchArticle = "//input[contains(@class, 'ant-input-borderless')]";
			if (driver.findElements(By.xpath(searchArticle)).size() > 0) {
				Helpers.inputByXpath(driver, searchArticle, a.name);
			}
			String xp = String.format("//span[text()='%s']", a.name);
			if (driver.findElements(By.xpath(xp)).size() == 0) {
				Helpers.clickByXpathWithAttempts(driver, "//span[text()='Add new article']", 100);
				Helpers.inputById(driver, "name", a.name);

				String inputPrice = "//input[contains(@class, 'ant-input-number-input')]";

				Helpers.inputByXpath(driver, inputPrice, Helpers.formatDbl(a.price));

				Helpers.selectDropDownItemById(driver, "id_departament", a.department);

				Helpers.selectDropDownItemById(driver, "id_courses", "Default course");

				String saveXp = "//span[text()='Save']";
				Helpers.clickByXpath(driver, saveXp);
				Actions actions = new Actions(driver);
				actions.moveByOffset(0, 50);
				Helpers.waitInvisibleByXpath(driver, saveXp);
			}
		}

	}

	private void loadUsers() {
		Helpers.clickByXpath(driver, "//span[text()='Users']");
		Helpers.waitByXpath(driver, "//span[text()='Add new user']");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		ExpectedCondition<WebElement> c1 = ExpectedConditions
				.elementToBeClickable(By.xpath("//span[text()='Add new user']"));
		ExpectedCondition<WebElement> c2 = ExpectedConditions
				.elementToBeClickable(By.xpath("//span[text()='Add your first user']"));
		wait.until(ExpectedConditions.or(c1, c2));

		User[] users = User.inputUsers();
		for (User s : users) {
			String searchUser = "//input[contains(@class, 'ant-input-borderless')]";
			if (driver.findElements(By.xpath(searchUser)).size() > 0) {
				Helpers.inputByXpath(driver, searchUser, s.displayName);
			}
			String inputDisplayName = String.format("//span[text()='%s']", s.displayName);
			if (driver.findElements(By.xpath(inputDisplayName)).size() == 0) {
				Helpers.clickByXpathWithAttempts(driver, "//span[text()='Add new user']", 100);
				Helpers.inputById(driver, "name", s.displayName);
				Helpers.inputById(driver, "firstname", s.firstName);
				Helpers.inputById(driver, "lastname", s.lastName);
				Helpers.selectDropDownItemById(driver, "language", s.language);
				Helpers.inputById(driver, "user_poscode", s.posPassword);
				String saveXp = "//span[text()='Save']";
				Helpers.clickByXpath(driver, saveXp);
			}
		}

	}

	public void loadLocation() {

		driver.get(url);
		driver.manage().window().maximize();
		Auth.login(driver, login, password);

		Helpers.clickByXpathWithAttempts(driver, "//i[@class='air-bo-2-cross']", 10);

		List<WebElement> chooseLocation = driver.findElements(By.xpath("//header/div[1]/span[2]/div"));

		if (chooseLocation.size() != 0) {
			Helpers.selectDropDownItemByXpath(driver, "//header/div[1]/span[2]/div", location);
		} else

			Helpers.clickByXpath(driver, "//span[text()='Products']");

		loadCategories();
		loadFoodGroups();
		loadDepartmens();
		loadCourses();
		loadArticles();
		loadUsers();

	}

}
