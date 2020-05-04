package com.zerobank.pages;

import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.ConstantVariables;
import com.zerobank.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class PayBillsPage extends BasePage{

    @FindBy(id = "sp_payee")
    public WebElement drpd_payee;

    public Select getPayee(){
        return new Select(drpd_payee);
    }

    @FindBy(id = "sp_account")
    public WebElement drpd_account;

    public Select getAccount(){
        return new Select(drpd_account);
    }

    @FindBy(id = "sp_amount")
    public WebElement txt_amount;

    @FindBy(id = "sp_date")
    public WebElement txt_date;

    @FindBy(id = "pay_saved_payees")
    public WebElement btn_pay;

    @FindBy(xpath = "//div[@id='alert_content']/span")
    public WebElement msg_successMessage;

    @FindBy(linkText = "Add New Payee")
    public WebElement tab_addNewPayee;

    @FindBy(linkText = "Purchase Foreign Currency")
    public WebElement tab_purchaseForeignCurrency;

    @FindBy(xpath = "//div[@id='alert_content']")
    public WebElement msg_addNewPayee;

    @FindBy(id = "np_new_payee_name")
    public WebElement txt_payeeName;

    @FindBy(id = "np_new_payee_address")
    public WebElement txt_payeeAddress;

    @FindBy(id = "np_new_payee_account")
    public WebElement txt_account;

    @FindBy(id = "np_new_payee_details")
    public WebElement txt_payeeDetails;

    @FindBy(id = "add_new_payee")
    public WebElement btn_add;

    @FindBy(id = "pc_currency")
    public WebElement drpd_currency;

    @FindBy(id = "pc_amount")
    public WebElement txt_purchaseForeignCurrencyAmount;

    public Select getCurrency(){
        return new Select(drpd_currency);
    }


    @Override
    public void validateResults(String element,String expected){
        element = element.toUpperCase();
        switch (element){
            case ConstantVariables.TITLE:
                Assert.assertEquals(expected, Driver.getDriver().getTitle());
                break;
            case ConstantVariables.MESSAGE:
                Assert.assertEquals(expected,msg_successMessage.getText());
                break;
            case ConstantVariables.DATE_EMPTY_FIELD_MESSAGE:
                Assert.assertEquals(expected,txt_date.getAttribute("validationMessage"));
                break;
            case ConstantVariables.AMOUNT_EMPTY_FIELD_MESSAGE:
                Assert.assertEquals(expected,txt_amount.getAttribute("validationMessage"));
                break;
            case ConstantVariables.AFTER_PAYMENT_MESSAGE:
                Assert.assertFalse(expected.equals(msg_successMessage.getText()));
                break;
            case ConstantVariables.ADD_NEW_PAYEE_MESSAGE:
                Assert.assertEquals(expected,msg_addNewPayee.getText());
                break;
            default:
                Assert.fail("There is no such " + element + " in this switch statement");
        }
    }

    @Override
    public void validateResultsList(String element, List<String> expectedList){
        element = element.toUpperCase();
        switch (element){
            case ConstantVariables.CURRENCY_DROP_DOWN:
                Assert.assertTrue(BrowserUtils.isListContainsList(expectedList,getCurrency().getOptions()));
                break;
            default:
                Assert.fail("There is no such " + element + " in this switch statement");
        }
    }

    @Override
    public void enterValue(String field, String value){
        field = field.toUpperCase();
        switch (field){
            case ConstantVariables.PAYEE:
                getPayee().selectByVisibleText(value);
                break;
            case ConstantVariables.ACCOUNT:
                getAccount().selectByVisibleText(value);
                break;
            case ConstantVariables.AMOUNT:
                txt_amount.sendKeys(value);
                break;
            case ConstantVariables.DATE:
                txt_date.sendKeys(value);
                break;
            default:
                Assert.fail("There is no such a " + field + " in this switch statement");
        }
    }

    @Override
    public void clickButton(String button){
        button = button.toUpperCase();
        switch (button){
            case ConstantVariables.PAY:
                btn_pay.click();
                break;
            default:
                Assert.fail("There is no a " + button + " in this switch statement");
        }
    }

    @Override
    public void navigateToTab(String tabName){
        tabName = tabName.toUpperCase();
        switch (tabName){
            case ConstantVariables.ADD_NEW_PAYEE:
                tab_addNewPayee.click();
                break;
            case ConstantVariables.PURCHASE_FOREIGN_CURRENCY:
                tab_purchaseForeignCurrency.click();
                break;
            default:
                Assert.fail("There is no such " + tabName + " in this switch statement");
        }
    }

}
