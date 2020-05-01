package com.zerobank.pages;

import com.zerobank.utilities.ConstantVariables;
import com.zerobank.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

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

    @FindBy(xpath = "")
    public WebElement msg_emptyFieldMessage;


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
            case ConstantVariables.EMPTY_FIELD_MESSAGE:
                Assert.assertEquals(expected,msg_emptyFieldMessage.getText());
                break;
            case ConstantVariables.AFTER_PAYMENT_MESSAGE:
                Assert.assertFalse(expected.equals(msg_successMessage.getText()));
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

}
