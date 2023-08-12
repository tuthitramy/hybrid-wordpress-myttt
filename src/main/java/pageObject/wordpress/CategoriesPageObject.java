package pageObject.wordpress;

import commons.BasePage;
import commons.WordpressPageGeneraterManager;
import org.openqa.selenium.WebDriver;
import pageUIs.wordpress.CategoriesPageUI;
import pageUIs.wordpress.PostsPageUI;

public class CategoriesPageObject extends BasePage {
    WebDriver driver;

    public CategoriesPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void selectRandomParentInDropdownByText() {
        waitForElementVisible(driver, CategoriesPageUI.PARENT_CATEGORIES_DROPDOWN);
        selectRandomSelectionInDropdown(driver, CategoriesPageUI.PARENT_CATEGORIES_DROPDOWN);
    }

    public void inputToDescriptionTextArea(String text) {
        waitForElementVisible(driver, CategoriesPageUI.DESCRIPTION_TEXTAREA);
        sendkeysToElement(driver, CategoriesPageUI.DESCRIPTION_TEXTAREA, text);
    }

    public boolean isCategoryDisplayed(String category) {
        waitForElementVisible(driver, CategoriesPageUI.DYNAMIC_CATEGORY_BY_TEXT, category);
        return isElementDisplayed(driver, CategoriesPageUI.DYNAMIC_CATEGORY_BY_TEXT, category);

    }

    public void hoverToCateGoryName(String category) {
        waitForElementVisible(driver, CategoriesPageUI.DYNAMIC_CATEGORY_BY_TEXT, category);
        hoverMouseToElement(driver, CategoriesPageUI.DYNAMIC_CATEGORY_BY_TEXT, category);
    }

    public void clickToViewTextLink() {
        waitForElementVisible(driver, CategoriesPageUI.VIEW_TEXTLINK);
        clickToElement(driver, CategoriesPageUI.VIEW_TEXTLINK);
    }


}
