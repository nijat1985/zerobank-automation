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

    @FindBy(xpath = "//button[@class='btn btn-primary']")
    public WebElement btn_find;

    @FindBy(id = "aa_fromDate")
    public WebElement date_startDate;

    @FindBy(id = "aa_toDate")
    public WebElement date_endDate;

    @FindBy(xpath = "//div[@id='filtered_transactions_for_account']/table/tbody/tr/td[1]")
    public List<WebElement> date_transactionDateList;

    @FindBy(xpath = "//div[@id='filtered_transactions_for_account']/table/tbody/tr/td[2]")
    public List<WebElement> desc_transactionDescriptionList;

    @FindBy(id = "aa_description")
    public WebElement txt_description;

    @FindBy(xpath = "//div[@id='filtered_transactions_for_account']/table/tbody/tr/td[3]")
    public List<WebElement> clmn_deposit;

    @FindBy(xpath = "//div[@id='filtered_transactions_for_account']/table/tbody/tr/td[4]")
    public List<WebElement> clmn_withdrawal;

    @FindBy(id = "aa_type")
    public WebElement drpd_transactionType;

    @FindBy(linkText = "Find Transactions")
    public WebElement tab_findTransactions;

    public Select getTransactionType(){
        return new Select(drpd_transactionType);
    }


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

    @Override
    public void validateDateResults(String element, String startDate, String endDate){
        element = element.toUpperCase();
        switch (element){
            case ConstantVariables.TRANSACTIONS_TABLE:
            Assert.assertTrue(BrowserUtils.isDatesInBetweenStartAndEndDate(startDate,endDate,BrowserUtils.getElementsText(date_transactionDateList)));
            break;
            default:
                Assert.fail("There is no such " + element + " in this if statement");
        }
    }

    @Override
    public void validateAccountDropdown(String expected){
        String value = expected.toUpperCase();
        switch (value){
            case ConstantVariables.SAVINGS:
                Assert.assertEquals(expected,getAccountTypes().getFirstSelectedOption().getText());
                break;
            case ConstantVariables.BROKERAGE:
                Assert.assertEquals(expected,getAccountTypes().getFirstSelectedOption().getText());
                break;
            case ConstantVariables.CHECKING:
                Assert.assertEquals(expected,getAccountTypes().getFirstSelectedOption().getText());
                break;
            case ConstantVariables.CREDIT_CARD:
                Assert.assertEquals(expected,getAccountTypes().getFirstSelectedOption().getText());
                break;
            case ConstantVariables.LOAN:
                Assert.assertEquals(expected,getAccountTypes().getFirstSelectedOption().getText());
                break;
            default:
                Assert.fail("There is no such " + expected + " in this switch statement");
        }
    }

    @Override
    public void clickButton(String button){
        button = button.toUpperCase();
        switch (button){
            case ConstantVariables.FIND:
                btn_find.click();
                date_startDate.clear();
                date_endDate.clear();
                txt_description.clear();
                break;
            default:
                Assert.fail("There is no a " + button + " in this switch statement");
        }
    }

    @Override
    public void enterValue(String field, String value){
        field = field.toUpperCase();
        switch (field){
            case ConstantVariables.START_DATE:
                date_startDate.sendKeys(value);
                break;
            case ConstantVariables.END_DATE:
                date_endDate.sendKeys(value);
                break;
            case ConstantVariables.DESCRIPTION:
                BrowserUtils.wait(2);
                txt_description.sendKeys(value);
                break;
            default:
                Assert.fail("There is no such a " + field + " in this switch statement");
        }
    }

    @Override
    public void navigateToTab(String tabName){
        tabName = tabName.toUpperCase();
        switch (tabName){
            case ConstantVariables.FIND_TRANSACTIONS:
                tab_findTransactions.click();
                break;
            default:
                Assert.fail("There is no such " + tabName + " in this switch statement");
        }
    }
}
