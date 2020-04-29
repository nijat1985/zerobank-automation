package com.zerobank.stepdefinitions;

import com.zerobank.pages.BasePage;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import com.zerobank.utilities.PageObjects;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class NavigationSteps {
    @Given("user is on {string} page")
    public void user_is_on_page(String page) {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
    }

    @When("user clicks on {string} button on {string} page")
    public void user_clicks_on_button_on_page(String button, String page) {
        BasePage pageName = PageObjects.getPageObjects(page);
        pageName.clickButton(button);
    }
}
