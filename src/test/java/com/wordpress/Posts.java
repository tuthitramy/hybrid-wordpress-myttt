package com.wordpress;

import com.aventstack.extentreports.Status;
import commons.BaseTest;
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

public class Posts extends BaseTest {
    LoginPageObject loginPage;
    DashBoardPageObject dashBoardPage;

    PostsPageObject postPage;
    UserPostsListPageObject userPostPageList;
    String randomImageName;
    String imageNameAtUserSite;
    String userName = "automationfc";
    String password = "Hello@123";
    String title = "Title " + DataHelper.getDataHelper().getRandomNumber();
    String content = "Content " + DataHelper.getDataHelper().getRandomNumber();
    String category = "Category " + DataHelper.getDataHelper().getRandomNumber();

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
    public void Post_01_Create_Posts(Method method) throws ParseException {
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

        ExtentTestManager.getTest().log(Status.INFO, "Create posts - Step 17: Back to Post List Page  ");
        postPage = (PostsPageObject) dashBoardPage.clickToMenuByText(driver, "Posts");

        ExtentTestManager.getTest().log(Status.INFO, "Create posts - Step 18: Input to search textbox");
        postPage.inputToSearchTextbox(title);

        ExtentTestManager.getTest().log(Status.INFO, "Create posts - Step 19: Click to Search Post Button");
        postPage.clickToSearchPostButton();

        ExtentTestManager.getTest().log(Status.INFO, "Create posts - Step 20: Verify Title is displayed in Search Result");
        verifyEquals(postPage.getDataByColName("Title"), title);

        ExtentTestManager.getTest().log(Status.INFO, "Create posts - Step 21: Verify Author is displayed in Search Result");
        verifyEquals(postPage.getDataByColName("Author"), userName);

        ExtentTestManager.getTest().log(Status.INFO, "Create posts - Step 22: Verify Categories is displayed in Search Result");
        verifyEquals(postPage.getDataByColName("Categories"), category);

        ExtentTestManager.getTest().log(Status.INFO, "Create posts - Step 23: Verify Tags is displayed in Search Result");
        verifyEquals(postPage.getDataByColName("Tags"), tag);
        ExtentTestManager.getTest().log(Status.INFO, "Create posts - Step 24: Get pushlished date ");
        String publishDate = postPage.getPublishedDate();


        ExtentTestManager.getTest().log(Status.INFO, "Create posts - Step 25: Navigate to user site");
        userPostPageList = postPage.navigateToUserSite();

        ExtentTestManager.getTest().log(Status.INFO, "Create posts - Step 26: Input to title to search input");
        userPostPageList.inputToTextboxByID(driver, "wp-block-search__input-1", title + " " + content);

        ExtentTestManager.getTest().log(Status.INFO, "Create posts - Step 27: Click to Search Button");
        userPostPageList.clickToButtonWithSubmitTypeByText(driver, "Search");

        ExtentTestManager.getTest().log(Status.INFO, "Create posts - Step 28: Verify post image is displayed");
        imageNameAtUserSite = userPostPageList.getFileNameAtUserPostListPage();
        System.out.println("Image Name At User Site: " + imageNameAtUserSite);
        verifyEquals(randomImageName, imageNameAtUserSite);

        ExtentTestManager.getTest().log(Status.INFO, "Create posts - Step 29: Get Post Title At User Site");
        String postTitle = userPostPageList.getPostTitle();
        System.out.println("Step 29: " + postTitle);
        ExtentTestManager.getTest().log(Status.INFO, "Create posts - Step 30: Verify Post Title is Displayed");
        System.out.println("Step 30: " + title);
        verifyEquals(title, postTitle);

        ExtentTestManager.getTest().log(Status.INFO, "Create posts - Step 31: Verify created date is displayed");
        String createdPostDate = userPostPageList.getCreatedPostDate();
        String createdPostDateAfterFormat = userPostPageList.convertDateFormat(createdPostDate);
        System.out.println("Created Post Date: " + createdPostDateAfterFormat);
        System.out.println("Publish Date: " + publishDate);
        verifyTrue(publishDate.contains(createdPostDateAfterFormat));


    }

