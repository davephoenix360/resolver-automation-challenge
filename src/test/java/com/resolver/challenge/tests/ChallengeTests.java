package com.resolver.challenge.tests;

import com.resolver.challenge.pages.HomePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Optional;

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

    @Test
    void test3_dropdownValues() {
        WebElement dropdownBtn = homePage.test3DropdownBtn();
        assertTrue(dropdownBtn.isDisplayed(), "Dropdown button should be visible");

        assertEquals("Option 1", dropdownBtn.getText(), "Dropdown value should be visible");

        dropdownBtn.click();
        WebElement dropdownMenu = homePage.test3DropdownMenu();

        // Select the Option 3
        Optional<WebElement> option3Opt = dropdownMenu.findElements(By.className("dropdown-item")).stream()
                .filter(webElement -> webElement.getText().equals("Option 3")).findFirst();

        assertTrue(option3Opt.isPresent(), "Dropdown menu should be visible");

        WebElement option3 = option3Opt.get();
        option3.click();

        assertEquals("Option 3", dropdownBtn.getText(), "Dropdown value should be visible");

    }

    @Test
    void test4_buttons() {
        WebElement test4Div = homePage.getTestDiv(4);
        assertTrue(test4Div.isDisplayed(), "Test 4 button should be visible");

        List<WebElement> test4Buttons = test4Div.findElements(By.className("btn"));

        assertTrue(test4Buttons.get(0).isEnabled(), "First button should be enabled");
        assertFalse(test4Buttons.get(1).isEnabled(), "Second button should be disabled");
    }

    @Test
    void test5_delayedBtn() {
        WebElement test5Btn = homePage.test5DelayedBtn();
        assertTrue(test5Btn.isDisplayed(), "Test 5 button should be visible");

        test5Btn.click();

        WebElement test5Success = homePage.test5SuccessAlert();
        assertTrue(test5Success.isDisplayed(), "Test 5 success alert should be visible");
        assertFalse(test5Btn.isEnabled(),  "Test 5 button should be disabled");

    }

    @Test
    void test6_tableValues() {
        WebElement test6Cell = homePage.test6GetCell(2, 2);
        assertEquals("Ventosanzap", test6Cell.getText(), "Cell value should be correct");
    }
}
