package com.zerobank.stepdefinitions;

import com.zerobank.pages.PayBillsPage;
import io.cucumber.java.en.When;

import java.util.Map;

public class AddNewPayee {
    @When("creates new payee using following information")
    public void creates_new_payee_using_following_information(Map<String,String> payeeInfo) {
        PayBillsPage payBillsPage = new PayBillsPage();
        payBillsPage.txt_payeeName.sendKeys(payeeInfo.get("Payee Name"));
        payBillsPage.txt_payeeAddress.sendKeys(payeeInfo.get("Payee Address"));
        payBillsPage.txt_account.sendKeys(payeeInfo.get("Account"));
        payBillsPage.txt_payeeDetails.sendKeys(payeeInfo.get("Payee details"));
        payBillsPage.btn_add.click();
    }
}