    @Test(enabled = true)
    public void Post_02_Edit_Posts(Method method) {
        ExtentTestManager.startTest(method.getName(), "Edit Posts");
        ExtentTestManager.getTest().log(Status.INFO, "Edit posts - Step 01: Navigate to Admin Site ");
        postPage = userPostPageList.navigateToAdminSite();

        ExtentTestManager.getTest().log(Status.INFO, "Edit posts - Step 02: Navigate to Post List Page  ");
        postPage = (PostsPageObject) dashBoardPage.clickToMenuByText(driver, "Posts");

        ExtentTestManager.getTest().log(Status.INFO, "Edit posts - Step 03: Input to search textbox");
        postPage.inputToSearchTextbox(title + " " + content);


        ExtentTestManager.getTest().log(Status.INFO, "Edit posts - Step 04: Click to Search Post Button");
        postPage.clickToSearchPostButton();

        ExtentTestManager.getTest().log(Status.INFO, "Edit posts - Step 05: Click to Title Link");
        postPage.clickToTitleNameTextLink();

        ExtentTestManager.getTest().log(Status.INFO, "Edit posts - Step 06: Input to Title Textbox");
        System.out.println(title);
        postPage.inputToTextboxByID(driver, "title", "Edit" + title);


        ExtentTestManager.getTest().log(Status.INFO, "Edit posts - Step 07: Input to Content Textbox");
        System.out.println(content);
        postPage.inputToContentTextbox("Edit" + content);

        ExtentTestManager.getTest().log(Status.INFO, "Edit posts - Step 08: Uncheck to Category Checkbox");
        postPage.uncheckToCategoryCheckbox();

        ExtentTestManager.getTest().log(Status.INFO, "Edit posts - Step 09: Click to Add New Category Textlink");
        postPage.clickToAddNewCategoryTextlink();

        ExtentTestManager.getTest().log(Status.INFO, "Edit posts - Step 10: Input to Category Field");
        postPage.inputToTextboxByID(driver, "newcategory", "Edit" + category);

        ExtentTestManager.getTest().log(Status.INFO, "Edit posts - Step 11: Click to Add New button");
        postPage.clickToInputTypeButtonByValue(driver, "Add New Category");

        ExtentTestManager.getTest().log(Status.INFO, "Edit posts - Step 12: Input TAG Field");
        System.out.println(tag);
        postPage.removeTagName();
        postPage.inputToTextboxByID(driver, "new-tag-post_tag", "Edit" + tag);

        ExtentTestManager.getTest().log(Status.INFO, "Edit posts - Step 13: Click to Add button");
        postPage.clickToInputTypeButtonByValue(driver, "Add");

        ExtentTestManager.getTest().log(Status.INFO, "Edit posts - Step 14: Click to Remove Thumbnail Textlink");
        postPage.clickToRemoveThumbnailTextlink();

        ExtentTestManager.getTest().log(Status.INFO, "Edit posts - Step 15: Click to Set featured image button");
        postPage.clickToSetFeaturedImageTextlink();

        ExtentTestManager.getTest().log(Status.INFO, "Edit posts - Step 16: Click to Upload Files Tab");
        postPage.clickToUploadFilesTab();

        ExtentTestManager.getTest().log(Status.INFO, "Edit posts - Step 17: Upload file");
        randomImageName = postPage.uploadRandomImage();
        postPage.sleepInSecond(3);

        ExtentTestManager.getTest().log(Status.INFO, "Edit posts - Step 18: Click to Set Featured Image button");
        postPage.clickToSetFeaturedImage();
        postPage.sleepInSecond(2);

        ExtentTestManager.getTest().log(Status.INFO, "Edit posts - Step 19: Click to Update Button");
        postPage.scrollToElement(driver, PostsPageUI.UPDATE_BUTTON);
        postPage.clickToUpdateButton();

        ExtentTestManager.getTest().log(Status.INFO, "Edit posts - Step 20:  Verify Post Updated Successfully Message ");
        String publishSuccessMessage = postPage.getPublishSuccessMessage();
        verifyTrue(publishSuccessMessage.contains("Post updated."));


        ExtentTestManager.getTest().log(Status.INFO, "Edit posts - Step 21: Back to Admin Post List Page  ");
        postPage = (PostsPageObject) dashBoardPage.clickToMenuByText(driver, "Posts");

        ExtentTestManager.getTest().log(Status.INFO, "Edit posts - Step 22: Input to search textbox");
        postPage.inputToSearchTextbox("Edit" + title);

        ExtentTestManager.getTest().log(Status.INFO, "Edit posts - Step 23: Click to Search Post Button");
        postPage.clickToSearchPostButton();

        ExtentTestManager.getTest().log(Status.INFO, "Edit posts - Step 24: Verify Title is displayed in Search Result");
        verifyEquals(postPage.getDataByColName("Title"), "Edit" + title);

        ExtentTestManager.getTest().log(Status.INFO, "Edit posts - Step 25: Verify Author is displayed in Search Result");
        verifyEquals(postPage.getDataByColName("Author"), userName);

        ExtentTestManager.getTest().log(Status.INFO, "Edit posts - Step 26: Verify Categories is displayed in Search Result");
        verifyEquals(postPage.getDataByColName("Categories"), "Edit" + category);
        System.out.println("Step 22: " + postPage.getDataByColName("Categories"));

        ExtentTestManager.getTest().log(Status.INFO, "Edit posts - Step 27: Verify Tags is displayed in Search Result");
        verifyEquals(postPage.getDataByColName("Tags"), "Edit" + tag);
        System.out.println("Edit" + tag);

        ExtentTestManager.getTest().log(Status.INFO, "Edit posts - Step 28: Navigate to user site");
        userPostPageList = postPage.navigateToUserSite();

        ExtentTestManager.getTest().log(Status.INFO, "Edit posts - Step 29: Input to title to search input");
        userPostPageList.inputToTextboxByID(driver, "wp-block-search__input-1", "Edit" + title + " " + "Edit" + content);

        ExtentTestManager.getTest().log(Status.INFO, "Edit posts - Step 30: Click to Search Button");
        userPostPageList.clickToButtonWithSubmitTypeByText(driver, "Search");

        ExtentTestManager.getTest().log(Status.INFO, "Edit posts - Step 31: Verify post image is displayed");
        imageNameAtUserSite = userPostPageList.getFileNameAtUserPostListPage();
        System.out.println("Image Name At User Site: " + imageNameAtUserSite);
        verifyEquals(randomImageName, imageNameAtUserSite);

        ExtentTestManager.getTest().log(Status.INFO, "Edit posts - Step 32: Get Post Title At User Site");
        String postTitle = userPostPageList.getPostTitle();
        System.out.println("postTitle: " + postTitle);
        System.out.println("Edit" + title);
        ExtentTestManager.getTest().log(Status.INFO, "Edit posts - Step 33: Verify Post Title is Displayed");
        verifyEquals("Edit" + title, postTitle);


    }

