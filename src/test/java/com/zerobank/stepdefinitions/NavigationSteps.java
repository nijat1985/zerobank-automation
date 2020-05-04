package com.zerobank.stepdefinitions;

import com.zerobank.pages.BasePage;
import com.zerobank.utilities.ConstantVariables;
import com.zerobank.utilities.Driver;
import com.zerobank.utilities.PageObjects;
import io.cucumber.java.en.When;

public class NavigationSteps {
    @When("user navigates/accesses to {string} {string}")
    public void user_navigates_to_page(String page_or_tab_name,String page_or_tab) {
        String checkIfPageOrTab = page_or_tab.toUpperCase();
        switch (checkIfPageOrTab){
            case ConstantVariables.PAGE:
                BasePage basePage = new BasePage();
                basePage.navigateTo(page_or_tab_name);
                break;
            case ConstantVariables.TAB:
                BasePage pageName = PageObjects.getPageObjects(Driver.getDriver().getTitle().split("-")[1].trim());
                pageName.navigateToTab(page_or_tab_name);
        }

    }
}
