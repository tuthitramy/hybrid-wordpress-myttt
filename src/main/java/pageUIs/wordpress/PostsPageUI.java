package pageUIs.wordpress;

public class PostsPageUI {

    public static final String ADD_NEW_BUTTON = "xpath=//a[@class='page-title-action' and text()='Add New']";

    public static final String TEXT_BUTTON_TAB = "xpath=//button[@data-wp-editor-id='content' and @id='content-html']";
    public static final String CONTENT_TEXTAREA = "xpath=//textarea[@class='wp-editor-area' and @id='content']";
    public static final String ADD_NEW_CATEGORY_TEXTLINK = "xpath=//a[@id='category-add-toggle']";
    public static final String ADD_NEW_CATEGORY_BTN = "xpath=//button[text()='Add New Category' and @type='submit']";

    public static final String PUBLISH_BUTTON = "xpath=//input[@type='submit' and @id='publish']";

    public static final String PUBLISH_BUTTON_CONFIRMATION = "xpath=//div[@class='editor-post-publish-panel']//button[text()='Publish']";

    public static final String PUBLISHED_MESSAGE = "xpath=//div[@id='message']/p";

    public static final String CHECKED_CHECKBOX = "xpath=//ul[@id='categorychecklist']//input[@checked='checked']";


    public static final String SEARCH_TEXTBOX = "xpath=//input[@id='post-search-input']";

    public static final String SEARCHBOX_BUTTON = "xpath=//input[@id='search-submit']";

    public static final String DYNAMIC_DATA_INFOMATION_BY_COLUMNNAME = "xpath=//td[@data-colname='%s']//a";
    public static final String SET_FEATURE_IMAGE_TEXTLINK = "xpath=//a[@id='set-post-thumbnail']";

    public static final String SELECT_FILE_BUTTON = "xpath=//button[text()='Select Files' and @type='button']";

    public static final String FILE_TYPE_LOCATOR = "xpath=//input[@type='file']";

    public static final String UPLOAD_FILE_BUTTON = "xpath=//button[@type='button' and @id='menu-item-upload']";

    public static final String SET_FEATURED_IMAGE_BUTTON = "xpath=//div[@class='media-toolbar-primary search-form']//button[@type='button' and text()='Set featured image']";
    public static final String BAR_SITE_NAME = "xpath=//li[@id='wp-admin-bar-site-name']/a";
    public static final String ADD_NEW_POST_HEADER = "xpath=//h1[@class='wp-heading-inline']";
    public static final String PUBLISHED_DATE = "xpath=//td[@class='date column-date']";
    public static final String TITLE_NAME_TEXTLINK = "xpath=//a[@class='row-title']";
    public static final String REMOVE_BUTTON = "xpath=//span[@class='remove-tag-icon']";

    public static final String REMOVE_POST_THUMBNAL_TEXTLINK = "xpath=//a[@id='remove-post-thumbnail']";
    public static final String UPDATE_BUTTON = "xpath=//input[@value='Update' and @type='submit']";
    public static final String POST_SELECT_CHECKBOX = "xpath=//tr[1]//input[@name='post[]']";

    public static final String DYNAMIC_BULK_ACTION_SELECTBOX = "xpath=//select[@name='action']";

    public static final String APPLY_BUTTON = "xpath=//input[@type='submit' and @value='Apply' and @id='doaction']";

    public static final String CATEGORIES_TEXTLINK_MENU = "xpath=//li[@id='menu-posts']//ul[@class='wp-submenu wp-submenu-wrap']//a[text()='Categories']";
    public static final String CATEGORIES_CHECKBOX_BY_TEXT = "xpath=//label[text()=' %s']/input";

}
