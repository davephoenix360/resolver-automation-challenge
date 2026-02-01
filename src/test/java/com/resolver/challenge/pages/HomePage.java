package com.resolver.challenge.pages;

import com.resolver.challenge.support.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class HomePage {
    private final WebDriver driver;
    private final Waits waits;

    public static final String DEFAULT_URL = "http://localhost:8000/index.html";

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get(DEFAULT_URL);
    }

    public WebElement getTestDiv(int testNumber) {
        return waits.visible(By.id("test-" + testNumber + "-div"));
    }

    // Test 1 Getters and ops

    public WebElement test1Email() {
        return getTestDiv(1).findElement(By.id("inputEmail"));
    }

    public WebElement test1Password() {
        return getTestDiv(1).findElement(By.id("inputPassword"));
    }

    public WebElement test1SignIn() {
        return getTestDiv(1).findElement(By.cssSelector("button[type='submit']"));
    }

    public void test1EnterLoginDetails(String email, String password) {
        test1Email().sendKeys(email);
        test1Password().sendKeys(password);
    }


}
