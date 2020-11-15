package com.rgstoian.SeleniumDemo.pages;

import com.rgstoian.SeleniumDemo.XPathRepo;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import java.sql.Driver;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class MainPage {
    WebDriver browser;
    public @FindBy(css = "div[class^='v-label']")
    WebElement title;
    public @FindBy(css = "input[placeholder^='filter by name']")
    WebElement filterInput;
    public @FindBy(xpath = "//span[text()='\uF00D']//ancestor::div[@role='button']")
    WebElement cancelFilterInput;
    public @FindBy(xpath = "//div[@class='v-grid-tablewrapper']/table")
    WebElement mainTable;
    public @FindBy(xpath = "//span[@class='v-button-caption' and text()='Add new customer']//ancestor::div[@role='button']")
    WebElement addNewCustomerButton;
    public @FindBy(xpath = "//span[text()='First Name']//ancestor::tr//input")
    WebElement firstNameInput;
    public @FindBy(xpath = "//span[text()='Last Name']//ancestor::tr//input")
    WebElement lastNameInput;
    public @FindBy(xpath = "//span[text()='email']//ancestor::tr//input")
    WebElement emailInput;
    public @FindBy(xpath = "//span[text()='Status']//ancestor::tr//select")
    WebElement statusSelectElement;
    public @FindBy(xpath = "//span[text()='Birthdate']//ancestor::tr//input")
    WebElement dateInput;
    public @FindBy(xpath = "//span[@class='v-button-caption' and text()='Save']//ancestor::div[@role='button']")
    WebElement saveButton;
    public @FindBy(xpath = "//span[@class='v-button-caption' and text()='Cancel']//ancestor::div[@role='button']")
    WebElement cancelButton;
    public @FindBy(xpath = "//h1[@class='v-Notification-caption' and text()='Customer created!']")
    WebElement creationNotification;

    public MainPage(WebDriver driver) {
        this.browser = driver;
        browser.manage().timeouts().implicitlyWait((long) 2, TimeUnit.SECONDS);
        PageFactory.initElements(driver, this);
    }

    public void addNewCustomer(String fname, String lname, String email, String status, String date) {
        addNewCustomerButton.click();
        firstNameInput.sendKeys(fname);
        lastNameInput.sendKeys(lname);
        emailInput.sendKeys(email);
        new Select(statusSelectElement).selectByValue(status);
        dateInput.sendKeys(date);
        saveButton.click();
        Assert.assertTrue(creationNotification.isEnabled());


    }

    public void checkCustomerInTable(String fname, String lname, String email, String status, String date) {
        WebElement createdFName = mainTable.findElement(By.xpath("//td[text()='" + fname + "']"));
        WebElement createdLName = mainTable.findElement(By.xpath("//td[text()='" + lname + "']"));
        WebElement createdEmail = mainTable.findElement(By.xpath("//td[text()='" + email + "']"));
        Assert.assertTrue(createdFName.isEnabled());
        Assert.assertTrue(createdLName.isEnabled());
        Assert.assertTrue(createdEmail.isEnabled());
        createdFName.click();

        lastNameInput.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Assert.assertTrue(firstNameInput.getAttribute("value")==fname);
//        Assert.assertTrue(getValueOfShittyInput(firstNameInput)==fname);
//        Assert.assertTrue(getValueOfShittyInput(lastNameInput)==lname);
//        Assert.assertTrue(getValueOfShittyInput(emailInput)==email);
        Assert.assertTrue(new Select(statusSelectElement).getFirstSelectedOption().getText()==status);
    }

//    public String getValueOfShittyInput(WebElement inputElement){
//        return ((JavascriptExecutor)browser).executeScript("arguments[0].value", inputElement);
//    }
}
