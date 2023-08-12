package pageObject.wordpress;

import commons.BasePage;
import commons.WordpressPageGeneraterManager;
import org.openqa.selenium.WebDriver;
import pageUIs.wordpress.LoginPageUI;
import pageUIs.wordpress.PostsPageUI;

public class PostsPageObject extends BasePage {
    WebDriver driver;

    public PostsPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToAddNewButton() {
        waitForElementVisible(driver, PostsPageUI.ADD_NEW_BUTTON);
        clickToElement(driver, PostsPageUI.ADD_NEW_BUTTON);
    }


    public void inputToContentTextbox(String content) {
        waitForElementVisible(driver, PostsPageUI.CONTENT_TEXTAREA);
        sendkeysToElement(driver, PostsPageUI.CONTENT_TEXTAREA, content);
    }

    public void clickToAddNewCategoryTextlink() {
        waitForElementVisible(driver, PostsPageUI.ADD_NEW_CATEGORY_TEXTLINK);
        clickToElement(driver, PostsPageUI.ADD_NEW_CATEGORY_TEXTLINK);
    }


    public void clickToPublishButton() {
        waitForElementVisible(driver, PostsPageUI.PUBLISH_BUTTON);
        clickToElement(driver, PostsPageUI.PUBLISH_BUTTON);
    }


    public String getPublishSuccessMessage() {
        waitForElementVisible(driver, PostsPageUI.PUBLISHED_MESSAGE);
        return getTextElement(driver, PostsPageUI.PUBLISHED_MESSAGE);
    }


    public void inputToSearchTextbox(String text) {
        waitForElementVisible(driver, PostsPageUI.SEARCH_TEXTBOX);
        sendkeysToElement(driver, PostsPageUI.SEARCH_TEXTBOX, text);
    }

    public void clickToSearchPostButton() {
        waitForElementVisible(driver, PostsPageUI.SEARCHBOX_BUTTON);
        clickToElement(driver, PostsPageUI.SEARCHBOX_BUTTON);
    }

    public String getDataByColName(String colName) {
        waitForElementVisible(driver, PostsPageUI.DYNAMIC_DATA_INFOMATION_BY_COLUMNNAME, colName);
        return getTextElement(driver, PostsPageUI.DYNAMIC_DATA_INFOMATION_BY_COLUMNNAME, colName);
    }

    public String uploadRandomImage() {
        waitForElementVisible(driver, PostsPageUI.SELECT_FILE_BUTTON);
        String randomImageName = getRandomImageName();
        System.out.println("Random Image Name: " + randomImageName);
        uploadMultipleFiles(driver, PostsPageUI.FILE_TYPE_LOCATOR, randomImageName);
        return randomImageName;

    }

    public void clickToUploadFilesTab() {
        waitForElementVisible(driver, PostsPageUI.UPLOAD_FILE_BUTTON);
        clickToElement(driver, PostsPageUI.UPLOAD_FILE_BUTTON);
    }

    public void clickToSetFeaturedImage() {
        waitForElementVisible(driver, PostsPageUI.SET_FEATURED_IMAGE_BUTTON);
        clickToElement(driver, PostsPageUI.SET_FEATURED_IMAGE_BUTTON);
    }

    public UserPostsListPageObject navigateToUserSite() {
        waitForElementVisible(driver, PostsPageUI.BAR_SITE_NAME);
        clickToElement(driver, PostsPageUI.BAR_SITE_NAME);
        return WordpressPageGeneraterManager.getUserPostListPage(driver);
    }

    public void clickToTextTab() {
        waitForElementVisible(driver, PostsPageUI.TEXT_BUTTON_TAB);
        clickToElement(driver, PostsPageUI.TEXT_BUTTON_TAB);
    }

    public void clickToSetFeaturedImageTextlink() {
        waitForElementVisible(driver, PostsPageUI.SET_FEATURE_IMAGE_TEXTLINK);
        clickToElement(driver, PostsPageUI.SET_FEATURE_IMAGE_TEXTLINK);
    }

    public String getPublishedDate() {
        waitForElementVisible(driver, PostsPageUI.PUBLISHED_DATE);
        return getTextElement(driver, PostsPageUI.PUBLISHED_DATE);
    }

    public PostsPageObject clickToTitleNameTextLink() {
        waitForElementVisible(driver, PostsPageUI.TITLE_NAME_TEXTLINK);
        clickToElement(driver, PostsPageUI.TITLE_NAME_TEXTLINK);
        return WordpressPageGeneraterManager.getPostPage(driver);
    }

    public void removeTagName() {
        waitForElementVisible(driver, PostsPageUI.REMOVE_BUTTON);
        clickToElementByJS(driver, PostsPageUI.REMOVE_BUTTON);
    }

    public void uncheckToCategoryCheckbox() {
        waitForElementVisible(driver, PostsPageUI.CHECKED_CHECKBOX);
        unchecktoDefaultCheckbox(driver, PostsPageUI.CHECKED_CHECKBOX);
    }

    public void clickToRemoveThumbnailTextlink() {
        waitForElementVisible(driver, PostsPageUI.REMOVE_POST_THUMBNAL_TEXTLINK);
        clickToElementByJS(driver, PostsPageUI.REMOVE_POST_THUMBNAL_TEXTLINK);
    }

    public void clickToUpdateButton() {
        waitForElementVisible(driver, PostsPageUI.UPDATE_BUTTON);
        clickToElement(driver, PostsPageUI.UPDATE_BUTTON);
    }

    public void checkToSelectPost() {
        waitForElementVisible(driver, PostsPageUI.POST_SELECT_CHECKBOX);
        checktoDefaultCheckboxOrRadio(driver, PostsPageUI.POST_SELECT_CHECKBOX);
    }

    public void selectActionByText(String text) {
        waitForElementVisible(driver, PostsPageUI.DYNAMIC_BULK_ACTION_SELECTBOX);
        selectItemByTextInDefaultDropdown(driver, PostsPageUI.DYNAMIC_BULK_ACTION_SELECTBOX, text);
    }

    public void clickToApplyButton() {
        waitForElementVisible(driver, PostsPageUI.APPLY_BUTTON);
        clickToElement(driver, PostsPageUI.APPLY_BUTTON);
    }

    public CategoriesPageObject clickToCategoriesLink() {
        waitForElementVisible(driver, PostsPageUI.CATEGORIES_TEXTLINK_MENU);
        clickToElement(driver, PostsPageUI.CATEGORIES_TEXTLINK_MENU);
        return WordpressPageGeneraterManager.getAdminCategoriesListPage(driver);
    }

    public void checkToCategoryCheckboxByText(String text){
        waitForElementVisible(driver, PostsPageUI.CATEGORIES_CHECKBOX_BY_TEXT,text);
        checktoDefaultCheckboxOrRadio(driver, PostsPageUI.CATEGORIES_CHECKBOX_BY_TEXT,text);
    }


}
