package com.Selenium_for_intellij;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

public class TestBase {
    public static WebDriver driver = null;

    public static void initialize() {
        if(driver == null) {
            if(Constants.browserName.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
                driver = new ChromeDriver();
            } else if(Constants.browserName.equalsIgnoreCase("firefox")) {
                System.setProperty("webdriver.chrome.driver", "drivers/geckodriver");
                driver = new FirefoxDriver();
            }
        }
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(Constants.url);
    }
    public static void quit() {
        System.out.println("quitting the browser");
        driver.quit();
        driver = null;
    }
    public static void close() {
        System.out.println("quitting the browser");
        driver.close();
        driver = null;
    }
}
