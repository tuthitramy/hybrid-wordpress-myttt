package reportConfig;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;
import commons.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static reportConfig.ExtentTestManager.getTest;

public class ExtentTestListener extends BaseTest implements ITestListener {
    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        iTestContext.setAttribute("WebDriver", this.getDriverInstance());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        ExtentManager.extentReports.flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        getTest().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Object testClass = iTestResult.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriverInstance();
        String base64Screenshot = "data:image/png;base64,"
                + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
//		getTest().log(Status.FAIL, "Test Failed", getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
        ExtentTest test = getTest();
        if (test != null) {
            test.log(Status.FAIL, "Test Failed");

            if (base64Screenshot != null) {
                Media media = test.addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0);
                if (media != null) {
                    test.log(Status.INFO, "Screenshot", media);
                }
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        getTest().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        getTest().log(Status.FAIL, "Test Failed with percentage" + getTestMethodName(iTestResult));
    }
}
