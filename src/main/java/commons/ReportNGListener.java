package commons;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReportNGListener extends BaseTest implements ITestListener {
    @Override
    public void onStart(ITestContext context) {
        System.out.println("---------- " + context.getName() + " STARTED test ----------");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("---------- " + context.getName() + " FINISHED test ----------");
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("---------- " + result.getName() + " STARTED test ----------");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("---------- " + result.getName() + " SUCCESS test ----------");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("---------- " + result.getName() + " FAILED test ----------");
        System.setProperty("org.uncommons.reportng.escape-output", "false");

        Object testClass = result.getInstance();
        WebDriver webDriver = ((BaseTest) testClass).getDriverInstance();

        String screenshotPath = captureScreenshotFile(webDriver, result.getName());
        Reporter.getCurrentTestResult();
        //File
        Reporter.log("<br><a target=\"_blank\" href=\"file:///" + screenshotPath + "\">" + "<img src=\"file:///"
                + screenshotPath + "\" " + "height='100' width='150'/> " + "</a></br>");
        //Base64
//		Reporter.log("<br><a target= href=\"data:image/png;base64," + screenshotPath + "\">" + "<img src=\"data:image/png;base64," + screenshotPath + "\" " + "height='100' width='150'/> " + "</a></br>");
        Reporter.setCurrentTestResult(null);
    }

    public String captureScreenshotFile(WebDriver driver, String screenshotName) {
        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
            //File
            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenPath = GlobalConstants.REPORTNG_SCREENSHOT + screenshotName + "_"
                    + formater.format(calendar.getTime()) + ".png";
            FileUtils.copyFile(source, new File(screenPath));
            return screenPath;
        } catch (IOException e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
            return e.getMessage();
        }
    }
//	public String captureScreenshotBase64(WebDriver driver, String screenshotName) {
//		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
//
//	}

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("---------- " + result.getName() + " SKIPPED test ----------");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("---------- " + result.getName() + " FAILED WITH SUCCESS PERCENTAGE test ----------");
    }

    @BeforeSuite
    public void deleteAllFilesInReportNGScreenshot() {
        System.out.println("---------- START delete file in folder ----------");
        deleteAllFileInFolder();
        System.out.println("---------- END delete file in folder ----------");
    }

    public void deleteAllFileInFolder() {
        try {
            String workingDir = GlobalConstants.PROJECT_PATH;
            String pathFolderDownload = workingDir + "\\ReportNGScreenShots";
            File file = new File(pathFolderDownload);
            File[] listOfFiles = file.listFiles();
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    System.out.println(listOfFiles[i].getName());
                    new File(listOfFiles[i].toString()).delete();
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

}


