package com.sk.automationpractice.tests;

import com.sk.automationpractice.utils.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * All the test class must extend this class as base class.
 * It includes all generic methods required for the execution of test cases.
 */

public class TestBase {

    protected static Properties CONFIG = new Properties();
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        initializeTestBaseSetup();
    }


    private void initializeTestBaseSetup() {
        Log.info("Starting Automation: Loading the Configuration Properties");
        loadData();
        Log.debug("Opening Browser: " + CONFIG.getProperty("browser"));
        setBrowser(CONFIG.getProperty("browser"));
    }

    private void loadData() {
        try {
            Log.debug(System.getProperty("user.dir") + "\\src\\main\\resources\\Config.properties");
            FileInputStream inputStream = new FileInputStream(
                    System.getProperty("user.dir") + "\\src\\main\\resources\\Config.properties");
            CONFIG.load(inputStream);
            Log.info("Config file is loaded");

        } catch (Exception exception) {
            Log.debug("file is not loaded " + exception.getMessage());
        }
    }

    private void setBrowser(String browserType) {
        switch (browserType) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe");
                driver = new ChromeDriver();
                Log.debug("Running chrome");
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\geckodriver.exe");
                driver = new FirefoxDriver();
                Log.debug("Running firefox");

                break;
            default:
                Log.debug("browser : " + browserType + " is invalid, Launching Firefox as browser of choice..");
                driver = new FirefoxDriver();
        }
    }

    protected void getUrl(String url) {
        Log.info("navigating to :-" + url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);
        driver.manage().window().maximize();
    }


    @AfterTest
    public void tearDown() {
        //Write a Log when tests are ending
        Log.endLog("Test is ending!");
        driver.close();
    }


}
