package com.zerobank.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.WebDriverManagerException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.HashMap;

public class Driver {
    private Driver(){}

    private static ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

    public static WebDriver getDriver(){
        if (driverPool.get() == null){
            String browser = ConfigurationReader.getProperty("browser");
            switch (browser.toLowerCase()){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    HashMap<String,Object> chromePrefs = new HashMap<>();
                    chromePrefs.put("profile.default_content_settings.popups",0);
                    chromePrefs.put("download.default_directory",System.getProperty("user.dir") + "/src/DownloadsUI");
                    driverPool.set(new ChromeDriver(new ChromeOptions().setExperimentalOption("prefs",chromePrefs)));
                    break;
                case "chrome-headless":
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver(new ChromeOptions().setHeadless(true)));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    break;
                case "firefox-headless":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver(new FirefoxOptions().setHeadless(true)));
                    break;
                case "ie":
                    if (!System.getProperty("os.name").toLowerCase().contains("windows")){
                        throw new WebDriverManagerException("Your OS doesn't support this browser");
                    }
                    WebDriverManager.iedriver().setup();
                    driverPool.set(new InternetExplorerDriver());
                    break;
                case "edge":
                    if (!System.getProperty("os.name").toLowerCase().contains("windows")){
                        throw new WebDriverManagerException("Your OS doesn't support this browser");
                    }
                    WebDriverManager.edgedriver().setup();
                    driverPool.set(new EdgeDriver());
                    break;
                case "safari":
                    if (!System.getProperty("os.name").toLowerCase().contains("mac")){
                        throw new WebDriverManagerException("Your OS doesn't support this browser");
                    }
                    WebDriverManager.getInstance(SafariDriver.class).setup();
                    driverPool.set(new SafariDriver());
                    break;
            }
        }
        return driverPool.get();
    }

    public static void closeDriver(){
        if (driverPool.get() != null){
            driverPool.get().quit();
            driverPool.remove();
        }
    }

}
