package com.sk.automationpractice.tests;

import com.sk.automationpractice.pages.HomePage;
import com.sk.automationpractice.utils.Log;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Scenario1 extends TestBase {

    @BeforeMethod
    public void navigateToURL() {
        getUrl(CONFIG.getProperty("url"));
    }


    @Test(dataProvider = "PersonalInfoProvider")
    public void personalInfo(String firstname, String lastname, String sex, String experience, String date,
                             ArrayList<String> profession, String pathPic, ArrayList<String> tools, ArrayList<String> continent, ArrayList<String> selCommands) {
        Log.startTestCase("login");

        HomePage homePage = new HomePage(driver);
        homePage.fillData(firstname, lastname, sex, experience, date, profession, pathPic, tools, continent, selCommands);
    }

    @DataProvider(name = "PersonalInfoProvider")
    public Object[][] getDataFromDataprovider() {

        List<String> profession = new ArrayList<String>();
        profession.add("Manual Tester");
        profession.add("Automation Tester");

        List<String> tools = new ArrayList<String>();
        tools.add("QTP");
        tools.add("Selenium IDE");
        tools.add("Selenium Webdriver");

        List<String> continent = new ArrayList<String>();
        continent.add("Europe");

        List<String> commands = new ArrayList<String>();
        commands.add("Browser Commands");
        commands.add("Wait Commands");

        return new Object[][]
                {
                        {"Sweta", "Katkoria", "Female", "7", "21091991", profession, "C:\\Users\\skatkoria\\Pictures\\crop.jpg", tools, continent, commands}

                };


    }
}


