package com.zerobank.stepdefinitions;

import com.zerobank.pages.OnlineStatementsPage;
import com.zerobank.utilities.BrowserUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.List;

public class StatementsAndDocumentsSteps {
    OnlineStatementsPage onlineStatementsPage;
    int yearStatement;
    File file;
    File[] files;

    @When("the user selects the Recent Statements Year {int}")
    public void the_user_selects_the_Recent_Statements_Year(int year) {
        onlineStatementsPage = new OnlineStatementsPage();
        yearStatement = year;
        for (WebElement each: onlineStatementsPage.listRecentStatements){
            if (each.getText().equals(year+"")){
                each.click();
                break;
            }
        }
    }

    @Then("{int} statements should be displayed for that year")
    public void statements_should_be_displayed_for_that_year(int statementCount) {
        Assert.assertEquals(onlineStatementsPage.getListOfStatements(yearStatement).size(), statementCount);
    }



    @When("the user clicks on statement {string}")
    public void the_user_clicks_on_statement(String nameOfStatement) {
        List<WebElement> listOfStatements = onlineStatementsPage.getListOfStatements(yearStatement);
        for (WebElement each: listOfStatements){
            if (each.getText().equals(nameOfStatement)){
                each.click();
                BrowserUtils.wait(3);
                break;
            }
        }
    }

    @Then("the downloaded file name should contain {string}")
    public void the_downloaded_file_name_should_contain(String nameOfTheFile) {
        try {
            file = new File(System.getProperty("user.dir")+"/src/DownloadsUI");
            files = file.listFiles();
            boolean nameCheck = false;
            for (File each:files){
                if (each.getName().contains(nameOfTheFile)){
                    System.out.println(each.getName());
                    nameCheck = true;
                    file = each;
                    break;
                }
            }
            Assert.assertTrue(nameCheck);
        }finally {
            System.out.println("See what happened");
        }
    }

    @Then("the file type should be {string}")
    public void the_file_type_should_be_pdf(String extension) {
        try {
            boolean extensionCheck = false;
            for (File each:files){
                if (each.getName().contains(extension)){
                    System.out.println(each.getName());
                    extensionCheck = true;
                    file = each;
                    break;
                }
            }
            Assert.assertTrue(extensionCheck);
        }finally {
            file.delete();
        }
    }
}