    @Test(enabled = true)
    public void Post_03_Delete_Posts(Method method) {
        ExtentTestManager.startTest(method.getName(), "Delete Posts");
        ExtentTestManager.getTest().log(Status.INFO, "Delete posts - Step 01: Navigate to Admin Site ");
        postPage = userPostPageList.navigateToAdminSite();

        ExtentTestManager.getTest().log(Status.INFO, "Delete posts - Step 02: Navigate to Post List Page  ");
        postPage = (PostsPageObject) dashBoardPage.clickToMenuByText(driver, "Posts");

        ExtentTestManager.getTest().log(Status.INFO, "Delete posts - Step 03: Input to search textbox");
        postPage.inputToSearchTextbox("Edit" + title + " " + "Edit" + content);

        ExtentTestManager.getTest().log(Status.INFO, "Edit posts - Step 04: Click to Search Post Button");
        postPage.clickToSearchPostButton();

        ExtentTestManager.getTest().log(Status.INFO, "Delete posts - Step 04: Check to Post checkbox");
        postPage.checkToSelectPost();

        ExtentTestManager.getTest().log(Status.INFO, "Delete posts - Step 05: Select Move to Trash Selection");
        postPage.selectActionByText("Move to Trash");

        ExtentTestManager.getTest().log(Status.INFO, "Delete posts - Step 06: Click to Apply Button");
        postPage.clickToApplyButton();

        ExtentTestManager.getTest().log(Status.INFO, "Delete posts - Step 07: Verify No Items Message");
        verifyEquals(postPage.getNoItemsMessage(driver), "No posts found.");

    }



    @AfterClass(alwaysRun = false)
    public void afterClass() {
//        closeBrowserDriver();
    }

}
