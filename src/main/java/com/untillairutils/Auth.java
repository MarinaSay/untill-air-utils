package com.untillairutils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Auth {

	public static void login (WebDriver driver, String login, String password) {
		List<WebElement> logout = driver.findElements(By.xpath("//span[text()='Logout']"));
		if (logout.size() > 0) {
			logout.get(0).click();
		}

		Helpers.waitId(driver,"normal_login_login");
		Helpers.inputById(driver,"normal_login_login", login);
		Helpers.inputById(driver,"normal_login_password", password);

		Helpers.clickByXpath(driver,"//button[@type='submit']");
	}
}
