package pageObject.wordpress;

import commons.BasePage;
import commons.WordpressPageGeneraterManager;
import org.openqa.selenium.WebDriver;
import pageUIs.wordpress.PostsPageUI;
import pageUIs.wordpress.UserPostsListPageUI;

public class UserPostsListPageObject extends BasePage {
    WebDriver driver;

    public UserPostsListPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public PostsPageObject navigateToAdminSite() {
        waitForElementVisible(driver, UserPostsListPageUI.ADMIN_ICON_TEXTLINK);
        clickToElement(driver, UserPostsListPageUI.ADMIN_ICON_TEXTLINK);
        return WordpressPageGeneraterManager.getPostPage(driver);
    }

    private String getSrcAttributeImage() {
        waitForElementVisible(driver, UserPostsListPageUI.SRC_ATTRIBUTE_IMAGE);
        return getAttributeValue(driver, UserPostsListPageUI.SRC_ATTRIBUTE_IMAGE, "src");
    }

    public String getFileNameAtUserPostListPage() {
        String srcPath = getSrcAttributeImage();
        int lastSlashIndex = srcPath.lastIndexOf('/');
        if (lastSlashIndex != -1 && lastSlashIndex < srcPath.length() - 1) {
            return srcPath.substring(lastSlashIndex + 1);
        }
        return "";
    }

    public String getPostTitle() {
        waitForElementVisible(driver, UserPostsListPageUI.POST_TITLE);
        return getTextElement(driver, UserPostsListPageUI.POST_TITLE);
    }

    public String getCreatedPostDate() {
        waitForElementVisible(driver, UserPostsListPageUI.CREATED_DATE);
        return getTextElement(driver, UserPostsListPageUI.CREATED_DATE);
    }


}
