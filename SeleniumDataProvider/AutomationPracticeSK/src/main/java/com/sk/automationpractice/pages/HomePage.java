package com.sk.automationpractice.pages;

import com.sk.automationpractice.utils.Log;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class HomePage {


    private WebDriver driver;

    @FindBy(name = "firstname")
    private WebElement firstname;

    @FindBy(name = "lastname")
    private WebElement lastname;

    @FindBy(name = "sex")
    private List<WebElement> radioSex;

    @FindBy(name = "exp")
    private List<WebElement> radioExp;

    @FindBy(id = "datepicker")
    private WebElement datepicker;

    @FindBy(name = "profession")
    private List<WebElement> chkProfession;

    @FindBy(id = "photo")
    private WebElement btnBrowse;

    @FindBy(name = "tool")
    private List<WebElement> chkTool;

    @FindBy(id = "continents")
    private WebElement continents;

    @FindBy(id = "selenium_commands")
    private WebElement selenium_commands;

    @FindBy(id = "submit")
    private WebElement submit;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HomePage fillData(String firstname, String lastname, String sex, String experience, String date,
                             ArrayList<String> profession, String pathPic, ArrayList<String> tools,
                             ArrayList<String> continent, ArrayList<String> commands) {
        Log.debug("########Signing with firstname: " + firstname);
        this.firstname.sendKeys(firstname);
        this.lastname.sendKeys(lastname);
        getRadioByValue(radioSex, sex);
        getRadioByValue(radioExp, experience);
        datepicker.sendKeys(date);
        getChkByValue(chkProfession, profession);
        btnBrowse.sendKeys(pathPic);
        getChkByValue(chkTool, tools);
        selectByValue(continents, continent);
        scroll(selenium_commands);
        selectByValue(selenium_commands, commands);
        submit.click();

        return new HomePage(driver);
    }

    private void getRadioByValue(List<WebElement> elements, String str) {
        for (WebElement ele : elements) {

            String sValue = ele.getAttribute("value");
            if (sValue.equalsIgnoreCase(str)) {

                ele.click();
                Log.info("Clicked on RadioButton" + sValue);

                break;

            }
        }
    }

    private void getChkByValue(List<WebElement> elements, ArrayList<String> str) {
        for (WebElement ele : elements) {
            for (String s : str) {

                String sValue = ele.getAttribute("value");
                if (sValue.equalsIgnoreCase(s)) {

                    ele.click();
                    Log.info("Clicked on checkbox" + sValue);

                }
            }
        }
    }

    private void selectByValue(WebElement element, ArrayList<String> str) {
        Select select = new Select(element);
        for (String s : str) {
            select.selectByVisibleText(s);
        }
    }

    private void scroll(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
        Log.debug("Scrolled");
    }
}
