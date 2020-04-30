package com.zerobank.utilities;

import java.util.List;

public interface CommonMethods {
    public default void enterValue(String str1, String str2){}
    public default void clickButton(String button){}
    public default void validateResults(String element,String expected){}
    public default void validateResultsList(String element, List<String> expectedList){}
}
