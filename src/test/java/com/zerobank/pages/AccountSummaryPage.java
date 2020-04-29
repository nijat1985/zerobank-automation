package com.zerobank.pages;

import com.zerobank.utilities.ConstantVariables;
import com.zerobank.utilities.Driver;
import org.junit.Assert;

public class AccountSummaryPage extends BasePage{

    @Override
    public void validateResults(String element,String expected){
        element = element.toUpperCase();
        switch (element){
            case ConstantVariables.TITLE:
                Assert.assertEquals(expected, Driver.getDriver().getTitle());
                break;
            default:
                Assert.fail("There is no such " + element + " in this switch statement");
        }
    }
}
