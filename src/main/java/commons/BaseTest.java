package commons;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver driver;
    protected final Log log;

    protected BaseTest() {
        log = LogFactory.getLog(getClass());
    }

    protected WebDriver getBrowserDriver(String browserName, String appUrl) {
        switch (browserName) {
            case ("chrome"):
                ChromeOptions options = new ChromeOptions();
                options.setBinary("C:\\Users\\My\\Downloads\\chrome-win64\\chrome-win64\\chrome.exe");
//                options.addArguments("--disable-notifications");
//                options.addArguments("--disable-extensions");
//                options.addArguments("--disable-infobars");
//                options.setExperimentalOption("useAutomationExtension", false);
//                options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
//                Map<String, Object> prefs = new HashMap<String, Object>();
//                prefs.put("credentials_enable_service", false);
//                prefs.put("profile.password_manager_enabled", false);
//                prefs.put("profile.default_content_setting_values.automatic_downloads", 1);
//                prefs.put("profile.default_content_setting_values.notifications", 2);
//                prefs.put("profile.default_content_setting_values.popups", 2);
//                prefs.put("profile.managed_default_content_settings.geolocation", 2);
//                options.setExperimentalOption("prefs", prefs);

                WebDriverManager.chromedriver().setup();
                // Create the ChromeDriver with the desired options
                driver = new ChromeDriver(options);

                break;
            case ("firefox"):

                // Instal plugin for Firefox
                FirefoxProfile profile = new FirefoxProfile();
                File translate = new File(
                        GlobalConstants.PROJECT_PATH + "\\browserExtensions\\to_google_translate-4.2.0.xpi");
                profile.addExtension(translate);
                FirefoxOptions options1 = new FirefoxOptions();
                options1.setProfile(profile);
                options1.addArguments("--disable-notifications");
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(options1);
//			driver = new FirefoxDriver();

                break;

            case ("edge"):
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();

                break;
            default:
                throw new RuntimeException("Please input correct the browser name!");

        }
        driver.get((appUrl));
        driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;

    }

    protected boolean verifyTrue(boolean condition) {
        boolean pass = true;
        try {
            if (condition == true) {
                System.out.println(" -------------------------- PASSED -------------------------- ");
            } else {
                System.out.println(" -------------------------- FAILED -------------------------- ");
            }
            Assert.assertTrue(condition);
        } catch (Throwable e) {
            pass = false;

            // Add lá»—i vÃ o ReportNG
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyFalse(boolean condition) {
        boolean pass = true;
        try {
            if (condition == false) {
                System.out.println(" -------------------------- PASSED -------------------------- ");
            } else {
                System.out.println(" -------------------------- FAILED -------------------------- ");
            }
            Assert.assertFalse(condition);
        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assert.assertEquals(actual, expected);
            System.out.println(" -------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
            pass = false;
            System.out.println(" -------------------------- FAILED -------------------------- ");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    public WebDriver getDriverInstance() {
        return this.driver;
    }

    public void closeBrowserDriver() {
        String cmd = null;
        try {
            String osName = GlobalConstants.OS_NAME;
            log.info("OS name = " + osName);

            String driverInstanceName = driver.toString().toLowerCase();
            log.info("Driver instance name = " + driverInstanceName);

            String browserDriverName = null;

            if (driverInstanceName.contains("chrome")) {
                browserDriverName = "chromedriver";
            } else if (driverInstanceName.contains("firefox")) {
                browserDriverName = "geckodriver";
            } else if (driverInstanceName.contains("edge")) {
                browserDriverName = "msedgedriver";
            } else if (driverInstanceName.contains("opera")) {
                browserDriverName = "operadriver";
            } else {
                browserDriverName = "safaridriver";
            }

            if (osName.contains("window")) {
                cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
            } else {
                cmd = "pkill " + browserDriverName;
            }

            if (driver != null) {
                driver.manage().deleteAllCookies();
                driver.quit();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            try {
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void onTestFailureBeforeClass(ITestResult result) {
        // TODO Auto-generated method stub

    }

    protected void showBrowserConsoleLogs(WebDriver driver) {
        if (driver.toString().contains("chrome") || driver.toString().contains("edge")) {
            LogEntries logs = driver.manage().logs().get("browser");
            List<LogEntry> logList = logs.getAll();
            for (LogEntry logging : logList) {
                if (logging.getLevel().toString().toLowerCase().contains("error")) {
                    log.info("----------------------------" + logging.getLevel().toString()
                            + "------------------------ \n" + logging.getMessage());
                }
            }
        }
    }

}
