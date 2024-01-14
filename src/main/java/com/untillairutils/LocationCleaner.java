package com.untillairutils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

        WebDriverManager.chromedriver().setup();
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

        openProducts();
        cleanArticles();
        cleanDepartments();
        cleanCourses();
        cleanFoodGroups();
        cleanCategories();
        cleanPosUsers();
        cleanPrinters();
        cleanSpaces();
    }

    public void openProducts() {
        Helpers.clickByXpath(driver, "//span[text()='Products']");
    }

    public void cleanArticles() {
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
            sleep();
            menu = driver.findElements(By.xpath("//div[@class='antd-table-row-actions']"));
        }

    }

    public void cleanDepartments() {
        Helpers.clickByXpath(driver, "//span[text()='Departments']");
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
            sleep();
            menu = driver.findElements(By.xpath("//div[@class='antd-table-row-actions']"));
        }
    }

    public void cleanCourses() {
        Helpers.clickByXpath(driver, "//span[text()='Courses']");
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
            sleep();
            menu = driver.findElements(By.xpath("//div[@class='antd-table-row-actions']"));
        }
    }

    public void cleanFoodGroups() {
        Helpers.clickByXpath(driver, "//span[text()='Groups']");
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
            sleep();
            menu = driver.findElements(By.xpath("//div[@class='antd-table-row-actions']"));
        }
    }

    public void cleanCategories() {
        Helpers.clickByXpath(driver, "//span[text()='Categories']");
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
            sleep();
            menu = driver.findElements(By.xpath("//div[@class='antd-table-row-actions']"));
        }
    }

    private void sleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
    }

    private void cleanPosUsers() {
        Helpers.scrollAndClickByXpath(driver, "//span[text()='POS Users']");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        ExpectedCondition<WebElement> c1 = ExpectedConditions
                .elementToBeClickable(By.xpath("//span[text()='Add new user']"));
        ExpectedCondition<WebElement> c2 = ExpectedConditions
                .elementToBeClickable(By.xpath("//span[text()='Add your first user']"));
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

    private void cleanPrinters() {
        Helpers.scrollAndClickByXpath(driver, "//span[text()='General']");
        Helpers.clickByXpath(driver, "//span[text()='Equipment']");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        ExpectedCondition<WebElement> c1 = ExpectedConditions
                .elementToBeClickable(By.xpath("//span[text()='Add new equipment']"));
        ExpectedCondition<WebElement> c2 = ExpectedConditions
                .elementToBeClickable(By.xpath("//span[text()='Add your first equipment']"));
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

    private void cleanSpaces() {
        Helpers.clickByXpath(driver, "//span[text()='Spaces']");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        ExpectedCondition<WebElement> c1 = ExpectedConditions
                .elementToBeClickable(By.xpath("//span[text()='Add new space']"));
        ExpectedCondition<WebElement> c2 = ExpectedConditions
                .elementToBeClickable(By.xpath("//span[text()='Add your first space']"));
        ExpectedCondition<WebElement> c3 = ExpectedConditions
                .elementToBeClickable(By.xpath("//div[@class='style_header__OgYnX']"));
        wait.until(ExpectedConditions.or(c1, c2, c3));
        String spaceXP = "//i[@class='air-bo-2-trash-can']";
        List<WebElement> menu = driver.findElements(By.xpath(spaceXP));
        while (menu.size() != 0) {
            Actions build = new Actions(driver);
            build.moveToElement(menu.get(0)).build().perform();
            Helpers.waitVisibleByXpath(driver, spaceXP);
            Helpers.clickByXpath(driver, spaceXP);
            Helpers.waitVisibleByXpath(driver, "//div[text()='Are you sure?']");
            Helpers.clickByXpath(driver, "//span[text()='Yes']");

            menu = driver.findElements(By.xpath(spaceXP));
        }
    }
}
