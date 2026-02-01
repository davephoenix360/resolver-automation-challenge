package com.resolver.challenge.pages;

import com.resolver.challenge.support.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

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
        // TODO: Add a navigation to home page here since our tests have that as prefix
    }

    public WebElement getTestDiv(int testNumber) {
        return waits.visible(By.id("test-" + testNumber + "-div"));
    }

    // A helper because we need the text without the child text for Test2
    public static String ownText(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript(
                "return Array.from(arguments[0].childNodes)" +
                        ".filter(n => n.nodeType === Node.TEXT_NODE)" +
                        ".map(n => n.textContent)" +
                        ".join(' ')" +
                        ".replace(/\\s+/g,' ')" +
                        ".trim();",
                element
        );
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

    // Test 2 Getters and ops
    public WebElement test2ListGroup() {
        return getTestDiv(2).findElement(By.cssSelector("ul.list-group"));
    }

    public List<WebElement> test2ListGroupChildren() {
        return test2ListGroup().findElements(By.cssSelector("li.list-group-item"));
    }

    // Test 3 Getters and ops
    public WebElement test3DropdownDiv() {
        return getTestDiv(3).findElement(By.className("dropdown"));
    }

    public WebElement test3DropdownBtn() {
        return getTestDiv(3).findElement(By.id("dropdownMenuButton"));
    }

    public WebElement test3DropdownMenu() {
        return getTestDiv(3).findElement(By.className("dropdown-menu"));
    }

    // Test 5 Getters
    public WebElement test5DelayedBtn() {
        return waits.visible(By.cssSelector("button[id='test5-button']"));
    }

    public WebElement test5SuccessAlert() {
        return waits.visible(By.cssSelector("div[id='test5-alert']"));
    }
}
