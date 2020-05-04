package com.zerobank.stepdefinitions;

import com.zerobank.pages.PayBillsPage;
import com.zerobank.utilities.ConstantVariables;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;

import java.util.Random;

public class PurchaseForeignCurrencySteps {
    PayBillsPage payBillsPage;
    Alert alert;

    @When("user tries to calculate cost without selecting/entering a {string}")
    public void user_tries_to_calculate_cost_without_selecting_a(String value) {
        String checkValue = value.toUpperCase();
        payBillsPage = new PayBillsPage();

        switch (checkValue){
            case ConstantVariables.CURRENCY:
                payBillsPage.getCurrency().selectByIndex(new Random().nextInt(payBillsPage.getCurrency().getOptions().size()));
                break;

            case ConstantVariables.VALUE:
                payBillsPage.txt_purchaseForeignCurrencyAmount.sendKeys(new Random().nextDouble()+"");
                break;

            default:
                Assert.fail("There is no such " + value + " in this switch statement");
        }
    }

    @Then("error message {string} should be displayed")
    public void error_message_should_be_displayed(String expectedMessage) {
        try {
            alert = Driver.getDriver().switchTo().alert();
            Assert.assertEquals(expectedMessage, alert.getText());
            alert.accept();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
