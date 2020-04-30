package com.zerobank.stepdefinitions;

import com.zerobank.pages.AccountSummaryPage;
import com.zerobank.pages.BasePage;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import com.zerobank.utilities.PageObjects;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginFunctionalitySteps {
    @Then("{string} should contain {string} on {string} page")
    public void should_contain_on_page(String element, String expected, String page) {
        BasePage pageName = PageObjects.getPageObjects(page);
        pageName.validateResults(element, expected);
    }

    @When("user enters {string} as {string} on {string} page")
    public void user_enters_as_on_page(String field, String value, String page) {
        BasePage pageName = PageObjects.getPageObjects(page);

        if (value.equalsIgnoreCase("user_username")){
            value = ConfigurationReader.getProperty("user_username");
        }else if (value.equalsIgnoreCase("user_password")){
            value = ConfigurationReader.getProperty("user_password");
        }

        pageName.enterValue(field,value);
    }

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
