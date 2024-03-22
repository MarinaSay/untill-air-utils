package com.untillairutils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LocationCleaner {
    String url;
    String login;
    String password;

    String location;
    WebDriver driver;

    public static void main(String[] args) {
        if (args.length != 4) {
            System.out
                    .print("Syntax: LocationCleaner <url> <username> <password> <locationname>");
            System.exit(1);
        }

        WebDriver driver = new ChromeDriver();

        String url = args[0];
        String login = args[1];
        String password = args[2];

        driver.get(url);
        driver.manage().window().maximize();
        Auth.login(driver, login, password);

        LocationCleaner cleaner = new LocationCleaner(driver, args[1], args[2], args[3], args[0]);
        cleaner.cleanLocation();

    }

    public LocationCleaner(WebDriver driver, String user, String password, String location, String url) {
        this.driver = driver;
        this.login = user;
        this.password = password;
        this.url = url;
        this.location = location;
    }

    public void cleanLocation() {


        Helpers.clickByXpathWithAttempts(driver, "//i[@class='air-bo-2-cross']", 10);
        Helpers.waitVisibleByXpath(driver, "//header");
        String locationXPATH = "//header//div[contains(@class, 'ant-select')]";
        List<WebElement> chooseLocation = driver.findElements(By.xpath(locationXPATH));

        if (chooseLocation.size() != 0) {
            Helpers.selectDropDownItemByXpath(driver, locationXPATH, location);
        }

        cleanProducts();
        cleanPosUsers();
        cleanSpaces();
        cleanPrinters();

    }


public void cleanItems(String title, String itemName){
    String titleXpath = String.format("//span[text()='%s']", title);
    Helpers.clickByXpath(driver, titleXpath);

    String xpath1 = String.format("//span[text()='Add new %s']", itemName);
    String xpath2 = String.format("//span[text()='Add your first %s']", itemName);
    String xpath3 = "//div[text()='You need to create these entities']";
    String xpath4  = String.format("//span[text()='Add first %s']", itemName);

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    ExpectedCondition<WebElement> c1 = ExpectedConditions
            .elementToBeClickable(By.xpath(xpath1));
    ExpectedCondition<WebElement> c2 = ExpectedConditions
            .elementToBeClickable(By.xpath(xpath2));
    ExpectedCondition<WebElement> c3 = ExpectedConditions.elementToBeClickable(By.xpath(xpath3));
    ExpectedCondition<WebElement> c4 = ExpectedConditions.elementToBeClickable(By.xpath(xpath4));
    wait.until(ExpectedConditions.or(c1, c2, c3, c4));

    String dataRowCell = "//tr[@data-row-key]//td//span";
    List<WebElement> rows = driver.findElements(By.xpath(dataRowCell));
    while (rows.size() != 0) {
        String name = rows.get(0).getText();
        System.out.printf("Deleting %s: %s\n", itemName, name);
        Actions build = new Actions(driver);
        build.moveToElement(rows.get(0)).build().perform();
        Helpers.waitVisibleByXpath(driver, "//i[@class='air-bo-2-trash-can']");
        Helpers.clickByXpath(driver, "//i[@class='air-bo-2-trash-can']");
        Helpers.waitVisibleByXpath(driver, "//button[@class='ant-btn ant-btn-primary ant-btn-sm']");
        Helpers.clickByXpath(driver, "//button[@class='ant-btn ant-btn-primary ant-btn-sm']");
        String namedCell = String.format("//tr[@data-row-key]//td//span[text()='%s']", name);
        Helpers.waitInvisibleByXpath(driver, namedCell);
        rows = driver.findElements(By.xpath(dataRowCell));
    }
}
    public void cleanProducts() {
        Helpers.clickByXpath(driver, "//span[text()='Products']");
        cleanItems("Articles", "article");
        cleanItems("Departments", "department");
        cleanItems("Courses", "course");
        cleanItems("Groups", "group");
        cleanItems("Categories", "category");

    }
    private void cleanPosUsers() {
        cleanItems("POS Users", "user");
    }
    private void cleanSpaces() {
        cleanItems("Spaces", "space");
    }

    private void cleanPrinters() {
        cleanItems("Printers", "printer");
    }
}
