package commons;

import org.openqa.selenium.WebDriver;
import pageObject.wordpress.*;

public class WordpressPageGeneraterManager {

    public static LoginPageObject getLoginPage(WebDriver driver) {
        return new LoginPageObject(driver);
    }

    public static DashBoardPageObject getDashBoardPage(WebDriver driver) {
        return new DashBoardPageObject(driver);
    }

    public static PostsPageObject getPostPage(WebDriver driver) {
        return new PostsPageObject(driver);
    }

    public static UserPostsListPageObject getUserPostListPage(WebDriver driver) {
        return new UserPostsListPageObject(driver);

    }

    public static CategoriesPageObject getAdminCategoriesListPage(WebDriver driver) {
        return new CategoriesPageObject(driver);

    }


}
