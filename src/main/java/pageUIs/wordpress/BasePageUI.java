package pageUIs.wordpress;

public class BasePageUI {
    public static final String DYNAMIC_PAGE_BY_TEXT = "xpath=//div[text()='%s']//preceding-sibling::div//parent::a";
    public static final String DYNAMIC_TEXTBOX_BY_ID = "xpath=//input[@id='%s']";
    public static final String DYNAMIC_INPUT_TYPE_BUTTON_BY_VALUE = "xpath=//input[@value='%s' and @type='button']";

    public static final String DYNAMIC_BUTTON_WITH_SUBMIT_TYPE_BY_TEXT = "xpath=//button[@type='submit' and text()='%s']";
    public static final String DYNAMIC_BUTTON_WITH_SUBMIT_TYPE_BY_VALUE = "xpath=//button[@type='submit' and @value='%s']";

    public static final String DYNAMIC_INPUT_WITH_SUBMIT_TYPE_BY_VALUE = "xpath=//input[@type='submit' and @value='%s']";

    public static final String NO_ITEMS_MESSAGE = "xpath=//td[@class='colspanchange']";


}
