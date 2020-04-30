package com.zerobank.stepdefinitions;

import com.zerobank.pages.BasePage;
import com.zerobank.utilities.PageObjects;
import io.cucumber.java.en.Then;

import java.util.List;

public class AccountSummarySteps {
    @Then("{string} should contain the following information on {string} page")
    public void should_contain_the_following_information_on_page(String element, String page, List<String> accountTypes) {
        BasePage pageName = PageObjects.getPageObjects(page);
        pageName.validateResultsList(element,accountTypes);
    }

    @Then("{string} table must have following columns on {string} page")
    public void table_must_have_following_columns_on_page(String element, String page, List<String> tableColumns) {
        BasePage pageName = PageObjects.getPageObjects(page);
        pageName.validateResultsList(element,tableColumns);
    }
}
