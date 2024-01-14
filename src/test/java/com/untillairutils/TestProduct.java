package com.untillairutils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.untillairutils.Helpers.formatDbl;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

class TestProduct extends TestBase{

    @BeforeEach
    void setUp() {
        loginAtLocation();
        setEnglish();
        closeWelcomePopUp();
    }
    @AfterEach
    void tearDown() {

        //logout();
    }


    void cleanupProduct() {
        LocationCleaner cleaner = new LocationCleaner(driver,
                getRequiredEnv(ENV_LOGIN), getRequiredEnv(ENV_PASSWORD),
                getRequiredEnv(ENV_LOCATION), getRequiredEnv(ENV_URL));
        cleaner.openProducts();
        cleaner.cleanArticles();
        cleaner.cleanDepartments();
        cleaner.cleanFoodGroups();
        cleaner.cleanCourses();
        cleaner.cleanCategories();
    }

    private void sleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
    }
    @Test
    void testCreateProduct() {
        cleanupProduct();
        Helpers.clickByXpath(driver, "//span[text()='Categories']");
        Helpers.clickByXpath(driver, "//span[text()='Add new category']");
        Helpers.inputById(driver, "names_0", "Food");
        confirmContinue();
        sleep();
        assertFalse(driver.findElements(By.xpath("//span[text()='Food']")).isEmpty());
        Helpers.clickByXpath(driver, "//span[text()='Groups']");
        Helpers.waitVisibleByXpath(driver, "//span[text()='Add new group']");
        Helpers.clickByXpath(driver, "//span[text()='Add new group']");
        Helpers.clickByXpath(driver, "//input[@id='name']");
        Helpers.inputById(driver, "name", "Hot");
        driver.findElement(By.id("id_category")).click();
        String foodXp = String.format("//div[@class='ant-select-item-option-content' and text()='%s']", "Food");
        Helpers.clickByXpath(driver, foodXp);
        driver.findElement(By.id("id_vat")).click();
        String vatXp = String.format("//div[@class='ant-select-item-option-content' and text()='%s']", "Standard");
        Helpers.clickByXpath(driver, vatXp);
        confirmContinue();
        assertFalse(driver.findElements(By.xpath("//span[text()='Hot']")).isEmpty());
        Helpers.clickByXpath(driver, "//span[text()='Departments']");
        Helpers.waitVisibleByXpath(driver, "//span[text()='Add new department']");
        Helpers.clickByXpath(driver, "//span[text()='Add new department']");
        Helpers.clickByXpath(driver, "//input[@id='name']");
        Helpers.inputById(driver, "name", "Pizza");
        driver.findElement(By.id("id_food_group")).click();
        String group = String.format("//div[@class='ant-select-item-option-content' and text()='%s']", "Hot");
        Helpers.clickByXpath(driver, group);
        String saveXp = "//span[text()='Save']";
        Helpers.clickByXpath(driver, saveXp);
        Helpers.waitInvisibleByXpath(driver, saveXp);
        assertFalse(driver.findElements(By.xpath("//span[text()='Pizza']")).isEmpty());
        Helpers.clickByXpath(driver, "//span[text()='Articles']");
        Helpers.waitVisibleByXpath(driver, "//span[text()='Add new article']");
        Helpers.clickByXpath(driver, "//span[text()='Add new article']");
        Helpers.clickByXpath(driver, "//input[@id='name']");
        Helpers.inputById(driver, "name", "Margarita");
        String inputPrice = "//input[contains(@class, 'ant-input-number-input')]";
        Helpers.inputByXpath(driver, inputPrice, formatDbl(10));
        Helpers.selectDropDownItemById(driver, "id_departament", "Pizza");
        Helpers.selectDropDownItemById(driver, "id_courses", "No course");
        Helpers.clickByXpath(driver, saveXp);
        Helpers.waitInvisibleByXpath(driver, saveXp);
        assertFalse(driver.findElements(By.xpath("//span[text()='Margarita']")).isEmpty());
    }

    @Test
    @Disabled
    void testDeleteProduct() {
        Helpers.clickByXpath(driver, "//span[text()='Articles']");
        deleteFirstItem();
        assertTrue(driver.findElements(By.xpath("//span[text()='Margarita']")).isEmpty());
        Helpers.clickByXpath(driver, "//span[text()='Departments']");
        deleteFirstItem();
        assertTrue(driver.findElements(By.xpath("//span[text()='Pizza']")).isEmpty());
        Helpers.clickByXpath(driver, "//span[text()='Groups']");
        deleteFirstItem();
        assertTrue(driver.findElements(By.xpath("//span[text()='Hot']")).isEmpty());
        Helpers.clickByXpath(driver, "//span[text()='Categories']");
        deleteFirstItem();
        assertTrue(driver.findElements(By.xpath("//span[text()='Food']")).isEmpty());
        Helpers.waitInvisibleByXpath(driver, "//div[contains(text(),'is deleted')]");
    }

}