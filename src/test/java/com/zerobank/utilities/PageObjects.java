package com.zerobank.utilities;

import com.zerobank.pages.*;
import org.junit.Assert;

public class PageObjects {
    public static BasePage getPageObjects (String page){
        page = page.toUpperCase();
        switch (page){
            case ConstantVariables.LOGIN:
                return new LoginPage();
            case ConstantVariables.ACCOUNT_SUMMARY:
                return new AccountSummaryPage();
            case ConstantVariables.ACCOUNT_ACTIVITY:
                return new AccountActivityPage();
            case ConstantVariables.PAY_BILLS:
                return new PayBillsPage();
            case ConstantVariables.BASE:
                return new BasePage();
            default:
            Assert.fail("There is no " + page + " in this switch statement");
        }
        return new BasePage();
    }
}
