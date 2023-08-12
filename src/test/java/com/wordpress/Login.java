package com.wordpress;

import com.aventstack.extentreports.Status;
import commons.BaseTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.wordpress.DashBoardPageObject;
import pageObject.wordpress.LoginPageObject;
import reportConfig.ExtentTestManager;

import java.lang.reflect.Method;
import java.text.ParseException;

public class Login extends BaseTest {
    LoginPageObject loginPage;
    DashBoardPageObject dashBoardPage;
    String userName = "automationfc";
    String invalidUserName = "automation_my";
    String password = "Hello@123";
    String invalidPassword = "HellO@1234";

    @Parameters({"browser", "adminUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String appUrl) {
        driver = getBrowserDriver(browserName, appUrl);
        loginPage = new LoginPageObject(driver);
//        loginPage.inputToTextboxByID(userName, "user_login");
//        loginPage.inputToTextboxByID(password, "user_pass");


    }

    @Test(enabled = true)
    public void Login_01_Empty_Username(Method method) {
        ExtentTestManager.startTest(method.getName(), "Login with empty username");
        ExtentTestManager.getTest().log(Status.INFO, "Login with empty username - Step 01: Input to Password Textbox");
        loginPage.inputToTextboxByID(password, "user_pass");
        ExtentTestManager.getTest().log(Status.INFO, "Login with empty username - Step 02: Click to button login");
        loginPage.clickToButtonLogin();

        ExtentTestManager.getTest().log(Status.INFO, "Login with empty username - Step 03: Verify message 'The username field is empty.' is displayed");
        System.out.println(loginPage.getLoginErrorMessage());
        verifyEquals(loginPage.getLoginErrorMessage(), "Error: The username field is empty.");


    }

    @Test(enabled = true)
    public void Login_02_Not_Exist_Username(Method method) {
        ExtentTestManager.startTest(method.getName(), "Login with username is not exist");
        ExtentTestManager.getTest().log(Status.INFO, "Login with not existing user name - Step 01: Input to User Name Textbox");
        loginPage.inputToTextboxByID(invalidUserName, "user_login");

        ExtentTestManager.getTest().log(Status.INFO, "Login with not existing user name - Step 02: Input to Password Textbox");
        loginPage.inputToTextboxByID(password, "user_pass");
        ExtentTestManager.getTest().log(Status.INFO, "Login with not existing  username - Step 03: Click to button login");
        loginPage.clickToButtonLogin();

        ExtentTestManager.getTest().log(Status.INFO, "Login with empty username - Step 04: Verify message 'Error: The username aaa is not registered on this site. If you are unsure of your username, try your email address instead.' is displayed");
        System.out.println(loginPage.getLoginErrorMessage());
        verifyEquals(loginPage.getLoginErrorMessage(), "Error: The username" + invalidUserName + "is not registered on this site. If you are unsure of your username, try your email address instead.");
    }

    @Test(enabled = true)
    public void Login_03_Invalid_Password(Method method) {
        ExtentTestManager.startTest(method.getName(), "Login with invalid password");
        ExtentTestManager.getTest().log(Status.INFO, "Login with invalid password - Step 01: Input to User Name Textbox");
        loginPage.inputToTextboxByID(userName, "user_login");

        ExtentTestManager.getTest().log(Status.INFO, "Login with invalid password - Step 02: Input to Password Textbox");
        loginPage.inputToTextboxByID(invalidPassword, "user_pass");

        ExtentTestManager.getTest().log(Status.INFO, "Login with invalid password - Step 03: Click to button login");
        loginPage.clickToButtonLogin();

        ExtentTestManager.getTest().log(Status.INFO, "Login with invalid password - Step 04: Verify message 'Error: The password you entered for the username automationfc is incorrect. Lost your password?' is displayed");
        System.out.println(loginPage.getLoginErrorMessage());
        verifyEquals(loginPage.getLoginErrorMessage(), "Error: The password you entered for the username automationfc is incorrect. Lost your password?");

    }

    @Test(enabled = true)
    public void Login_04_Valid_Username_Password(Method method) {
        ExtentTestManager.startTest(method.getName(), "Login with valid password and username");
        ExtentTestManager.getTest().log(Status.INFO, "Login with invalid password - Step 01: Input to User Name Textbox");
        loginPage.inputToTextboxByID(userName, "user_login");

        ExtentTestManager.getTest().log(Status.INFO, "Login with invalid password - Step 02: Input to Password Textbox");
        loginPage.inputToTextboxByID(password, "user_pass");

        ExtentTestManager.getTest().log(Status.INFO, "Login with invalid password - Step 03: Click to button login");
        loginPage.clickToButtonLogin();

        ExtentTestManager.getTest().log(Status.INFO, "Login with invalid password - Step 04: Verify DashBoard Page is displayed");
        dashBoardPage = new DashBoardPageObject(driver);
        verifyTrue(dashBoardPage.isDashBoardPageDisplayed());

    }


    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }

}
