package com.resolver.challenge.tests;

import com.resolver.challenge.pages.HomePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChallengeTests extends BaseTest {

    private HomePage homePage;

    @BeforeEach
    void initPage() {
        homePage = new HomePage(driver);
        homePage.open();
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
        assertEquals("test@example.com", email.getAttribute("value"));
        assertEquals("password123", password.getAttribute("value"));
    }

    @Test
    void test2_listGroupValues() {
        WebElement listGroup = homePage.test2ListGroup();
        assertTrue(listGroup.isDisplayed(), "List group should be visible");

        List<WebElement> listGroupChildren = homePage.test2ListGroupChildren();
        assertEquals(3, listGroupChildren.size(), "List group should be visible");

        WebElement secondListItem = listGroupChildren.get(1);
        String secondListItemValue = HomePage.ownText(driver, secondListItem);
        assertEquals("List Item 2", secondListItemValue, "List Item 2 should be visible and correct");

        WebElement secondListItemBadge = secondListItem.findElement(By.className("badge"));
        assertEquals("6", secondListItemBadge.getText(), "List Item 2 should be visible and correct");
    }
}
