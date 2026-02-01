package com.resolver.challenge.support;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {
    // Using the factor method for the WebDriver

    public static WebDriver newChromeDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1400,900");
        options.addArguments("--disable-gpu");

        return new ChromeDriver(options);
    }
}
