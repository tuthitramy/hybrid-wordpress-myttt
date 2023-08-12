package pageUIs.wordpress;

import commons.BasePage;

public class UserPostsListPageUI extends BasePage {
    public static final String ADMIN_ICON_TEXTLINK = "xpath=//li[@id='wp-admin-bar-wp-logo' and @class='menupop']/a";
    public static final String SRC_ATTRIBUTE_IMAGE = "xpath=//div[@class='post-thumbnail']/img";

    public static final String POST_TITLE = "xpath=//h3[@class='post-title mb-2 mt-2']/a";

    public static final String CREATED_DATE = "xpath=//span[@class='entry-date']/a";


}
