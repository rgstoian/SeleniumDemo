package com.rgstoian.SeleniumDemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import org.testng.Assert;
import com.rgstoian.SeleniumDemo.pages.MainPage;
import org.testng.reporters.jq.Main;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;


public class CheckElementsTest {
    SoftAssert verify = new SoftAssert();
    WebDriver browser;
    MainPage page;

    @BeforeClass
    public void setUp() {
        browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.get("http://rs0506.go.ro:6969/");
        browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        page = new MainPage(browser);
    }

    @Test
    public void checkDefaultElements() {
        verify.assertTrue(page.title.isDisplayed());
        verify.assertTrue(page.filterInput.isDisplayed());
        verify.assertTrue(page.cancelFilterInput.isDisplayed());
        verify.assertTrue(page.mainTable.isDisplayed());
    }

    @Test
    public void createNewCustomer() {
        String fname = "Pulica";
        String lname = "Franaru";
        String email = "pf@eu.com";
        String status = "2";
        String date = "11/5/20";
        page.addNewCustomer(fname, lname, email, status, date);
        page.checkCustomerInTable(fname, lname, email, status, date);
    }

    @Test
    public void editCustomer() {
        String editedFName = "Ion";
        String editedLName = "Popescu";
        String editedEMail = "ip@eu.com";
//        page.editPosition(editedFName, editedLName, editedEMail);
    }

    @AfterClass
    public void tearDown() {
        browser.quit();
    }
}
