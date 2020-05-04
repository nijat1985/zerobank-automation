package com.zerobank.stepdefinitions;

import com.zerobank.pages.AccountActivityPage;
import com.zerobank.pages.BasePage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.ConstantVariables;
import com.zerobank.utilities.PageObjects;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import static org.junit.Assert.*;

public class FindTransactionsSteps {
    AccountActivityPage accountActivityPage;

    @Then("{string} should only show transactions dates between {string} to {string} on {string} page")
    public void should_only_show_transactions_dates_between_to_on_page(String element, String startDate, String endDate, String page) {
        BasePage pageName = PageObjects.getPageObjects(page);
        pageName.validateDateResults(element, startDate, endDate);
    }

    @Then("the results should be sorted by most recent date")
    public void the_results_should_be_sorted_by_most_recent_date() {
        accountActivityPage = new AccountActivityPage();
        Assert.assertTrue(BrowserUtils.isSortedDescendingOrder(accountActivityPage.date_transactionDateList));


    }

    @Then("the results table should only not contain transactions date {string}")
    public void the_results_table_should_only_not_contain_transactions_date(String date) {
        accountActivityPage = new AccountActivityPage();
        assertFalse(BrowserUtils.isListContainsValue(accountActivityPage.date_transactionDateList,date));
    }

    @Then("results table should only show descriptions containing {string}")
    public void results_table_should_only_show_descriptions_containing(String value) {
        accountActivityPage = new AccountActivityPage();
        BrowserUtils.wait(2);
        assertTrue(BrowserUtils.isListContainsValue(accountActivityPage.desc_transactionDescriptionList,value));
    }

    @Then("results table should not show descriptions containing {string}")
    public void results_table_should_not_show_descriptions_containing(String value) {
        accountActivityPage = new AccountActivityPage();
        assertFalse(BrowserUtils.isListContainsValue(accountActivityPage.desc_transactionDescriptionList,value));
    }


    @Then("results table should show at least one result under {string}")
    public void results_table_should_show_at_least_one_result_under(String value) {
        accountActivityPage = new AccountActivityPage();
        value = value.toUpperCase();
        switch (value){
            case ConstantVariables.DEPOSIT:
                assertTrue(accountActivityPage.clmn_deposit.size()>0);
                break;
            case ConstantVariables.WITHDRAWAL:
                assertTrue(accountActivityPage.clmn_withdrawal.size()>0);
                break;

            default:
                Assert.fail("There is no such " + value + " in this switch statement");
        }
    }

    @When("user selects type {string}")
    public void user_selects_type(String value) {
        accountActivityPage = new AccountActivityPage();
        String checkValue = value.toUpperCase();
        switch (checkValue){
            case ConstantVariables.DEPOSIT:
                accountActivityPage.getTransactionType().selectByVisibleText(value);
                break;
            case ConstantVariables.WITHDRAWAL:
                accountActivityPage.getTransactionType().selectByVisibleText(value);
                break;

            default:
                Assert.fail("There is no such " + value + " in this switch statement");
        }
    }

    @Then("results table should show no result under {string}")
    public void results_table_should_show_no_result_under(String value) {
        accountActivityPage = new AccountActivityPage();
        value = value.toUpperCase();
        switch (value){
            case ConstantVariables.DEPOSIT:
                BrowserUtils.wait(2);
                assertFalse(BrowserUtils.isListEmpty(accountActivityPage.clmn_deposit));
                break;
            case ConstantVariables.WITHDRAWAL:
                BrowserUtils.wait(2);
                assertFalse(BrowserUtils.isListEmpty(accountActivityPage.clmn_withdrawal));
                break;

            default:
                Assert.fail("There is no such " + value + " in this switch statement");
        }
    }
}
