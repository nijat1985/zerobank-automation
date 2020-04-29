package com.zerobank.pages;

import com.zerobank.utilities.ConstantVariables;
import com.zerobank.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(xpath = "//input[@id='user_login']")
    public WebElement txt_login;

    @FindBy(xpath = "//input[@id='user_password']")
    public WebElement txt_password;

    @FindBy(xpath = "//input[@name='submit']")
    public WebElement btn_login;

    @FindBy(xpath = "//form[@id='login_form']/div[@class='alert alert-error']")
    public WebElement msg_error;


    @Override
    public void enterValue(String field, String value){
        field = field.toUpperCase();
        switch (field){
            case ConstantVariables.USERNAME:
                txt_login.sendKeys(value);
                break;
            case ConstantVariables.PASSWORD:
                txt_password.sendKeys(value);
                break;
            default:
                Assert.fail("There is no such a " + field + " in this switch statement");
        }
    }

    @Override
    public void clickButton(String button){
        button = button.toUpperCase();
        switch (button){
            case ConstantVariables.LOGIN:
                btn_login.click();
                break;
            default:
                Assert.fail("There is no a " + button + " in this switch statement");
        }
    }


    @Override
    public void validateResults(String element,String expected){
        element = element.toUpperCase();
        switch (element){
            case ConstantVariables.ERROR_MESSAGE:
                Assert.assertEquals(expected, msg_error.getText());
                Assert.assertTrue(msg_error.isDisplayed());
                break;
            default:
                Assert.fail("There is no such " + element + " in this switch statement");
        }
    }

}
