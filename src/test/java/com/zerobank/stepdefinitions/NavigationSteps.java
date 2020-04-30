package com.zerobank.stepdefinitions;

import com.zerobank.pages.BasePage;
import com.zerobank.utilities.PageObjects;
import io.cucumber.java.en.When;

public class NavigationSteps {
    @When("user navigates to {string} page")
    public void user_navigates_to_page(String page) {
        BasePage basePage = new BasePage();
        basePage.navigateTo(page);
    }
}
