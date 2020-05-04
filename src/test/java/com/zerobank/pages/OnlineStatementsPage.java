package com.zerobank.pages;

import com.zerobank.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class OnlineStatementsPage extends BasePage{

    @FindBy(xpath = "//ul[@class='nav nav-pills']/li")
    public List<WebElement> listRecentStatements;


    public List<WebElement> getListOfStatements(int year){
        String xpath = "//div[@id='os_" + year + "']/table/tbody/tr/td[1]/a";
        List<WebElement> listEl = Driver.getDriver().findElements(By.xpath(xpath));
        return listEl;
    }


}
