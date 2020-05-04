package com.zerobank.pages;

import com.zerobank.utilities.CommonMethods;
import com.zerobank.utilities.ConstantVariables;
import com.zerobank.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage implements CommonMethods {
    public BasePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//div[@class='span12']/div/ul/li/a[contains(text(),'Account Activity')]")
    public WebElement tab_accountActivity;

    @FindBy(xpath = "//div[@class='span12']/div/ul/li/a[contains(text(),'Pay Bills')]")
    public WebElement tab_payBills;

    @FindBy(xpath = "//div[@class='span12']/div/ul/li/a[contains(text(),'Online Statements')]")
    public WebElement tab_onlineStatements;




    public void navigateTo(String page){
        page = page.toUpperCase();
        switch (page){
            case ConstantVariables.ACCOUNT_ACTIVITY:
                tab_accountActivity.click();
                break;
            case ConstantVariables.PAY_BILLS:
                tab_payBills.click();
                break;
            case ConstantVariables.ONLINE_STATEMENTS:
                tab_onlineStatements.click();
                break;
            default:
                Assert.fail("There is no such " + page + " in this switch statement");
        }
    }

}
