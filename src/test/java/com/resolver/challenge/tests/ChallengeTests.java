package com.resolver.challenge.tests;

import com.resolver.challenge.pages.HomePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

public class ChallengeTests extends BaseTest {

    private HomePage homePage;

    @BeforeEach
    void initPage() {
        homePage = new HomePage(driver);
        homePage.open(); // open here so every test starts on the page
    }

    @Test
    void test1_elementsPresent_andEnterCredentials() {
        WebElement email = homePage.test1Email();
        WebElement password = homePage.test1Password();
        WebElement signIn = homePage.test1SignIn();

        assertTrue(email.isDisplayed(), "Email input should be visible");
        assertTrue(password.isDisplayed(), "Password input should be visible");
        assertTrue(signIn.isDisplayed(), "Sign in button should be visible");

        homePage.test1EnterLoginDetails("test@example.com", "password123");
        assertEquals("dcd@example.com", email.getAttribute("value"));
        assertEquals("dcd_123", password.getAttribute("value"));
    }
}
