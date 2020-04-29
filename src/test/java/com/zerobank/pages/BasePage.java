package com.zerobank.pages;

import com.zerobank.utilities.CommonMethods;
import com.zerobank.utilities.Driver;
import org.openqa.selenium.support.PageFactory;

public class BasePage implements CommonMethods {
    public BasePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }
}
