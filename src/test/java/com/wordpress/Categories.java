package com.wordpress;

import com.aventstack.extentreports.Status;
import commons.BaseTest;
import commons.Create_Posts;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.wordpress.*;
import pageUIs.wordpress.PostsPageUI;
import reportConfig.ExtentTestManager;
import utilities.DataHelper;

import java.lang.reflect.Method;
import java.text.ParseException;

public class Categories extends BaseTest {
    LoginPageObject loginPage;
    DashBoardPageObject dashBoardPage;
    String userName = "automationfc";
    PostsPageObject postPage;
    CategoriesPageObject categoriesPage;
    UserPostsListPageObject userPostPageList;
    String randomImageName;

    String imageNameAtUserSite;
    String password = "Hello@123";
    String categoryName = "CategoryName " + DataHelper.getDataHelper().getRandomNumber();
    String SlugName = "SlugName" + DataHelper.getDataHelper().getRandomNumber();
    String description = "Description " + DataHelper.getDataHelper().getRandomNumber();
    String title = "Title " + DataHelper.getDataHelper().getRandomNumber();
    String content = "Content " + DataHelper.getDataHelper().getRandomNumber();
    String tag = "Tag " + DataHelper.getDataHelper().getRandomNumber();

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
    public void Categories_01_Create_Categories(Method method) {
        ExtentTestManager.startTest(method.getName(), "Create Categories");
        ExtentTestManager.getTest().log(Status.INFO, "Create Categories - Step 01: Click to Post menu ");
        postPage = (PostsPageObject) dashBoardPage.clickToMenuByText(driver, "Posts");

        ExtentTestManager.getTest().log(Status.INFO, "Create Categories - Step 02: Click to Categories");
        categoriesPage = postPage.clickToCategoriesLink();

        ExtentTestManager.getTest().log(Status.INFO, "Create Categories - Step 03: Input to Name Field");
        categoriesPage.inputToTextboxByID(driver, "tag-name", categoryName);

        ExtentTestManager.getTest().log(Status.INFO, "Create Categories - Step 04: Input to Slug Field");
        categoriesPage.inputToTextboxByID(driver, "tag-slug", SlugName);

        ExtentTestManager.getTest().log(Status.INFO, "Create Categories - Step 05: Select random parent category");
        categoriesPage.selectRandomParentInDropdownByText();

        ExtentTestManager.getTest().log(Status.INFO, "Create Categories - Step 06: Input to description Textarea");
        categoriesPage.inputToDescriptionTextArea(description);

        ExtentTestManager.getTest().log(Status.INFO, "Create Categories - Step 07: Click To button Add New Category");
        categoriesPage.clickToButtonWithInputTagSubmitTypeByValue(driver, "Add New Category");
        ExtentTestManager.getTest().log(Status.INFO, "Create Categories - Step 08: Input to Search Field");
        categoriesPage.inputToTextboxByID(driver, "tag-search-input", categoryName);
        ExtentTestManager.getTest().log(Status.INFO, "Create Categories - Step 09: Click to Search Category Button");
        categoriesPage.clickToButtonWithInputTagSubmitTypeByValue(driver, "Search Categories");

        ExtentTestManager.getTest().log(Status.INFO, "Create Categories - Step 09: Verify Category is displayed");
        verifyTrue(categoriesPage.isCategoryDisplayed(categoryName));
        ExtentTestManager.getTest().log(Status.INFO, "Create Categories - Step 09: Verify Category is displayed");
        categoriesPage.hoverToCateGoryName(categoryName);
//        ExtentTestManager.getTest().log(Status.INFO, "Create Categories - Step 09: Verify Category is displayed");
//        categoriesPage.clickToViewTextLink();


    }

    @Test(enabled = true)
    public void Categories_02_View_Categories(Method method) {
        ExtentTestManager.startTest(method.getName(), "View Category");
        ExtentTestManager.getTest().log(Status.INFO, "Create posts - Step 01: Click to Post menu ");
        postPage = (PostsPageObject) categoriesPage.clickToMenuByText(driver, "Posts");

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
        postPage.checkToCategoryCheckboxByText(categoryName);

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

        ExtentTestManager.getTest().log(Status.INFO, "Create Categories - Step 02: Click to Categories");
        categoriesPage = postPage.clickToCategoriesLink();


        ExtentTestManager.getTest().log(Status.INFO, "Create Categories - Step 08: Input to Search Field");
        categoriesPage.inputToTextboxByID(driver, "tag-search-input", categoryName);
        ExtentTestManager.getTest().log(Status.INFO, "Create Categories - Step 09: Click to Search Category Button");
        categoriesPage.clickToButtonWithInputTagSubmitTypeByValue(driver, "Search Categories");

        ExtentTestManager.getTest().log(Status.INFO, "Create Categories - Step 09: Verify Category is displayed");
        verifyTrue(categoriesPage.isCategoryDisplayed(categoryName));
        ExtentTestManager.getTest().log(Status.INFO, "Create Categories - Step 09: Verify Category is displayed");
        categoriesPage.hoverToCateGoryName(categoryName);
        ExtentTestManager.getTest().log(Status.INFO, "Create Categories - Step 09: Verify Category is displayed");
        categoriesPage.clickToViewTextLink();


    }

    @Test(enabled = false)
    public void Post_03_Delete_Posts(Method method) {

    }


    @AfterClass(alwaysRun = false)
    public void afterClass() {
//        closeBrowserDriver();
    }

}
