package com.zerobank.utilities;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.*;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class BrowserUtils {
    /**
     * Pause test for some time
     *
     * @param seconds
     */
    public static void wait(int seconds) {
        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //TODO given a list of elements, extract the text of the elements into new list of strings
    /**
     * takes a list of web elements
     * returns a list of Strings
     */

    public static List<String> getElementsText(List<WebElement> elements) {
        List<String> textValues = new ArrayList<>();
        for (WebElement element : elements) {
            if (!element.getText().isEmpty()) {
                textValues.add(element.getText().trim());
            }
        }
        return textValues;
    }

    public static List<String> getElementsText(By locator) {

        List<WebElement> elems = Driver.getDriver().findElements(locator);
        List<String> elemTexts = new ArrayList<>();

        for (WebElement el : elems) {
            elemTexts.add(el.getText());
        }
        return elemTexts;
    }


    /*
     * switches to new window by the exact title
     * returns to original window if windows with given title not found
     */
    public static void switchToWindow(String targetTitle) {
        String origin = Driver.getDriver().getWindowHandle();
        for (String handle : Driver.getDriver().getWindowHandles()) {
            Driver.getDriver().switchTo().window(handle);
            if (Driver.getDriver().getTitle().equals(targetTitle)) {
                return;
            }
        }
        Driver.getDriver().switchTo().window(origin);
    }

    public static void hover(WebElement element) {
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).perform();
    }

    /**
     * write a utility that takes a String url
     * changes to tab with given url,
     * if such url is not found, go back to original window
     * @param driver
     * @param url
     */

    public static void getNewWindowByUrl(WebDriver driver, String url){
        Set<String> windowHandle = driver.getWindowHandles();
        for (String window : windowHandle) {
            driver.switchTo().window(window);
            if (driver.getCurrentUrl().equals(url)){
                break;
            }
            driver.switchTo().defaultContent();
        }
    }

    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeToWaitInSec);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForVisibility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickability(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitForClickability(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }



    /**
     * waits for backgrounds processes on the browser to complete
     *
     * @param timeOutInSeconds
     */
    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            System.out.println("Waiting for page to load...");
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeOutInSeconds);
            wait.until(expectation);
        } catch (Throwable error) {
            System.out.println(
                    "Timeout waiting for Page Load Request to complete after " + timeOutInSeconds + " seconds");
        }
    }

    public static WebElement fluentWait(final WebElement webElement, int timeinsec) {
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(Driver.getDriver())
                .withTimeout(Duration.ofSeconds(timeinsec))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return webElement;
            }
        });
        return element;
    }

    /**
     * Clicks on an element using JavaScript
     *
     * @param element
     */
    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", element);
    }



    /**
     * Scroll to element using JavaScript
     *
     * @param element
     */
    public static void scrollTo(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }


    /*
     * takes screenshot
     * @param name
     * take a name of a test and returns a path to screenshot takes
     */
    public static String getScreenshot(String name) throws IOException {
        // name the screenshot with the current date time to avoid duplicate name
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        // TakesScreenshot ---> interface from selenium which takes screenshots
        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        // full path to the screenshot location
        String target = System.getProperty("user.dir") + "/test-output/Screenshots/" + name + date + ".png";
        File finalDestination = new File(target);
        // save the screenshot to the path given
        FileUtils.copyFile(source, finalDestination);
        return target;
    }

    /**
     * Method name: checkMonth
     * returns: int
     * Parameters: 2 String
     */

    public static int checkMonth(String startDate, String endDate){
        int difference = 0;
        //2020-04-21
        String startMonth = startDate.split("-")[1];
        String endMonth = endDate.split("-")[1];
        difference = Integer.parseInt(endMonth) - Integer.parseInt(startMonth);
        return difference;
    }

    public static boolean isDatesInBetweenStartAndEndDate(String startDate, String endDate, List<String> dateList){
        List<LocalDate> localDateList = convertListToDate(dateList);
        int startYear = Integer.parseInt(startDate.split("-")[0]);
        int startMonth = Integer.parseInt(startDate.split("-")[1]);
        int startDay = Integer.parseInt(startDate.split("-")[2]);

        int endYear = Integer.parseInt(endDate.split("-")[0]);
        int endMonth = Integer.parseInt(endDate.split("-")[1]);
        int endDay = Integer.parseInt(endDate.split("-")[2]);

        LocalDate startDateLocal = LocalDate.of(startYear,startMonth,startDay);
        LocalDate endDateLocal = LocalDate.of(endYear,endMonth,endDay);

        boolean checkDates = true;

        for (int i = 0; i < dateList.size(); i++) {
            LocalDate dateFromList = localDateList.get(i);
            boolean isAfterStartDateOrEqual = dateFromList.isAfter(startDateLocal) || dateFromList.isEqual(startDateLocal);
            boolean isBeforeEndDateOrEqual = dateFromList.isBefore(endDateLocal) || dateFromList.isEqual(endDateLocal);
            if(!(isAfterStartDateOrEqual && isBeforeEndDateOrEqual)){
                System.out.println("date from list " + dateList.get(i));
                System.out.println("startDateLocal = " + startDateLocal);

                System.out.println("endDateLocal = " + endDateLocal);
                System.out.println("!endDateLocal.isAfter(dateList.get(i)) = " + !endDateLocal.isAfter(localDateList.get(i)));
                checkDates = false;
                break;
            }
        }

        return checkDates;
    }

    public static List<LocalDate> convertListToDate(List<String> list){
        List<LocalDate> dateList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            int year = Integer.parseInt(list.get(i).split("-")[0]);
            int month = Integer.parseInt(list.get(i).split("-")[1]);
            int day = Integer.parseInt(list.get(i).split("-")[2]);

            dateList.add(LocalDate.of(year,month,day));
        }

        return dateList;
    }


    public static boolean isSortedAscendingOrder(List<WebElement> list){
        boolean isSorted = false;
        List<String> listText = new ArrayList<>();
        for (WebElement element : list) {
            if (!element.getText().isEmpty()) {
                listText.add(element.getAttribute("textContent"));
            }
        }
        List<String> listTextLowercase = new ArrayList<>();

        for (int i = 0; i < listText.size(); i++) {
            listTextLowercase.add(listText.get(i).toLowerCase());
        }

        List<String> listTextToSort = new ArrayList<>(listTextLowercase);
        Collections.sort(listTextToSort);
        isSorted = listTextLowercase.equals(listTextToSort);

        System.out.println("listTextToSort.toString() = " + listTextToSort.toString());
        System.out.println("listTextLowercase = " + listTextLowercase);

        return isSorted;
    }

    public static boolean isSortedDescendingOrder(List<WebElement> list){
        boolean isSorted = false;
        List<String> listText = new ArrayList<>();
        for (WebElement element : list) {
            if (!element.getText().isEmpty()) {
                listText.add(element.getAttribute("textContent"));
            }
        }
        List<String> listTextLowercase = new ArrayList<>();

        for (int i = 0; i < listText.size(); i++) {
            listTextLowercase.add(listText.get(i).toLowerCase());
        }
        List<String> listTextToSort = new ArrayList<>(listTextLowercase);
        Collections.sort(listTextToSort,Collections.reverseOrder());
        isSorted = listTextLowercase.equals(listTextToSort);

        System.out.println("listTextToSort.toString() = " + listTextToSort.toString());
        System.out.println("listTextLowercase = " + listTextLowercase.toString());

        return isSorted;
    }

    public static boolean isListContainsValue(List<WebElement> list, String value){
        boolean isContins = true;
        if (list.size()<1){
            return false;
        }
        List<String> txtList = getElementsText(list);
        for (String each : txtList){
            System.out.println(each + "  " + value);
            if (!each.contains(value)){
                isContins = false;
                break;
            }
        }
        return isContins;
    }

    public static boolean isListEmpty(List<WebElement> list){
        boolean isEmpty = false;
        int sum = 0;

        List<String> txt_list = getElementsText(list);
        for(String each : txt_list){
            sum += each.length();
        }
        isEmpty = sum > 0;

        return isEmpty;
    }


    public static boolean isListContainsList(List<String> listCheck, List<WebElement> listContains){
        List<String> listContainsTxt = getElementsText(listContains);
        boolean isContains = false;
        int count = 0;
        for (int i = 0; i < listCheck.size(); i++) {
            for (int j = 0; j < listContainsTxt.size(); j++) {
                if (listCheck.get(i).contains(listContainsTxt.get(j))){
                    count++;
                }
            }
        }
        isContains = count >= listCheck.size();

        return isContains;
    }

//    /**
//     * @param name screenshot name
//     * @return path to the screenshot
//     */
//    public static String getScreenshot(String name) {
//        //adding date and time to screenshot name, to make screenshot unique
//        name = new Date().toString().replace(" ", "_").replace(":", "-") + "_" + name;
//        //where we gonna store a screenshot
//        String path = "";
//
//        if (System.getProperty("os.name").toLowerCase().contains("mac")) {
//            path = System.getProperty("user.dir") + "/test-output/screenshots/" + name + ".png";
//        } else {
//            path = System.getProperty("user.dir") + "\\test-output\\screenshots\\" + name + ".png";
//        }
//
//        System.out.println("OS name: " + System.getProperty("os.name"));
//        System.out.println("Screenshot is here: " + path);
//        //since our reference type is a WebDriver
//        //we cannot see methods from TakesScreenshot interface
//        //that's why do casting
//        TakesScreenshot takesScreenshot = (TakesScreenshot) Driver.getDriver();
//        //take screenshot of web browser, and save it as a file
//        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
//        //where screenshot will be saved
//        File destination = new File(path);
//        try {
//            //copy file to the previously specified location
//            FileUtils.copyFile(source, destination);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return path;
//    }



}

