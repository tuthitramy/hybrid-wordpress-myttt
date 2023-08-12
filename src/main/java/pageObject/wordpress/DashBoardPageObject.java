package pageObject.wordpress;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.wordpress.DashBoardPageUI;
import pageUIs.wordpress.LoginPageUI;

public class DashBoardPageObject extends BasePage {
    WebDriver driver;

    public DashBoardPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDashBoardPageDisplayed() {
        waitForElementVisible(driver, DashBoardPageUI.DASHBOARD_TITLE);
        return isElementDisplayed(driver, DashBoardPageUI.DASHBOARD_TITLE);

    }


}
