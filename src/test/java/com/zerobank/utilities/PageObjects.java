package com.zerobank.utilities;

import com.zerobank.pages.BasePage;
import com.zerobank.pages.LoginPage;

public class PageObjects {
    public static BasePage getPageObjects (String page){
        page = page.toUpperCase();
        switch (page){
            case ConstantVariables.LOGIN:
                return new LoginPage();
            default:
                return new BasePage();
        }

    }
}
