package com.zerobank.stepdefinitions;

import com.zerobank.pages.BasePage;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.PageObjects;
import io.cucumber.java.en.When;

public class InputSteps {
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
}
