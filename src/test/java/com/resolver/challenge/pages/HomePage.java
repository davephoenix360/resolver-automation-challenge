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

    public WebElement testDiv(String id) {
        return waits.visible(By.id(id));
    }
}
