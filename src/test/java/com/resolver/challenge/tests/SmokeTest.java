package com.resolver.challenge.tests;

import com.resolver.challenge.pages.HomePage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SmokeTest extends BaseTest {
    @Test
    void opens_homepage() {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        assertNotNull(driver.getTitle());
//        System.out.println(driver.getTitle());
    }
}
