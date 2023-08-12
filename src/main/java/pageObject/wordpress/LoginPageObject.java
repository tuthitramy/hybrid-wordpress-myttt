package pageObject.wordpress;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.wordpress.LoginPageUI;

public class LoginPageObject extends BasePage {
    WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void inputToTextboxByID(String inputValue, String id) {
        waitForElementVisible(driver, LoginPageUI.DYNAMIC_TEXTBOX_BY_ID, id);
        sendkeysToElement(driver, LoginPageUI.DYNAMIC_TEXTBOX_BY_ID, inputValue, id);
    }

    public void clickToButtonLogin() {
        waitForElementVisible(driver, LoginPageUI.BUTTON_LOGIN);
        clickToElement(driver, LoginPageUI.BUTTON_LOGIN);
    }

    public String getLoginErrorMessage() {
        waitForElementVisible(driver, LoginPageUI.LOGIN_ERROR_MESSAGE);
        return getTextElement(driver, LoginPageUI.LOGIN_ERROR_MESSAGE);
    }

}
