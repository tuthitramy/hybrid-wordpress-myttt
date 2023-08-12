package commons;

import com.aventstack.extentreports.Status;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.wordpress.DashBoardPageObject;
import pageObject.wordpress.LoginPageObject;
import pageObject.wordpress.PostsPageObject;
import pageObject.wordpress.UserPostsListPageObject;
import pageUIs.wordpress.PostsPageUI;
import reportConfig.ExtentTestManager;
import utilities.DataHelper;

import java.lang.reflect.Method;
import java.text.ParseException;

public class Create_Posts extends BaseTest {
    LoginPageObject loginPage;
    DashBoardPageObject dashBoardPage;

    PostsPageObject postPage;
    String randomImageName;
    String userName = "automationfc";
    String password = "Hello@123";
    String title = "Title " + DataHelper.getDataHelper().getRandomNumber();
    String content = "Content " + DataHelper.getDataHelper().getRandomNumber();
    public static String category = "Category " + DataHelper.getDataHelper().getRandomNumber();

    String tag = "#Tag " + DataHelper.getDataHelper().getRandomNumber();

    @Parameters({"browser", "adminUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String appUrl) {
        driver = getBrowserDriver(browserName, appUrl);

        loginPage = new LoginPageObject(driver);
        loginPage.inputToTextboxByID(userName, "user_login");
        loginPage.inputToTextboxByID(password, "user_pass");
        loginPage.clickToButtonLogin();
        dashBoardPage = new DashBoardPageObject(driver);


    }

    @Test(enabled = true)
    public void Post_Create_Posts(Method method) throws ParseException {
        ExtentTestManager.startTest(method.getName(), "Create posts");
        ExtentTestManager.getTest().log(Status.INFO, "Create posts - Step 01: Click to Post menu ");
        postPage = (PostsPageObject) dashBoardPage.clickToMenuByText(driver, "Posts");

        ExtentTestManager.getTest().log(Status.INFO, "Create posts - Step 02: Click to Add New Button");
        postPage.clickToAddNewButton();

        ExtentTestManager.getTest().log(Status.INFO, "Create posts - Step 03: Input to Title Textbox");
        System.out.println(title);
        postPage.inputToTextboxByID(driver, "title", title);

        ExtentTestManager.getTest().log(Status.INFO, "Create posts - Step 04: Move to Text Tab");
        postPage.clickToTextTab();

        ExtentTestManager.getTest().log(Status.INFO, "Create posts - Step 05: Input to Content Textbox");
        System.out.println(content);
        postPage.inputToContentTextbox(content);

        ExtentTestManager.getTest().log(Status.INFO, "Create posts - Step 06: Click to Add New Category Textlink");
        postPage.clickToAddNewCategoryTextlink();

        ExtentTestManager.getTest().log(Status.INFO, "Create posts - Step 07: Input to Category Field");
        System.out.println(category);
        postPage.inputToTextboxByID(driver, "newcategory", category);

        ExtentTestManager.getTest().log(Status.INFO, "Create posts - Step 08: Click to Add New button");
        postPage.clickToInputTypeButtonByValue(driver, "Add New Category");

        ExtentTestManager.getTest().log(Status.INFO, "Create posts - Step 09: Input TAG Field");
        System.out.println(tag);
        postPage.inputToTextboxByID(driver, "new-tag-post_tag", tag);

        ExtentTestManager.getTest().log(Status.INFO, "Create posts - Step 10: Click to Add button");
        postPage.clickToInputTypeButtonByValue(driver, "Add");

        ExtentTestManager.getTest().log(Status.INFO, "Create posts - Step 11: Click to Set featured image button");
        postPage.clickToSetFeaturedImageTextlink();

        ExtentTestManager.getTest().log(Status.INFO, "Create posts - Step 12: Click to Upload Files Tab");
        postPage.clickToUploadFilesTab();

        ExtentTestManager.getTest().log(Status.INFO, "Create posts - Step 13: Upload file");
        randomImageName = postPage.uploadRandomImage();
        postPage.sleepInSecond(3);

        ExtentTestManager.getTest().log(Status.INFO, "Create posts - Step 14: Click to Set Featured Image button");
        postPage.clickToSetFeaturedImage();
        postPage.sleepInSecond(2);

        ExtentTestManager.getTest().log(Status.INFO, "Create posts - Step 15: Click to Publish Button");
        postPage.scrollToElement(driver, PostsPageUI.ADD_NEW_POST_HEADER);
        postPage.clickToPublishButton();
        postPage.sleepInSecond(2);

        ExtentTestManager.getTest().log(Status.INFO, "Create posts - Step 16:  Verify Post Publish Successfully Message ");
        String publishSuccessMessage = postPage.getPublishSuccessMessage();
        verifyTrue(publishSuccessMessage.contains("Post published."));
    }

    @AfterClass(alwaysRun = false)
    public void afterClass() {
        closeBrowserDriver();
    }
}
