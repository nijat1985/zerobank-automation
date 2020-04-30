package com.zerobank.pages;

import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.ConstantVariables;
import com.zerobank.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AccountActivityPage extends BasePage{

    @FindBy(id = "aa_accountId")
    public WebElement drpd_account;

    public Select getAccountTypes(){
        return new Select(drpd_account);
    }

    @FindBy(xpath = "//table/thead/tr/th")
    public List<WebElement> hdr_transactionTable;

    @Override
    public void validateResults(String element,String expected){
        element = element.toUpperCase();
        switch (element){
            case ConstantVariables.TITLE:
                Assert.assertEquals(expected, Driver.getDriver().getTitle());
                break;
            case ConstantVariables.ACCOUNT_DROP_DOWN_DEFAULT_OPTION:
                Assert.assertEquals(expected,getAccountTypes().getFirstSelectedOption().getText());
                break;
            default:
                Assert.fail("There is no such " + element + " in this switch statement");
        }
    }


    @Override
    public void validateResultsList(String element, List<String> expectedList){
        element = element.toUpperCase();
        switch (element){
            case ConstantVariables.ACCOUNT_DROP_DOWN:
                Assert.assertTrue(expectedList.containsAll(BrowserUtils.getElementsText(getAccountTypes().getOptions())));
                break;
            case ConstantVariables.TRANSACTIONS:
                Assert.assertEquals(expectedList,BrowserUtils.getElementsText(hdr_transactionTable));
                break;
            default:
                Assert.fail("There is no such " + element + " in this switch statement");
        }
    }
}
