package com.zerobank.stepdefinitions;

import com.zerobank.pages.BasePage;
import com.zerobank.utilities.PageObjects;
import io.cucumber.java.en.Then;

public class AccountActivityNavigationSteps {
    @Then("Account drop down should have {string} selected on {string} page")
    public void account_drop_down_should_have_selected_on_page(String expected, String page) {
        BasePage pageName = PageObjects.getPageObjects(page);
        pageName.validateAccountDropdown(expected);
    }
}
